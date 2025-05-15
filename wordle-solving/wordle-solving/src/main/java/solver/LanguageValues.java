package solver;

import java.net.http.WebSocket;
import java.util.List;

public record LanguageValues(String filename, String bestCandidate,
                             String formatPattern, String enterPrompt, String solutionFound, String noSolution,
                             List<String> excludedWords) {
}
