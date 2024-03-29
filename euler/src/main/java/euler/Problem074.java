package euler;

import utils.Combinations;
import utils.Permutations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * </div>
 * <h2>Digit factorial chains</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 74</h3>
 * <span>Published on Friday, 16th July 2004, 06:00 pm; Solved by 17208; Difficulty rating: 15%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:</p>
 * <p style="margin-left:50px;">1! + 4! + 5! = 1 + 24 + 120 = 145</p>
 * <p>Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169; it turns out that there are only three such loops that exist:</p>
 * <p style="margin-left:50px;">169 → 363601 → 1454 → 169<br> 871 → 45361 → 871<br> 872 → 45362 → 872</p>
 * <p>It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,</p>
 * <p style="margin-left:50px;">69 → 363600 → 1454 → 169 → 363601 (→ 1454)<br> 78 → 45360 → 871 → 45361 (→ 871)<br> 540 → 145 (→ 145)</p>
 * <p>Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting number below one million is sixty terms.</p>
 * <p>How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem074 {
    static Map<String, Long> factorials = LongStream.rangeClosed(0, 9)
            .boxed().collect(Collectors.toMap(String::valueOf, Permutations::factorial));

    static Long next(Long target) {
        return target.toString().chars().mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.summingLong(d -> Long.valueOf(factorials.get(d))));
    }

    private static long findChain(Long target, HashSet<Long> chain) {
        if (chain.contains(target)) {
            return chain.size();
        }
        chain.add(target);
        return findChain(next(target), chain);
    }

    public static void main(String[] args) {
        System.out.println(LongStream.range(1, 1_000_000).boxed()
                .mapToLong(l -> findChain(l, new HashSet<>()))
                .filter(l -> l == 60)
                .count());
    }

}
