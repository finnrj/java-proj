package euler;

import org.apache.commons.lang3.tuple.Pair;
import utils.Utils;

import java.util.Comparator;
import java.util.stream.LongStream;

/**
 * </div>
 * <h2>Ordered fractions</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 71</h3>
 * <span>Published on Friday, 4th June 2004, 06:00 pm; Solved by 18616; Difficulty rating: 10%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Consider the fraction, <i>n/d</i>, where <i>n</i> and <i>d</i> are positive integers. If <i>n</i>&lt;<i>d</i> and HCF(<i>n,d</i>)=1, it is called a reduced proper fraction.</p>
 * <p>If we list the set of reduced proper fractions for <i>d</i> ≤ 8 in ascending order of size, we get:</p>
 * <p style="text-align:center;font-size:90%;">1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, <b>2/5</b>, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8</p>
 * <p>It can be seen that 2/5 is the fraction immediately to the left of 3/7.</p>
 * <p>By listing the set of reduced proper fractions for <i>d</i> ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem071 {

    public static void main(String[] args) {
        String format = "n/d: %d/%d";
        long subtract = 1L;
        Pair<Long, Long> threshold = Pair.of(3 * 142857L - subtract, 7 * 142857L); // (428571 - subtract, 999999)
        Comparator<Pair<Long, Long>> pairComparator = (p1, p2) -> Long.compare(p2.getValue() * p1.getKey(), p1.getValue() * p2.getKey());
        LongStream.rangeClosed(2, 1_000_000).boxed()
//                .peek(System.out::println)
                .flatMap(d -> LongStream.range(1, d).boxed()
                        .filter(n -> threshold.getValue() * n > d * threshold.getKey()
                                && 7 * n < 3 * d
                                && Utils.gcd(n, d) == 1)
//                    .peek(n -> System.out.println("in interval: "  +String.format(format, n,d)))
                        .map(n -> Pair.of(n, d)))
                .sorted(pairComparator)
                .forEach(System.out::println); // (428570,999997)
    }

}
