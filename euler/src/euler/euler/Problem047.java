package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import utils.Utils;

public class Problem047 {

	private static boolean hasFourPrimeFactors(Long candidate) {
		return Utils.primeFactors(candidate).boxed().collect(Collectors.toSet())
				.size() == 4;
	}

	private static boolean has4PrimeFactors(Long candidate) {
		int c = 0;
		for (int p = 2; candidate != 1; p++) {
			if (candidate % p == 0) {
				c++;
				while (candidate % p == 0)
					candidate /= p;
			}
		}
		return c == 4;
	}

	public static void main(String[] args) {
		// 134043
		Instant start = Instant.now();
		// slowOne(start);
		// start = Instant.now();
		fastOne(start);
	}

	private static void fastOne(Instant start) {
		System.out
				.println(LongStream.iterate(84333, n -> n + 1)
						.peek(System.out::println).filter(n -> LongStream
								.rangeClosed(n, n + 3).allMatch(Problem047::has4PrimeFactors))
				.findFirst());
		System.out.println(Duration.between(Instant.now(), start));
	}

	private static void slowOne(Instant start) {
		System.out.println(LongStream.iterate(84333, n -> n + 1)
				.peek(System.out::println).filter(n -> LongStream.rangeClosed(n, n + 3)
						.allMatch(Problem047::hasFourPrimeFactors))
				.findFirst());
		System.out.println(Duration.between(Instant.now(), start));
	}
}
