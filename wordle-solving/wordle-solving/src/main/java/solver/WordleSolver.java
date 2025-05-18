package solver;

import org.apache.commons.lang3.RegExUtils;
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
    public static final int GAMBLE_POSSIBLE_SOLUTION_RANGE = 0;
    private static Scanner input;

    static {
        LANGUAGES.put("EN",
                new LanguageValues("words",
                        "tares",
                        new LanguageValues.PromptValues(
                                "Format: 5 digit number, 0 = no match, 1 = match, but wrong position, 2 = match and correct position",
                                "Please enter result for '%s' :",
                                "invalid input: '%s'"
                        ),
                        new LanguageValues.SolutionValues("The solution must be ", "No solution found "),
                        List.of()));

        LANGUAGES.put("DE",
                new LanguageValues("words-german",
                        "seilt",
                        new LanguageValues.PromptValues(
                                "Format: 5 ziffriger Zahl, 0 = kein Match, 1 = Match aber falsch positioniert, 2 = Match und korrekt positioniert",
                                "Bitte taste Ergebnis fur '%s' :",
                                "Invalides Input: '%s'"
                        ),
                        new LanguageValues.SolutionValues("Die Lösung ist ", "Keine Lösung gefunden"),
                        List.of("BGHSt", "UNHCR")));
        LANGUAGES.put("DA",
                new LanguageValues("words-danish",
                        "silet",
                        new LanguageValues.PromptValues(
                                "Format: 5 cifret tal, 0 = ingen match, 1 = match, men forkert position, 2 = match og korrekt position",
                                "Indtast resultat for '%s' :",
                                "Fejlagtigt input: '%s"
                        ),
                        new LanguageValues.SolutionValues("Løsningen må være ", "Ingen løsning fundet "),
                        List.of()));
    }

    ;

    public record BuildResult(String word, Map<Integer, List<String>> results) implements Comparable<BuildResult> {
        @Override
        public int compareTo(BuildResult buildResult) {
            // reversed order
            return  - difference(buildResult);
        }

        public int difference(BuildResult other) {
            return this.results().size() - other.results().size();
        }
    }

    public Integer compare(String candidate, String target) {
        target = target.toLowerCase();
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
//            build01 (words, solver, actualLanguage.filename(), actualLanguage.excludedWords());
            runWordleGuessing(
                    words.stream()
                            .map(String::trim)
                            .distinct()
                            .filter(str -> str.length() == 5
                                    && StringUtils.containsNone(str, "-'.")
                                    && !actualLanguage.excludedWords().contains(str))
                            .toList(),
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
        String bestCandidate = actualLanguage.bestStartingCandidate();
        System.out.println(actualLanguage.promptValues().format());

        wordsLeft = doGuessWordle(words, solver, actualLanguage, wordsLeft, bestCandidate);
        handleResult(actualLanguage, wordsLeft);
    }

    private static List<String> doGuessWordle(List<String> words, WordleSolver solver, LanguageValues actualLanguage, List<String> wordsLeft, String bestCandidate) {
        while (notFinishedGuessing(wordsLeft)) {
            String strippedInput = fetchGuessResult(actualLanguage.promptValues(), bestCandidate);
            wordsLeft = solver.build(bestCandidate, wordsLeft).results().getOrDefault(Integer.parseInt(strippedInput), Collections.emptyList());
            final List<String> wordsLeftFinal = wordsLeft;
            List<BuildResult> buildResults = words.stream().filter(str -> str.length() == 5
                            && StringUtils.containsNone(str, "-'.")
                            && !actualLanguage.excludedWords().contains(str))
                    .map(word1 -> solver.build(word1, wordsLeftFinal)).sorted().toList();
            BuildResult firstElement = buildResults.get(0);
            bestCandidate = buildResults.stream()
                    .takeWhile(br -> firstElement.difference(br) <= GAMBLE_POSSIBLE_SOLUTION_RANGE)
                    .map(BuildResult::word)
                    .dropWhile(word -> actualLanguage.excludedWords().contains(word))
                    .filter(wordsLeftFinal::contains)
                    .findFirst()
                    .orElse(firstElement.word());
        }
        return wordsLeft;
    }

    private static boolean notFinishedGuessing(List<String> wordsLeft) {
        return wordsLeft.size() > 1;
    }

    private static void handleResult(LanguageValues actualLanguage, List<String> wordsLeft) {
        if (wordsLeft.isEmpty()) {
            System.out.println(actualLanguage.solutionValues().noSolution());
        } else {
            System.out.println(actualLanguage.solutionValues().solutionFound() + wordsLeft.get(0));
        }
    }

    private static String fetchGuessResult(LanguageValues.PromptValues promptValues, String bestCandidate) {
        String strippedInput = "no input yet";
        int round = 0;
        do {
            if (round > 0) {
                System.out.println(String.format(promptValues.invalidInput(), strippedInput));
            }
            System.out.println(String.format(promptValues.prompt(), bestCandidate));
            strippedInput = input.nextLine().strip();
            round++;
        } while (!isValidInput(strippedInput));
        return strippedInput;
    }

    private static boolean isValidInput(String strippedInput) {
        return NumberUtils.isDigits(strippedInput)
                && strippedInput.length() == 5
                && RegExUtils.dotAllMatcher("[012]{5}", strippedInput).matches();
    }

    static void build01(List<String> words, WordleSolver solver, String filename, List<String> excludes) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/" + filename));
        List<String> r = words.stream()
                .map(String::trim)
                .distinct()
                .filter(str -> str.length() == 5
                        && StringUtils.containsNone(str, "-'.")
                        && !excludes.contains(str)).collect(Collectors.toList());
        r.stream().map(word -> solver.build(word, r)).sorted()
                .peek(br -> System.out.println(String.format("%s - %04d", br.word(), br.results().size())))
                .map(BuildResult::word)
                .forEach(pw::println);
        pw.close();
        System.out.println("File src/main/resources/" + filename + " created with " + r.size() + " words");
    }

}
