package euler;

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem005 {

	// recursive thingie stolen from project Euler
	public static boolean delbart(int candidate, int max) {
		if (max <= 1) {
			return true;
		}

		if (candidate % max == 0) {
			return delbart(candidate, max - 1);
		}
		return false;
	}

	public static long smallestDivisor(long candidate, long target) {
		if (candidate * candidate > target) {
			return target;
		}
		if (target % candidate == 0) {
			return candidate;
		}
		return smallestDivisor((candidate == 2) ? 3 : candidate + 2, target);
	}

	public static int maxmalMultiple(int prime, int max) {
		int result = prime;
		while (result * prime < max) {
			result *= prime;
		}
		return result;
	}

	public static void main(String[] args) {
		IntPredicate isPrime = n -> smallestDivisor(2, n) == n;
		IntStream primes = IntStream.rangeClosed(2, 20).filter(isPrime);
		System.out.println(primes.map(x -> maxmalMultiple(x, 20)).boxed()
				.collect(Collectors.reducing(1, (x, y) -> x * y)));

		int i = 20;
		while (!delbart(i, 20)) {
			i += 20;
		}
		System.out.println(i);

	}
}
