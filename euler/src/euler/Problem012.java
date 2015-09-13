package euler;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import euler.util.Utils;

public class Problem012 {
	// triangle number with more than 500 factors

	public static void main(String[] args) {
		IntStream factors = IntStream.of();
		long factorsCount = 0;
		int counter = 0;
		int sum = 0;
		long max = 0;
		while (factorsCount <= 500) {
			counter++;
			sum += counter;
			factors = Utils.factorize(sum);
			factorsCount = factors.count();
			if (factorsCount > max) {
				max = factorsCount;
				System.out.println("new max: " + max);
			}
		}
		System.out.println(sum + ", "
				+ Utils.factorize(sum).boxed().collect(Collectors.toList()));
	}
}
