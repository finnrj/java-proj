package euler;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Problem020 {

	public static void main(String[] args) {
		System.out.println(Stream.iterate(BigInteger.ONE,
				bi -> bi.add(BigInteger.ONE)).limit(100)
				.reduce(BigInteger.ONE, (b1, b2) -> b1.multiply(b2)).toString().chars()
				.map(Character::getNumericValue).sum());

	}
}
