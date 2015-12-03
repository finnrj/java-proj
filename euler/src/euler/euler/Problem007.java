package euler;

import java.util.function.LongSupplier;

public class Problem007 {

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
		new LongSupplier() {
			long seed = 2;

			@Override
			public long getAsLong() {
				long result = seed;
				seed = (seed != 2) ? seed + 2 : 3;
				return result;
			}
		};

		// LongPredicate isPrime = n -> smallestDivisor(2, n) == n;
		// long start = System.currentTimeMillis();
		// LongStream.generate(primeCandidates).filter(isPrime).limit(10_001)
		// .forEach(n -> System.out.println(n));
		// System.out.println(System.currentTimeMillis() - start);

		// try (Stream<String> lines = Files.lines(Paths.get(".", "primes1.txt"))) {
		// Arrays
		// .stream(
		// lines.map(l -> l.trim()).filter(l -> !l.isEmpty()).skip(1)
		// .collect(Collectors.joining(" ")).split("\\s+"))
		// .mapToInt(Integer::valueOf).filter(p -> p < 2_000_000)
		// .forEach(p -> System.out.println(p));
		// }
	}
}
