package euler;

import org.apache.commons.lang3.tuple.Pair;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static utils.Utils.*;

/**
 * </div>
 * <h2>Totient maximum</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 69</h3>
 * <span>Published on Friday, 7th May 2004, 06:00 pm; Solved by 22059; Difficulty rating: 10%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Euler's Totient function, φ(<i>n</i>) [sometimes called the phi function], is used to determine the number of numbers less than <i>n</i> which are relatively prime to <i>n</i>. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.</p>
 * <div style="margin-left:100px;">
 * <table border="1">
 * <tbody>
 * <tr>
 * <td><b><i>n</i></b></td>
 * <td><b>Relatively Prime</b></td>
 * <td><b>φ(<i>n</i>)</b></td>
 * <td><b><i>n</i>/φ(<i>n</i>)</b></td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>1</td>
 * <td>1</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>1,2</td>
 * <td>2</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>1,3</td>
 * <td>2</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>1,2,3,4</td>
 * <td>4</td>
 * <td>1.25</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>1,5</td>
 * <td>2</td>
 * <td>3</td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>1,2,3,4,5,6</td>
 * <td>6</td>
 * <td>1.1666...</td>
 * </tr>
 * <tr>
 * <td>8</td>
 * <td>1,3,5,7</td>
 * <td>4</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>9</td>
 * <td>1,2,4,5,7,8</td>
 * <td>6</td>
 * <td>1.5</td>
 * </tr>
 * <tr>
 * <td>10</td>
 * <td>1,3,7,9</td>
 * <td>4</td>
 * <td>2.5</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>It can be seen that <i>n</i>=6 produces a maximum <i>n</i>/φ(<i>n</i>) for <i>n</i> ≤ 10.</p>
 * <p>Find the value of <i>n</i> ≤ 1,000,000 for which <i>n</i>/φ(<i>n</i>) is a maximum.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem069 {
        public static final Long END_INCLUSIVE = 1_000_000L;

    public static long eulerTotient(Long target) {
        if (isPrime(target)) {
            return target - 1;
        }
        return LongStream.range(1, target)
                .boxed()
                .filter(sm -> gcd(target, sm) == 1)
                .count();
    }

    public static Double eulerTotientPrimeFactors(Long target) {
        if (isPrime(target)) {
            return target - 1.0;
        }
        return primeFactors(target).boxed().collect(Collectors.groupingBy(Function.identity()))
        .entrySet().stream().map(e -> (e.getKey() - 1)*(Math.pow(e.getKey(), e.getValue().size() - 1)))
        .reduce(1.0, (a,b) -> a * b);
    }

    static long phi(long n)
    {
        // Initialize result as n
        long result = n;

        // Consider all prime factors
        // of n and subtract their
        // multiples from result
        for (long p = 2; p * p <= n; ++p)
        {

            // Check if p is
            // a prime factor.
            if (n % p == 0)
            {

                // If yes, then update
                // n and result
                while (n % p == 0)
                    n /= p;
                result -= result / p;
            }
        }

        // If n has a prime factor
        // greater than sqrt(n)
        // (There can be at-most
        // one such prime factor)
        if (n > 1)
            result -= result / n;
        return result;
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
//        LongStream.rangeClosed(2L, END_INCLUSIVE)
//                .boxed()
//                .map(l -> Pair.of(l, l / eulerTotientPrimeFactors(l)))
////                .peek(System.out::println)
//                .max(Comparator.comparing(Pair::getValue))
//                .ifPresentOrElse(System.out::println, () -> System.out.println("too bad"));
//        System.out.println(String.format("%-20s: %s", "totientPrimeFactors", Duration.between(start, start = Instant.now())));
//        LongStream.rangeClosed(2L, END_INCLUSIVE)
//                .boxed()
//                .map(l -> Pair.of(l, (l * 1.0) / eulerTotient(l)))
////                .peek(System.out::println)
//                .max(Comparator.comparing(Pair::getValue))
//                .ifPresentOrElse(System.out::println, () -> System.out.println("too bad"));
//        System.out.println(String.format("%-20s: %s", "gcd", Duration.between(start, start = Instant.now())));
        LongStream.rangeClosed(2L, END_INCLUSIVE)
                .boxed()
                .map(l -> Pair.of(l, (l * 1.0) / phi(l)))
//                .peek(System.out::println)
                .max(Comparator.comparing(Pair::getValue))
                .ifPresentOrElse(System.out::println, () -> System.out.println("too bad"));
        System.out.println(String.format("%-20s: %s", "phi", Duration.between(start, Instant.now())));

        List<Long> lst = getPrimes(p -> p < 100).collect(Collectors.toList());
        long res = 1;
        for(int idx = 0; idx < lst.size(); idx++) {
            res *= lst.get(idx);
            if(res > 1_000_000) {
                res /= lst.get(idx);
                break;
            }
        }
        System.out.println(res);
        System.out.println(primeFactors(res).boxed().collect(Collectors.toList()));
        System.out.println(primeFactors(510510L).boxed().collect(Collectors.toList()));
    }
}
