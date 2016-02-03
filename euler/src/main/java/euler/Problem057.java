package euler;

import java.math.BigInteger;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Square root convergents</h2> <div id="problem_info" class="info">
 * <h3>Problem 57</h3> <span>Published on Friday, 21st November 2003, 06:00 pm;
 * Solved by 26468; Difficulty rating: 5%</span> </div>
 * <div class="problem_content" role="problem">
 * <p>
 * It is possible to show that the square root of two can be expressed as an
 * infinite continued fraction.
 * </p>
 * <p style="text-align:center;">
 * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
 * </p>
 * <p>
 * By expanding this for the first four iterations, we get:
 * </p>
 * <p>
 * 1 + 1/2 = 3/2 = 1.5<br>
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4<br>
 * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...<br>
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...<br>
 * </p>
 * <p>
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth
 * expansion, 1393/985, is the first example where the number of digits in the
 * numerator exceeds the number of digits in the denominator.
 * </p>
 * <p>
 * In the first one-thousand expansions, how many fractions contain a numerator
 * with more digits than denominator?
 * </p>
 * </div> <br>
 * <br>
 */
public class Problem057 {

	public static void main(String[] args) {
		System.out.println(
				Stream.iterate(new SimpleImmutableEntry<BigInteger, BigInteger>(
						BigInteger.ONE, BigInteger.ONE), e -> {
							BigInteger denominator = e.getKey().add(e.getValue());
							BigInteger nominator = denominator.add(e.getValue());
							return new SimpleImmutableEntry<BigInteger, BigInteger>(nominator,
									denominator);
						}).limit(1001).filter(e -> e.getKey().toString().length() > e
								.getValue().toString().length())
						.count());
	}
}
