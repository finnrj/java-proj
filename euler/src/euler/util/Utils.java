package euler.util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.IntStream.Builder;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Utils {

	private static TreeSet<Long> primes;
	static {
		try (Stream<String> lines = Files.lines(Paths.get(".", "docs/primes1.txt"))) {
			primes = Arrays
					.stream(
							lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
									.collect(Collectors.joining(" ")).split("\\s+"))
					.mapToLong(Long::valueOf).boxed()
					.collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	public static LongStream getPrimesFromFile(LongPredicate filterCondition)
			throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(".", "docs/primes1.txt"))) {
			return Arrays
					.stream(
							lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
									.collect(Collectors.joining(" ")).split("\\s+"))
					.mapToLong(Long::valueOf).filter(filterCondition);
		}
	}

	public static Stream<Long> getPrimes(Predicate<Long> filterCondition) {
		return primes.stream().filter(filterCondition);
	}

	public static Boolean isPrime(long candidate) {
		Predicate<Long> identical = prime -> prime == Math.abs(candidate);
		return getPrimes(identical).count() == 1;
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

	public static void main(String[] args) throws IOException {
		LongPredicate pred = p -> true;
		Predicate<Long> pre = p -> true;

		Instant start = Instant.now();

		LongStream allBs = getPrimesFromFile(pred).limit(10_000);

		System.out.println("getPrimesFromFile"
				+ Duration.between(start, start = Instant.now()));

		Stream<Long> allBss = getPrimes(pre).limit(10_000);
		System.out.println("getPrimes"
				+ Duration.between(start, start = Instant.now()));

	}
}
