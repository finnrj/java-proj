package euler;

import org.apache.commons.lang3.StringUtils;
import utils.Utils;

import java.math.BigInteger;
import java.util.stream.LongStream;

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
    private static BigInteger determineX(BigInteger result, BigInteger currentValue) {
        if (result.compareTo(BigInteger.ZERO) == 0) {
            return currentValue.sqrt();
        }
        BigInteger doubledAndShifted = result.multiply(BigInteger.valueOf(20));
        BigInteger candidate = currentValue.divide(doubledAndShifted).min(BigInteger.valueOf(9));
        BigInteger plusOne = candidate.add(BigInteger.ONE);

        while (getMultiply(doubledAndShifted, candidate).compareTo(currentValue) > 0) {
            plusOne = candidate;
            candidate = candidate.subtract(BigInteger.ONE);
        }

        while (getMultiply(doubledAndShifted, plusOne).compareTo(currentValue) <= 0) {
            candidate = plusOne;
            plusOne = plusOne.add(BigInteger.ONE);
        }

        if (getMultiply(doubledAndShifted, candidate).compareTo(currentValue) <= 0
                && getMultiply(doubledAndShifted, plusOne).compareTo(currentValue) > 0) {
            return candidate;
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
            BigInteger toSubtract =
                    getMultiply(
                            new BigInteger(StringUtils.defaultIfBlank(result, "0"))
                                    .multiply(BigInteger.valueOf(20)), x);
            result += x;
            remainder = currentValue.subtract(toSubtract);
            currentValue = remainder.multiply(BigInteger.valueOf(100));
        }
        return result;
    }


    public static void main(String[] args) {
//        String wolfram = "1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727350138462309122970249248360";
//        System.out.println(Math.sqrt(2.0));
//        String decimals = squareRootDecimals(BigInteger.TWO);
//        System.out.println(decimals);

        System.out.println(LongStream.range(1, 100).boxed()
                .map(BigInteger::valueOf)
                .filter(bi -> ! Problem064Cheated.isSquare(bi))
                .peek(System.out::println)
                .map(Problem080::squareRootDecimals)
                .map(str -> Utils.sumOfDigits(new BigInteger(str)))
                .mapToLong(BigInteger::longValue).sum());
    }
}
