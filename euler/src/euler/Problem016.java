package euler;

import java.math.BigInteger;

public class Problem016 {

	public static void main(String[] args) {
		BigInteger result = new BigInteger("2").pow(1000);
		BigInteger sum = BigInteger.ZERO;
		BigInteger[] divMod = new BigInteger[] { result, sum };
		while (divMod[0].max(BigInteger.ZERO) != BigInteger.ZERO) {
			divMod = divMod[0].divideAndRemainder(BigInteger.TEN);
			sum = sum.add(divMod[1]);
		}
		System.out.println(result);
		System.out.println(sum);

		System.out.println(new BigInteger("2").pow(1000).toString().chars()
				.map(b -> b - '0').sum());

		System.out.println(new BigInteger("2").pow(1000).toString().chars()
				.map(Character::getNumericValue).sum());

	}
}
