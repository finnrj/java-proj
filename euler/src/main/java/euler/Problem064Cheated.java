package euler;

/**
 * </div>
 * <h2>Convergents of e</h2>
 * <div id="problem_info" class="info">
 * <h3>Problem 65</h3>
 * <span>Published on Friday, 12th March 2004, 06:00 pm; Solved by 19500; Difficulty rating: 15%</span>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>The square root of 2 can be written as an infinite continued fraction.</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>√2 = 1 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
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
 * <td>2 + ...</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>The infinite continued fraction can be written, √2 = [1;(2)], (2) indicates that 2 repeats <i>ad infinitum</i>. In a similar way, √23 = [4;(1,3,1,8)].</p>
 * <p>It turns out that the sequence of partial values of continued fractions for square roots provide the best rational approximations. Let us consider the convergents for √2.</p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 3/2</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 7/5</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 17/12</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tbody>
 * <tr>
 * <td>1 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>= 41/29</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;">
 * <div style="text-align:center;">
 * 1
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>&nbsp;</td>
 * <td>
 * <div style="text-align:center;">
 * 2
 * </div></td>
 * <td>&nbsp;</td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * <p>Hence the sequence of the first ten convergents for √2 are:</p>
 * <div class="note">
 * 1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
 * </div>
 * <p>What is most surprising is that the important mathematical constant,<br><i>e</i> = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2<i>k</i>,1, ...].</p>
 * <p>The first ten terms in the sequence of convergents for <i>e</i> are:</p>
 * <div class="note">
 * 2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
 * </div>
 * <p>The sum of digits in the numerator of the 10<sup>th</sup> convergent is 1+4+5+7=17.</p>
 * <p>Find the sum of digits in the numerator of the 100<sup>th</sup> convergent of the continued fraction for <i>e</i>.</p>
 * </div>
 * <br>
 * <br>
 */

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Problem064Cheated {
    /*
     * Solution to Project Euler problem 64
     * Copyright (c) Project Nayuki. All rights reserved.
     *
     * https://www.nayuki.io/page/project-euler-solutions
     * https://github.com/nayuki/Project-Euler-solutions
     */


    public static void main(String[] args) {

        System.out.println(new Problem064Cheated().run());
    }


    public String run() {
        int count = 0;
        for (int i = 1; i <= 10000; i++) {
            if (!isSquare(i) && getSqrtContinuedFractionPeriod(i) % 2 == 1)
                count++;
        }
        return Integer.toString(count);
    }


    // Returns the period of the continued fraction of sqrt(n)
    private static int getSqrtContinuedFractionPeriod(int n) {
        Map<QuadraticSurd, Integer> seen = new HashMap<>();
        QuadraticSurd val = new QuadraticSurd(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.valueOf(n));
        do {
            seen.put(val, seen.size());
            val = val.subtract(new QuadraticSurd(val.floor(), BigInteger.ZERO, BigInteger.ONE, val.d)).reciprocal();
        } while (!seen.containsKey(val));
        return seen.size() - seen.get(val);
    }


    // Represents (a + b * sqrt(d)) / c. d must not be a perfect square.
    private static final class QuadraticSurd {

        public final BigInteger a, b, c, d;


        public QuadraticSurd(BigInteger a, BigInteger b, BigInteger c, BigInteger d) {
            if (c.signum() == 0)
                throw new IllegalArgumentException();

            // Simplify
            if (c.signum() == -1) {
                a = a.negate();
                b = b.negate();
                c = c.negate();
            }
            BigInteger gcd = a.gcd(b).gcd(c);
            if (!gcd.equals(BigInteger.ONE)) {
                a = a.divide(gcd);
                b = b.divide(gcd);
                c = c.divide(gcd);
            }

            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }


        public QuadraticSurd subtract(QuadraticSurd other) {
            if (!d.equals(other.d))
                throw new IllegalArgumentException();
            return new QuadraticSurd(a.multiply(other.c).subtract(other.a.multiply(c)), b.multiply(other.c).subtract(other.b.multiply(c)), c.multiply(other.c), d);
        }


        public QuadraticSurd reciprocal() {
            return new QuadraticSurd(a.multiply(c).negate(), b.multiply(c), b.multiply(b).multiply(d).subtract(a.multiply(a)), d);
        }


        public BigInteger floor() {
            BigInteger temp = sqrt(b.multiply(b).multiply(d));
            if (b.signum() == -1)
                temp = temp.add(BigInteger.ONE).negate();
            temp = temp.add(a);
            if (temp.signum() == -1)
                temp = temp.subtract(c.subtract(BigInteger.ONE));
            return temp.divide(c);
        }


        public boolean equals(Object obj) {
            if (!(obj instanceof QuadraticSurd))
                return false;
            else {
                QuadraticSurd other = (QuadraticSurd) obj;
                return a.equals(other.a) && b.equals(other.b) && c.equals(other.c) && d.equals(other.d);
            }
        }


        public int hashCode() {
            return a.hashCode() + b.hashCode() + c.hashCode() + d.hashCode();
        }


        public String toString() {
            return String.format("(%d + %d*sqrt(%d)) / %d", a, b, d, c);
        }

    }

    public static int sqrt(int x) {
        if (x < 0)
            throw new IllegalArgumentException("Square root of negative number");
        int y = 0;
        for (int i = 1 << 15; i != 0; i >>>= 1) {
            y |= i;
            if (y > 46340 || y * y > x)
                y ^= i;
        }
        return y;
    }

    public static long sqrt(long x) {
        if (x < 0)
            throw new IllegalArgumentException("Square root of negative number");
        long y = 0;
        for (long i = 1L << 31; i != 0; i >>>= 1) {
            y |= i;
            if (y > 3037000499L || y * y > x)
                y ^= i;
        }
        return y;
    }


    // Returns floor(sqrt(x)), for x >= 0.
    public static BigInteger sqrt(BigInteger x) {
        if (x.signum() == -1)
            throw new IllegalArgumentException("Square root of negative number");
        BigInteger y = BigInteger.ZERO;
        for (int i = (x.bitLength() - 1) / 2; i >= 0; i--) {
            y = y.setBit(i);
            if (y.multiply(y).compareTo(x) > 0)
                y = y.clearBit(i);
        }
        return y;
    }

    public static boolean isSquare(int x) {
        if (x < 0)
            return false;
        int y = sqrt(x);
        return y * y == x;
    }

    public static boolean isSquare(BigInteger x) {
        if (x.signum() == -1)
            return false;
        BigInteger y = sqrt(x);
        return (y.multiply(y)).compareTo(x) == 0;
    }

}

