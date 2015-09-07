package euler.util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
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

	public static List<Integer> factorize(Integer n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int max = (n % 2 == 0) ? n / 2 : (n + 1) / 2;
		for (int i = 1; i <= max; i++) {
			if (n % i == 0) {
				result.add(i);
			}
		}
		return result;
	}

}
