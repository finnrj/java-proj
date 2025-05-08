package solver;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        List<Integer> freeIndices = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        int result = 0;
        for (int i = candidate.length() -1; i >= 0; i--) {
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
        try (Stream<String> lines = Files.lines(Paths.get("src", "main", "resources","words"))) {
            List<String> words = new ArrayList<>(lines.map(String::trim).toList());
//            System.out.println(words.size());
            WordleSolver solver = new WordleSolver();
            words.sort(Comparator.comparingInt(String::length));
//            System.out.println(words.get(0) + " " + words.get(words.size() - 2) + " " + words.get(words.size() - 1));
            Stream<BuildResult> r = words.stream().filter(str -> str.length() == 5).map(word -> solver.build(word, words)).sorted();
                    r.forEach(br -> System.out.println(br.word() + " " + br.results().size()));
        }
    }

}
