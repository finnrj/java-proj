package solver;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;
import java.util.*;
import java.util.stream.Stream;

public class WordleSolver {

    public record BuildResult(String word, Map<Integer, List<String>> results) implements Comparable<BuildResult> {
        @Override
        public int compareTo(BuildResult buildResult) {
            return this.results().size() - buildResult.results().size();
        }
    }

    public Integer compare(String candidate, String target) {
        List<Integer> freeIndices = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        int result = 0;
        for (int i = candidate.length() - 1; i >= 0; i--) {
            if (target.charAt(i) == candidate.charAt(i)) {
                result += 2 * Math.pow(10, (4 - i));
                freeIndices.remove(i);
            }
        }
        for (int i = 0; i < candidate.length(); i++) {
            for (int j = freeIndices.size() - 1; j >= 0; j--) {
                Integer freeIndex = freeIndices.get(j);
                if (candidate.charAt(i) == target.charAt(freeIndex)
                        && candidate.charAt(i) != target.charAt(i)) {
                    result += 1 * Math.pow(10, (4 - i));
                    freeIndices.remove(j);
                    break;
                }
            }
        }
        return result;
    }

    public BuildResult build(String actual, List<String> words) {
        HashMap<Integer, List<String>> result = new HashMap<>();
        for (String word : words) {
            Integer score = compare(actual, word);
            if (result.containsKey(score)) {
                result.get(score).add(word);
            } else {
                result.put(score, new ArrayList<>(Collections.singletonList(word)));
            }
        }
        return new BuildResult(actual, result);
    }

    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("src", "main", "resources", "words"))) {
            List<String> words = new ArrayList<>(lines.map(String::trim).toList());
            WordleSolver solver = new WordleSolver();
            words.sort(Comparator.comparingInt(String::length));
//            build01(words, solver);
            List<String> wordsLeft = words;
            String bestCandidate = "tares";
            Scanner input = new Scanner(System.in);
            while (wordsLeft.size() > 1) {
                System.out.println("Please enter result for '" + bestCandidate + "' :");
                String strippedInput = input.nextLine().strip();
                Integer result = NumberUtils.isDigits(strippedInput) ? Integer.parseInt(strippedInput) : 0;
                wordsLeft = solver.build(bestCandidate, wordsLeft).results().get(result);
                final List<String> wordsLeftFinal = wordsLeft;
                bestCandidate = wordsLeft.stream().filter(str -> str.length() == 5).map(word -> solver.build(word, wordsLeftFinal)).sorted()
                        .map(BuildResult::word).reduce((first, second) -> second).orElse("NO RESULT FOUND!!");
            }
            System.out.println("The solution must be " + wordsLeft.get(0));
        }
    }

    private static void build01(List<String> words, WordleSolver solver) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/build_01.txt"));
        Stream<BuildResult> r = words.stream().filter(str -> str.length() == 5).map(word -> solver.build(word, words)).sorted();
//            r.forEach(br -> System.out.println(br.word() + " " + br.results().size()));
        r.map(br -> String.format("%s - %04d", br.word(), br.results().size())).peek(System.out::println)
                .forEach(pw::println);
        pw.close();
    }

}
