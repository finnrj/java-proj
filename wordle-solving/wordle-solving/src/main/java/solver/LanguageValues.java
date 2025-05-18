package solver;

import java.util.List;

public record LanguageValues(String filename, String bestStartingCandidate,
                             PromptValues promptValues,
                             SolutionValues solutionValues,
                             List<String> excludedWords) {

    public record PromptValues(String format, String prompt, String invalidInput) {}
    public record SolutionValues(String solutionFound, String noSolution) {}

}
