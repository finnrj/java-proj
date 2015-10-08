package euler;

import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

public class Problem048 {

	public static void main(String[] args) throws IOException {
		String largeString = Stream
				.iterate(BigInteger.ONE, bi -> bi.add(BigInteger.ONE))
				.map(bi -> bi.pow(bi.intValue()))
				// .peek(System.out::println)
				.limit(1000).reduce(BigInteger::add).get().toString();
		System.out.println(largeString.substring(largeString.length() - 10));
	}
}
