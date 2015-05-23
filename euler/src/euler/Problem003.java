package euler;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Problem003 {
	// stolen from project Euler
	public static long largestPrimeFactor(long number) {
		for (int i = 2; i < number; i++) {
			while (0 == number % i) {
				number /= i;
			}
		}
		return number;
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

	public static void main(String[] args) {
		long ceil = (long) Math.ceil(Math.sqrt(600851475143L));
		LongPredicate isPrime = n -> smallestDivisor(2, n) == n;
		LongPredicate divides = n -> 600851475143L % n == 0;
		System.out.println("\n"
				+ LongStream.rangeClosed(2, ceil).filter(isPrime.and(divides))
						.peek(n -> System.out.print(n + " ")).max().getAsLong());
	}
}
