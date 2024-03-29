package euler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Odd period square roots</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 64</h3>
 * <span>Published on Friday, 27th February 2004, 06:00 pm; Solved by 13949; Difficulty rating: 20%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>All square roots are periodic when written as continued fractions and can be written in the form:</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>√<var>N</var> = <var>a</var><sub>0</sub> +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td><var>a</var><sub>1</sub> +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td><var>a</var><sub>2</sub> +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td><var>a</var><sub>3</sub> + ...</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>For example, let us consider √23:</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>√23 = 4 + √23 — 4 = 4 +&nbsp;</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;= 4 +&nbsp;</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 1
 * <br>
 * <span style="border-top:1px solid #000;">√23—4</span>
 * </div></td>
 * <td>&nbsp;</td>
 * <td>1 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23 – 3</span>
 * <br>7
 * </div></td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>If we continue we would get the following expansion:</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>√23 = 4 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>1 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>3 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>1 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>8 + ...</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>The process can be summarised as follows:</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td><var>a</var><sub>0</sub> = 4,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 1
 * <br>
 * <span style="border-top:1px solid #000;">√23—4</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23+4</span>
 * <br>7
 * </div></td>
 * <td>&nbsp;=&nbsp;1 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23—3</span>
 * <br>7
 * </div></td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>1</sub> = 1,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 7
 * <br>
 * <span style="border-top:1px solid #000;">√23—3</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">7(√23+3)</span>
 * <br>14
 * </div></td>
 * <td>&nbsp;=&nbsp;3 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23—3</span>
 * <br>2
 * </div></td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>2</sub> = 3,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * <br>
 * <span style="border-top:1px solid #000;">√23—3</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">2(√23+3)</span>
 * <br>14
 * </div></td>
 * <td>&nbsp;=&nbsp;1 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23—4</span>
 * <br>7
 * </div></td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>3</sub> = 1,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 7
 * <br>
 * <span style="border-top:1px solid #000;">√23—4</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">7(√23+4)</span>
 * <br>7
 * </div></td>
 * <td>&nbsp;=&nbsp;8 +&nbsp;</td>
 * <td>√23—4</td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>4</sub> = 8,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 1
 * <br>
 * <span style="border-top:1px solid #000;">√23—4</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23+4</span>
 * <br>7
 * </div></td>
 * <td>&nbsp;=&nbsp;1 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23—3</span>
 * <br>7
 * </div></td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>5</sub> = 1,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 7
 * <br>
 * <span style="border-top:1px solid #000;">√23—3</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">7(√23+3)</span>
 * <br>14
 * </div></td>
 * <td>&nbsp;=&nbsp;3 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23—3</span>
 * <br>2
 * </div></td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>6</sub> = 3,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * <br>
 * <span style="border-top:1px solid #000;">√23—3</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">2(√23+3)</span>
 * <br>14
 * </div></td>
 * <td>&nbsp;=&nbsp;1 +&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">√23—4</span>
 * <br>7
 * </div></td>
 * </tr>
 * <tr>
 * <td><var>a</var><sub>7</sub> = 1,</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 7
 * <br>
 * <span style="border-top:1px solid #000;">√23—4</span>
 * </div></td>
 * <td>&nbsp;=&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * <span style="border-bottom:1px solid #000;">7(√23+4)</span>
 * <br>7
 * </div></td>
 * <td>&nbsp;=&nbsp;8 +&nbsp;</td>
 * <td>√23—4</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>It can be seen that the sequence is repeating. For conciseness, we use the notation √23 = [4;(1,3,1,8)], to indicate that the block (1,3,1,8) repeats indefinitely.</p>
 * <p>The first ten continued fraction representations of (irrational) square roots are:</p>
 * <p style="margin-left:20px;">√2=[1;(2)], period=1<br> √3=[1;(1,2)], period=2<br> √5=[2;(4)], period=1<br> √6=[2;(2,4)], period=2<br> √7=[2;(1,1,1,4)], period=4<br> √8=[2;(1,4)], period=2<br> √10=[3;(6)], period=1<br> √11=[3;(3,6)], period=2<br> √12= [3;(2,6)], period=2<br> √13=[3;(1,1,1,1,6)], period=5</p>
 * <p>Exactly four continued fractions, for <var>N</var> ≤ 13, have an odd period.</p>
 * <p>How many continued fractions for <var>N</var> ≤ 10000 have an odd period?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem064 {

    public static class Fraction {
        private long nominator, denominator;

        public Fraction(long nominator, long denominator) {
            this.nominator = nominator;
            this.denominator = denominator;
            reduce();
        }

        public static Fraction of( long n, long d) {
            return new Fraction(n,d);
        }

        public long getNominator() {
            return nominator;
        }

        public long getDenominator() {
            return denominator;
        }

        final private void reduce() {
            long a = nominator;
            long b = denominator;
            while (a != b) {
                if (a>b) {
                    a -= b;
                } else {
                    b -= a;
                }
            }
            this.nominator /= a;
            this.denominator /= b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fraction fraction = (Fraction) o;
            return nominator == fraction.nominator && denominator == fraction.denominator;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nominator, denominator);
        }

        @Override
        public String toString() {
            return String.format(" %d/%d", nominator, denominator);
        }

        public long integerPart() {
            return nominator/denominator;
        }

        public Fraction inverse() {
            return new Fraction(denominator, nominator);
        }

        public Pair<Long, Fraction> parts () {
            long ip = integerPart();
            if(ip == 0) {
                return Pair.of(Long.valueOf(ip), of(nominator,denominator));
            }
            return Pair.of(Long.valueOf(ip), new Fraction( nominator - ip * denominator,denominator));
        }

    }

    public static int extractNextBD(BigDecimal target, List<Integer> representation) {
        int period = bestPeriodBD(representation);
        while (period == 0) {
//            int intPart = target.round(new MathContext(0, RoundingMode.FLOOR)).intValue();
            BigDecimal intPart = target.setScale(0, RoundingMode.FLOOR);
            representation.add(intPart.intValue());
            BigDecimal diff = target.subtract(intPart).setScale(20, RoundingMode.HALF_DOWN);
//            if(diff.compareTo(BigDecimal.ONE) > 0 ) {
//                System.out.println("strange things happening: " + target + ", " + representation);
//            System.out.println(target.setScale(0, RoundingMode.FLOOR));
//                System.out.println("int part: " + intPart);
//                System.out.println("diff: " + diff);
//            }
            target = BigDecimal.ONE.divide(diff, new MathContext(20, RoundingMode.HALF_DOWN));

            period = bestPeriodBD(representation);
        }
        return period;
    }


    public static int bestPeriodBD(List<Integer> representation) {
        if (representation.size() < 20) {
            return 0;
        }
        if (representation.size() > 300) {
            System.out.println(representation);
            return -1;
        }

        String repString = representation.stream()
                .map(String::valueOf)
                .collect(Collectors.joining())
                .substring(1);
        int max = repString.length() / 2;
        for (int i = 1; i <= max; i++) {
            String candidatePeriod = repString.substring(0, i);
            String rest = repString;
            while (rest.startsWith(candidatePeriod)) {
                rest = StringUtils.removeStart(rest, candidatePeriod);
            }
            if (rest.isEmpty() || rest.length() < candidatePeriod.length()) {
                return candidatePeriod.length();
            }
        }
//        System.out.println(representation);
        return 0;
    }


    public static void main(String[] args) {
        long max = 101;
        long res = Stream.iterate(BigDecimal.valueOf(2), bd -> bd.compareTo(BigDecimal.valueOf(max)) < 0, bi -> bi.add(BigDecimal.ONE))
                .peek(bd -> System.out.print(bd + ": "))
                .map(bd -> bd.sqrt(MathContext.DECIMAL64))
                .filter(bd -> bd.stripTrailingZeros().scale() > 0)
                .mapToInt(bd -> extractNextBD(bd, new ArrayList<>()))
                .peek(System.out::println)
                .filter(i -> (i % 2) == 1)
                .count();

        System.out.println(String.format("for N <= %d the count of odd periods = %s", max, res));
    }

}
