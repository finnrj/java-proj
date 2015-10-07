package euler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import euler.util.Utils;

public class Problem046 {

	public static void main(String[] args) throws IOException {
		Set<Long> doubleSquares = LongStream.rangeClosed(1, 10_000)
				.map(n -> 2 * n * n).boxed().collect(Collectors.toSet());
		List<Long> primes = Utils.getPrimesFromFile(p -> p < 10_000).boxed()
				.collect(Collectors.toList());
		System.out.println(LongStream
				.iterate(3, n -> n + 2)
				.filter(n -> !Utils.isPrime(n))
				.filter(
						n -> !primes.stream().filter(p -> p < n)
								.anyMatch(p -> doubleSquares.contains(n - p))).findFirst());
	}
}
