package euler;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import euler.util.Utils;

public class Problem027 {

	private static class Triple implements Comparable<Triple> {
		private Long b;
		private Integer a;
		private Long n;

		public Triple(Long b, Integer a, Long n) {
			this.b = b;
			this.a = a;
			this.n = n;
		}

		@Override
		public String toString() {
			return String
					.format(
							"n**2 %s%d * n + %d constructs %d primes\ncoefficient product: %d * %d = %d",
							(b < 0) ? "" : " + ", a, b, n, a, b, a * b);
		}

		@Override
		public int compareTo(Triple other) {
			return Long.compare(n, other.n);
		}
	}

	public static void main(String[] args) throws IOException {
		Instant start = Instant.now();

		LongStream allBs = fetchAllBs(1000);

		System.out.println("fetchAllBs                 "
				+ Duration.between(start, start = Instant.now()));

		Stream<SimpleImmutableEntry<Long, IntStream>> bWithAs = fetchAsForEachB(allBs);
		System.out.println("fetchAsForEachB            "
				+ Duration.between(start, start = Instant.now()));

		Stream<Triple> result = findPrimesGenerated(bWithAs);
		System.out.println("findPrimesGenerated       "
				+ Duration.between(start, start = Instant.now()));

		findMaximumPrimesGenrated(result);
		System.out.println("findMaximumPrimesGenrated "
				+ Duration.between(start, start = Instant.now()));
	}

	private static void findMaximumPrimesGenrated(Stream<Triple> result) {
		Optional<Triple> max = result
		// .peek(System.out::println)
				.max(Triple::compareTo);
		System.out.println(max);
	}

	private static Stream<Triple> findPrimesGenerated(
			Stream<SimpleImmutableEntry<Long, IntStream>> bWithAs) {
		Stream<Triple> result = bWithAs.flatMap(b_a -> {
			return b_a
					.getValue()
					.boxed()
					.map(
							a -> {
								SimpleImmutableEntry<Long, Long> firstNonPrime = LongStream
										.range(0, 75)
										.boxed()
										.map(
												i -> new AbstractMap.SimpleImmutableEntry<Long, Long>(
														i, i * i + a * i + b_a.getKey()))
										.filter(i_v -> !Utils.isPrime(i_v.getValue())).findFirst()
										.get();
								return new Triple(b_a.getKey(), a, firstNonPrime.getKey());
							});
		});
		return result.peek(System.out::println);
	}

	private static Stream<SimpleImmutableEntry<Long, IntStream>> fetchAsForEachB(
			LongStream allBs) {
		return allBs.mapToObj(b -> {
			IntStream as = IntStream.range(-999, 1000).filter(
					a -> Utils.isPrime(1 + a * 1 + b)//
							&& Utils.isPrime(4 + 2 * a + b)//
							&& Utils.isPrime(9 + 3 * a + b));
			return new AbstractMap.SimpleImmutableEntry<Long, IntStream>(b, as);
		});
	}

	private static LongStream fetchAllBs(long max) throws IOException {
		return LongStream.concat(Utils.getPrimes(p -> p < max),
				Utils.getPrimes(p -> p < max).map(p -> -1 * p));
	}
}
