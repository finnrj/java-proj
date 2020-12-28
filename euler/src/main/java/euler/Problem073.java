package euler;

import org.apache.commons.lang3.tuple.Pair;
import utils.Utils;

import java.util.Comparator;
import java.util.stream.LongStream;

/**
 * </div>
 * <h2>Counting fractions in a range</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 73</h3>
 * <span>Published on Friday, 2nd July 2004, 06:00 pm; Solved by 16558; Difficulty rating: 15%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Consider the fraction, <i>n/d</i>, where <i>n</i> and <i>d</i> are positive integers. If <i>n</i>&lt;<i>d</i> and HCF(<i>n,d</i>)=1, it is called a reduced proper fraction.</p>
 * <p>If we list the set of reduced proper fractions for <i>d</i> ≤ 8 in ascending order of size, we get:</p>
 * <p style="text-align:center;font-size:90%;">1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, <b>3/8, 2/5, 3/7</b>, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8</p>
 * <p>It can be seen that there are 3 fractions between 1/3 and 1/2.</p>
 * <p>How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for <i>d</i> ≤ 12,000?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem073 {

    public static void main(String[] args) {
        String format = "n/d: %d/%d";
        long subtract = 1L;
        Comparator<Pair<Long, Long>> pairComparator = (p1, p2) -> Long.compare(p2.getValue() * p1.getKey(), p1.getValue() * p2.getKey());
        System.out.println(LongStream.rangeClosed(2, 12_000).boxed()
//                .peek(System.out::println)
                .flatMap(d -> LongStream.range(1, d).boxed()
                        .filter(n -> 3 * n > d
                                && 2 * n < d
                                && Utils.gcd(n, d) == 1)
//                    .peek(n -> System.out.println("in interval: "  +String.format(format, n,d)))
                        .map(n -> Pair.of(n, d)))
                .sorted(pairComparator)
                .peek(System.out::println)
                .count());

    }

}
