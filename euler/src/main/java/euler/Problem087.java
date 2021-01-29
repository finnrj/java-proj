package euler;

import utils.Utils;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * </div>
 * <h2>Prime power triples</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=87"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 21st January 2005, 06:00 pm; Solved by 20307;<br>Difficulty rating: 20%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 87</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>The smallest number expressible as the sum of a prime square, prime cube, and prime fourth power is 28. In fact, there are exactly four numbers below fifty that can be expressed in such a way:</p>
 * <p class="margin_left">28 = 2<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup><br> 33 = 3<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup><br> 49 = 5<sup>2</sup> + 2<sup>3</sup> + 2<sup>4</sup><br> 47 = 2<sup>2</sup> + 3<sup>3</sup> + 2<sup>4</sup></p>
 * <p>How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem087 {

    public static void main(String[] args) {
        Instant start = Instant.now();
        long MAX = 50_000_000;
        List<BigInteger> fourth = Utils.getPrimes(p -> p < 84)
                .map(BigInteger::valueOf)
                .map(p -> p.pow(4))
                .collect(Collectors.toList());
        List<BigInteger> cube = Utils.getPrimes(p -> p < 368)
                .map(BigInteger::valueOf)
                .map(p -> p.pow(3))
                .collect(Collectors.toList());
        List<BigInteger> square = Utils.getPrimes(p -> p < 7072)
                .map(BigInteger::valueOf)
                .map(p -> p.pow(2))
                .collect(Collectors.toList());
        System.out.println("start counting " + Duration.between(start, start = Instant.now()));
        System.out.println(String.format("sizes: fourth, cube, square: %d, %d, %d", fourth.size(), cube.size(), square.size()));
        System.out.println(String.format("min and max fourth: %d, %d", fourth.get(0), fourth.get(fourth.size() - 1)));
        System.out.println(String.format("min and max cube: %d, %d", cube.get(0), cube.get(cube.size() - 1)));
        System.out.println(String.format("min and max square: %d, %d", square.get(0), square.get(square.size() - 1)));

        Set<BigInteger> sums = new HashSet();
        for (BigInteger four : fourth) {
            BigInteger diff = BigInteger.valueOf(MAX).subtract(four);
            for (int i = 0; i < cube.size() && cube.get(i).compareTo(diff) < 0; i++) {
                BigInteger diffDiff = diff.subtract(cube.get(i));
                for (int j = 0; j < square.size() && square.get(j).compareTo(diffDiff) < 0; j++) {
                    sums.add(four.add(cube.get(i)).add(square.get(j)));
                }
            }
        }
        System.out.println("it took " + Duration.between(start, Instant.now()));
        System.out.println(String.format("found %d sums", sums.size()));
        //        found 1097343 sums
    }
}
