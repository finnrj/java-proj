package euler;

import java.util.stream.LongStream;

public class Problem014 {

	public static void main(String[] args) {
		long max = 1;
		for (int j = 2; j < 1_000_000; j++) {
			long count = generateCollatzStream(j).filter(n -> n > 0)
			// .peek(n -> System.out.print(n + " -> "))
					.count();
			// System.out.println();
			// System.out.println(String.format("length = %d", count));
			if (max < count) {
				max = count;
				System.out.println(String
						.format("new max: %4d for number: %7d", max, j));
			}
		}
		System.out.println("FINISHED !!");
	}

	private static LongStream generateCollatzStream(Integer startValue) {
		return LongStream.iterate(startValue, n -> {
			if (n == 1)
				return -1;
			else if (n % 2 == 0)
				return n / 2;
			else
				return 3 * n + 1;
		}).limit(1000);
	}
}
