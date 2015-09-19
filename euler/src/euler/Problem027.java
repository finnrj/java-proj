package euler;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import euler.util.Utils;

public class Problem027 {

	private static class Triple {
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
			return String.format("n**2 %d * n + %d constructs %d primes", a, b, n);
		}
	}

	public static void main(String[] args) throws IOException {
		LongStream positiveBs = Utils.getPrimes(p -> p < 1000);
		LongStream allBs = LongStream.concat(positiveBs,
				Utils.getPrimes(p -> p < 1000).map(p -> -1 * p));

		Stream<SimpleImmutableEntry<Long, IntStream>> bWithAs = allBs
				.mapToObj(p -> {
					IntStream as = IntStream.range(-999, 1000).filter(
							i -> Utils.isPrime(p + i + 1));
					return new AbstractMap.SimpleImmutableEntry<Long, IntStream>(p, as);
				});

		Stream<Object> result = bWithAs.flatMap(sie -> {
			return sie
					.getValue()
					.boxed()
					.map(
							a -> {
								long firstNonPrime = LongStream.range(0, 100)
										.map(i -> i * i + a * i + sie.getKey())
										.filter(r -> !Utils.isPrime(r)).findFirst().getAsLong();
								return new Triple(sie.getKey(), a, firstNonPrime);
							});
		});
		result.forEach(System.out::println);
	}
}
