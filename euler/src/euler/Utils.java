package euler;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Utils {

	public static LongStream getPrimes(LongPredicate filterCondition)
			throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(".", "primes1.txt"))) {
			return Arrays
					.stream(
							lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
									.collect(Collectors.joining(" ")).split("\\s+"))
					.mapToLong(Long::valueOf).filter(filterCondition);
		}
	}

	public static BigInteger factorial(int n) {
		BigInteger result = Stream
				.iterate(BigInteger.ONE, bi -> bi.add(BigInteger.ONE)).limit(n)
				.reduce(BigInteger.ONE, (bi1, bi2) -> bi1.multiply(bi2));
		return result;
	}
}
