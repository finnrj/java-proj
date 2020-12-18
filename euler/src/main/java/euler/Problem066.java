package euler;

import org.apache.commons.lang3.tuple.Pair;

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
public class Problem066 {
    // solved using the Chakravala Method https://en.wikipedia.org/wiki/Chakravala_method
    public static record Triple(BigInteger x, BigInteger y, BigInteger k) {}

    static List<BigInteger> factors = Stream.iterate(BigInteger.TWO,
            bi -> bi.compareTo(BigInteger.valueOf(1_000)) < 0, bi -> bi.add(BigInteger.ONE))
            .filter(bi -> !isSquare(bi))
            .collect(Collectors.toList());

    static List<BigInteger> targets = Stream.iterate(BigInteger.TWO,
            bi -> bi.compareTo(BigInteger.valueOf(100_000)) < 0, bi -> bi.add(BigInteger.ONE))
            .map(value -> value.multiply(value))
            .collect(Collectors.toList());

    public Triple initialTriple(BigInteger D) {
        BigInteger min = findMinTarget(targets, D, BigInteger.ONE);
        return new Triple(sqrt(min), BigInteger.ONE, min.add(D.negate()));
    }

    public Triple solve(BigInteger D) {
        return doSolveRecursive(D, initialTriple(D));
    }

    private Triple doSolveRecursive(BigInteger D, Triple target) {
        BigInteger x = target.x();
        BigInteger y = target.y();
        BigInteger k = target.k();
        if (k.compareTo(BigInteger.ONE) == 0) {
            return target;
        }

        BigInteger below = Stream.iterate(BigInteger.ONE, bi -> bi.add(BigInteger.ONE))
                .filter(bi -> x.add(bi.multiply(y)).divideAndRemainder(k)[1].compareTo(BigInteger.ZERO) == 0)
                .takeWhile(bi -> bi.multiply(bi).compareTo(D) <= 0)
                .max(BigInteger::compareTo).orElse(BigInteger.TWO);
        BigInteger above = below.add(k.abs());
        List<BigInteger> belowAndAbove = Arrays.asList(below.multiply(below), above.multiply(above));
        BigInteger m = sqrt(findMinTarget(belowAndAbove, D, k.abs()));
        BigInteger newX = x.multiply(m).add(D.multiply(y)).divide(k.abs());
        BigInteger newY = x.add(m.multiply(y)).divide(k.abs());
        BigInteger newK = m.multiply(m).add(D.negate()).divide(k);
        return doSolveRecursive(D, new Triple(newX, newY, newK));
    }

    private BigInteger findMinTarget(List<BigInteger> bis, BigInteger D, BigInteger toAdd) {
        BigInteger below = bis.stream()
                .takeWhile(bi -> bi.compareTo(D) <= 0)
                .max(BigInteger::compareTo).orElse(bis.get(0));
        BigInteger sqrtAbove = sqrt(below).add(toAdd);
        BigInteger above = sqrtAbove.multiply(sqrtAbove);
        if (above.add(D.negate()).compareTo(D.add(below.negate())) < 0) {
            return above;
        }
        return below;
    }

    public static void main(String[] args) {
        Problem066 solver = new Problem066();
        Pair<BigInteger, BigInteger> res = factors.stream()
                .map(t -> Pair.of(t, solver.solve(t).x()))
                .max(Map.Entry.comparingByValue())
                .orElseThrow(IllegalArgumentException::new);
        System.out.println("(D, maximal minimal solution in x): " + res);
    }
}
