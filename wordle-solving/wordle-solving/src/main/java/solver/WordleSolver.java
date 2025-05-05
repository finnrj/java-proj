package solver;

import java.util.*;

public class WordleSolver {
    public Short compare(String actual, String target) {
        short result = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == actual.charAt(i)) {
                result += 2 * Math.pow(10, (4-i));
            } else if (target.indexOf(actual.charAt(i)) != -1) {
                result += 1 * Math.pow(10, (4-i));
            }
        }
        return result;
    }

    public Map<Short,List<String>> build(String actual, List<String> words) {
        HashMap<Short, List<String>> result = new HashMap<>();
        for (String word : words) {
            short score = compare(actual, word);
            if (result.containsKey(score)) {
                result.get(score).add(word);
            } else {
                result.put(score, new ArrayList<>(Collections.singletonList(word)));
            }
        }
        return result;
    }
}
