package solver;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordleSolver {

    private static final Map<String, LanguageValues> LANGUAGES = new HashMap<>();
    private static Scanner input;

    static {
        LANGUAGES.put("EN",
                new LanguageValues("words",
                        "tares",
                        "Format: 5 digit number, 0 = no match, 1 = match, but wrong position, 2 = match and correct position",
                        "Please enter result for '%s' :",
                        "The solution must be ",
                        new ArrayList<>()));
        LANGUAGES.put("DE",
                new LanguageValues("words-german",
                        "seilt",
                        "Format: 5 ziffriger Zahl, 0 = kein Match, 1 = Match aber falsch positioniert, 2 = Match und korrekt positioniert",
                        "Bitte taste Ergebniss fur '%s' :",
                        "Die Losung ist ",
                        List.of("Alkyl")));
        LANGUAGES.put("DA",
                new LanguageValues("words-danish",
                        "silet",
                        "Format: 5 cifret tal, 0 = ingen match, 1 = match, men forkert position, 2 = match og korrekt position",
                        "Indtast resultat for '%s' :",
                        "Løsningen må være ",
                        new ArrayList<>()));
    };


    public record BuildResult(String word, Map<Integer, List<String>> results) implements Comparable<BuildResult> {
        @Override
        public int compareTo(BuildResult buildResult) {
            // reversed order
            return buildResult.results().size() - this.results().size();
        }
    }

    public Integer compare(String candidate, String target) {
        if (target.length() < candidate.length()) {
            System.out.println("Target word is shorter than candidate word'" + target + "'");
            return -1;
        }
        List<Integer> freeIndices = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        int result = 0;
        for (int i = candidate.length() - 1; i >= 0; i--) {
            if (target.charAt(i) == candidate.charAt(i)) {
                result += (int) (2 * Math.pow(10, (4 - i)));
                freeIndices.remove(i);
            }
        }
        for (int i = 0; i < candidate.length(); i++) {
            for (int j = freeIndices.size() - 1; j >= 0; j--) {
                Integer freeIndex = freeIndices.get(j);
                if (candidate.charAt(i) == target.charAt(freeIndex)
                        && candidate.charAt(i) != target.charAt(i)) {
                    result += (int) (1 * Math.pow(10, (4 - i)));
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
        input = new Scanner(System.in);
        LanguageValues actualLanguage = chooseLanguage();

        try (Stream<String> lines = Files.lines(Paths.get("src", "main", "resources", actualLanguage.filename()))) {
            List<String> words = new ArrayList<>(lines.map(String::trim).toList());
            WordleSolver solver = new WordleSolver();
            runWordleGuessing(words.stream()
                    .map(String::trim)
                    .filter(str -> str.length() == 5 && StringUtils.containsNone(str, "-'.")).collect(Collectors.toList()),
                    solver,
                    actualLanguage);
        }
    }

    private static LanguageValues chooseLanguage() {
        String strippedInput = "";
        do {
            System.out.println("Please select language among: " + LANGUAGES.keySet() + ": ");
            strippedInput = input.nextLine().strip().toUpperCase();
        } while (!LANGUAGES.containsKey(strippedInput));
        return LANGUAGES.get(strippedInput);
    }

    private static void runWordleGuessing(List<String> words, WordleSolver solver, LanguageValues actualLanguage) {
        List<String> wordsLeft = words;
        String bestCandidate = actualLanguage.bestCandidate();
        while (wordsLeft.size() > 1) {
            String strippedInput;
            System.out.println(actualLanguage.formatPattern());
            do {
                System.out.println(String.format(actualLanguage.enterPrompt(), bestCandidate));
                strippedInput = input.nextLine().strip();
            } while (!(NumberUtils.isDigits(strippedInput) && strippedInput.length() == 5));
            wordsLeft = solver.build(bestCandidate, wordsLeft).results().get(Integer.parseInt(strippedInput));
            final List<String> wordsLeftFinal = wordsLeft;
            bestCandidate = words.stream().filter(str -> str.length() == 5).map(word -> solver.build(word, wordsLeftFinal)).sorted()
                    .map(BuildResult::word).dropWhile(actualLanguage.excludedWords()::contains).findFirst().orElse("NO RESULT FOUND FOR BEST CANDIDATE!!");
        }
        System.out.println( actualLanguage.solutionFound() + wordsLeft.getFirst());
    }

    private static void build01(List<String> words, WordleSolver solver, String filename) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/" + filename));
        List<String> r = words.stream()
                .map(String::trim)
                .filter(str -> str.length() == 5 && StringUtils.containsNone(str, "-'.")).collect(Collectors.toList());
        r.stream().map(word -> solver.build(word, r)).sorted()
                .map(br -> String.format("%s - %04d", br.word(), br.results().size())).peek(System.out::println)
                .forEach(pw::println);
        pw.close();
    }

}
