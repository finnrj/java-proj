package euler.util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.IntStream.Builder;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Utils {

	public static LongStream getPrimes(LongPredicate filterCondition)
			throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(".", "docs/primes1.txt"))) {
			return Arrays
					.stream(
							lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
									.collect(Collectors.joining(" ")).split("\\s+"))
					.mapToLong(Long::valueOf).filter(filterCondition);
		}
	}

	public static Boolean isPrime(long candidate) {
		LongPredicate identical = prime -> prime == Math.abs(candidate);
		try {
			return getPrimes(identical).count() == 1;
		} catch (IOException e) {
			return false;
		}
	}

	public static int[] readNumberTriangle(Path path) throws IOException {
		try (Stream<String> lines = Files.lines(path)) {
			return Arrays
					.stream(
							lines.map(l -> l.trim()).collect(Collectors.joining(" "))
									.split("\\s+")).mapToInt(Integer::valueOf).toArray();
			// .peek(i -> System.out.println(i))
		}
	}

	public static BigInteger factorial(int n) {
		BigInteger result = Stream
				.iterate(BigInteger.ONE, bi -> bi.add(BigInteger.ONE)).limit(n)
				.reduce(BigInteger.ONE, (bi1, bi2) -> bi1.multiply(bi2));
		return result;
	}

	public static IntStream factorize(Integer n) {
		Builder builder = IntStream.builder().add(1);
		int max = (int) Math.floor(Math.sqrt(n));
		for (int i = 2; i <= max; i++) {
			if (n % i == 0) {
				builder.add(i);
				int div = n / i;
				if (div != i) {
					builder.add(n / i);
				}
			}
		}
		return builder.build().sorted();
	}
}
