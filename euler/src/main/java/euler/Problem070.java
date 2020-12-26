package euler;

import org.apache.commons.lang3.tuple.Pair;
import utils.Utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static utils.Utils.phi;

/**
 * </div>
 * <h2>Totient permutation</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 70</h3>
 * <span>Published on Friday, 21st May 2004, 06:00 pm; Solved by 14347; Difficulty rating: 20%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Euler's Totient function, φ(<var>n</var>) [sometimes called the phi function], is used to determine the number of positive numbers less than or equal to <var>n</var> which are relatively prime to <var>n</var>. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.<br>The number 1 is considered to be relatively prime to every positive number, so φ(1)=1. </p>
 * <p>Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.</p>
 * <p>Find the value of <var>n</var>, 1 &lt; <var>n</var> &lt; 10<sup>7</sup>, for which φ(<var>n</var>) is a permutation of <var>n</var> and the ratio <var>n</var>/φ(<var>n</var>) produces a minimum.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem070 {

    public static void main(String[] args) {
        Instant start = Instant.now();
        LongStream.range(2, 10_000_000)
                .boxed()
                .map(l -> Pair.of(l, phi(l)))
                .filter(p -> Stream.of(p.getKey(), Utils.phi(p.getValue()))
                        .collect(Collectors.groupingBy(Utils::tagAsOrderedString))
                        .size() == 1)
//                .peek(l -> System.out.println(String.format("%-7d: %d", l, phi(l))))
                .map(p -> Pair.of(p.getKey(), (p.getKey() * 1.0) / phi(p.getValue())))
        .min(Comparator.comparing(Map.Entry::getValue))
        .ifPresent(System.out::println);
        System.out.println(String.format("%-20s: %s", "using phi and tagging", Duration.between(start, Instant.now())));
    }

}
