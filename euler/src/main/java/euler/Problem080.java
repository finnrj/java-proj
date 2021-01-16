package euler;

import org.apache.commons.lang3.StringUtils;
import utils.Utils;

import java.math.BigInteger;

/**
 * </div>
 * <h2>Square root digital expansion</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=80"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 8th October 2004, 06:00 pm; Solved by 19269;<br>Difficulty rating: 20%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 80</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite without any repeating pattern at all.</p>
 * <p>The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.</p>
 * <p>For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem080 {


    /*Returns the square root of n.
    Note that the function */
    static float squareRoot(float n) {

        /*We are using n itself as
        initial approximation This
        can definitely be improved */
        float x = n;
        float y = 1;

        // e decides the accuracy level
        double e = 0.000000000000000001;
        while (x - y > e) {
            x = (x + y) / 2;
            y = n / x;
        }
        return x;
    }

    private static BigInteger determineX(BigInteger result, BigInteger currentValue) {
//        System.out.println("determineX with result: "+ result + " and current value: " + currentValue);
        if (result.compareTo(BigInteger.ZERO) == 0) {
            return currentValue.sqrt();
        }
        BigInteger doubledAndShifted = result.multiply(BigInteger.valueOf(20));
//        System.out.println("doubled and shifted = " + doubledAndShifted);
        BigInteger candidate = currentValue.divide(doubledAndShifted);
//        System.out.println("candidate = " + candidate);
        BigInteger plusOne = candidate.add(BigInteger.ONE);
        BigInteger plusTwo = candidate.add(BigInteger.TWO);
        BigInteger minusOne = candidate.add(BigInteger.valueOf(-1));
        if (getMultiply(doubledAndShifted, candidate).compareTo(currentValue) <= 0
                && getMultiply(doubledAndShifted, plusOne).compareTo(currentValue) > 0) {
            return candidate;
        }
        if (getMultiply(doubledAndShifted, minusOne).compareTo(currentValue) <= 0
                && getMultiply(doubledAndShifted, candidate).compareTo(currentValue) > 0) {
            return minusOne;
        }
        if (getMultiply(doubledAndShifted, plusOne).compareTo(currentValue) <= 0
                && getMultiply(doubledAndShifted, plusTwo).compareTo(currentValue) > 0) {
            return plusOne;
        }
        throw new IllegalArgumentException(
                String.format("no value found for: result: %s, currentvalue: %s, candidate: %s",
                        result, currentValue, candidate));
    }

    private static BigInteger getMultiply(BigInteger doubledAndShifted, BigInteger candidate) {
        return candidate.multiply(doubledAndShifted.add(candidate));
    }

    static String squareRootDecimals(BigInteger target) {
        String result = ""; // p
        BigInteger remainder = BigInteger.ZERO; //
        BigInteger currentValue = remainder.multiply(BigInteger.valueOf(100)).add(target); // c
        while (result.length() < 100) {
            BigInteger x = determineX(
                    new BigInteger(StringUtils.defaultIfBlank(result, "0")),
                    currentValue);
            BigInteger toSubtract = BigInteger.valueOf(-1).multiply(
                    getMultiply(new BigInteger(StringUtils.defaultIfBlank(result, "0")).multiply(BigInteger.valueOf(20)), x));
            result += x;
            remainder = currentValue.add(toSubtract);
            currentValue = remainder.multiply(BigInteger.valueOf(100));
        }
        return result;
    }


    public static void main(String[] args) {
        String wolfram = "1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727350138462309122970249248360";
        System.out.println(Math.sqrt(2.0));
        String decimals = squareRootDecimals(BigInteger.TWO);
        System.out.println(decimals);
        System.out.println(decimals.substring(0, 1) + "." + decimals.substring(1));
        System.out.println(wolfram.substring(0, 101));
        System.out.println(decimals.length());
        System.out.println(Utils.sumOfDigits(new BigInteger(decimals)));

        String sqrt_99 = "9.94987437106619954734479821001206005178" +
                "1265636768060791176046438349453927827131" +
                "54012653019738487195";
        String decimals99 = squareRootDecimals(BigInteger.valueOf(99));
        System.out.println(decimals99.substring(0,1) + "." + decimals99.substring(1));
        System.out.println(sqrt_99);
//        System.out.println(sqrt_99.substring(2).length());
//        int n = 99;
//        System.out.printf("Square root of "
//                + n + " is " + squareRoot(n));
    }

}
