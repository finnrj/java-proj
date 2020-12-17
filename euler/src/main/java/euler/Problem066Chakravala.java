package euler;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static euler.Problem064Cheated.isSquare;
import static euler.Problem064Cheated.sqrt;

/**
 * </div>
 * <h2>Diophantine equation</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 66</h3>
 * <span>Published on Friday, 26th March 2004, 06:00 pm; Solved by 12357; Difficulty rating: 25%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Consider quadratic Diophantine equations of the form:</p>
 * <p style="text-align:center;"><i>x</i><sup>2</sup> – D<i>y</i><sup>2</sup> = 1</p>
 * <p>For example, when D=13, the minimal solution in <i>x</i> is 649<sup>2</sup> – 13×180<sup>2</sup> = 1.</p>
 * <p>It can be assumed that there are no solutions in positive integers when D is square.</p>
 * <p>By finding minimal solutions in <i>x</i> for D = {2, 3, 5, 6, 7}, we obtain the following:</p>
 * <p style="margin-left:20px;">3<sup>2</sup> – 2×2<sup>2</sup> = 1<br> 2<sup>2</sup> – 3×1<sup>2</sup> = 1<br><span style="color:#dd0000;font-weight:bold;">9</span><sup>2</sup> – 5×4<sup>2</sup> = 1<br> 5<sup>2</sup> – 6×2<sup>2</sup> = 1<br> 8<sup>2</sup> – 7×3<sup>2</sup> = 1</p>
 * <p>Hence, by considering minimal solutions in <i>x</i> for D ≤ 7, the largest <i>x</i> is obtained when D=5.</p>
 * <p>Find the value of D ≤ 1000 in minimal solutions of <i>x</i> for which the largest value of <i>x</i> is obtained.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem066Chakravala {
    public static record Triple (BigInteger x, BigInteger y, BigInteger D) {}

    static List<BigInteger> factors = Stream.iterate(BigInteger.TWO,
            bi -> bi.compareTo(BigInteger.valueOf(1_000)) < 0, bi -> bi.add(BigInteger.ONE))
            .filter(bi -> !isSquare(bi))
            .collect(Collectors.toList());

    static List<BigInteger> targets = Stream.iterate(BigInteger.TWO,
            bi -> bi.compareTo(BigInteger.valueOf(100_000)) < 0, bi -> bi.add(BigInteger.ONE))
            .map(value -> value.multiply(value))
            .collect(Collectors.toList());

    public Triple initialtriple(BigInteger D) {
        BigInteger min = targets.stream().map(bi -> bi.add(D.negate()).abs())
                .min(BigInteger::compareTo).orElse(BigInteger.ZERO);
        if (isSquare(min.add(D))) {
            return new Triple(sqrt(min.add(D)), BigInteger.ONE, min);
        }
        return new Triple(sqrt(D.add(min.negate())), BigInteger.ONE, min.negate());
    }

    static Set<BigInteger> targetSet = new HashSet<>(targets);

    public static void main(String[] args) {
    }
}
