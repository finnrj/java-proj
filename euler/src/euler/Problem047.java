package euler;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

import euler.util.Utils;

public class Problem047 {

	private static boolean hasFourPrimeFactors(Long candidate) {
		return Utils.primeFactors(candidate).boxed().collect(Collectors.toSet())
				.size() == 4;
	}

	public static void main(String[] args) {
		// 57493
		System.out.println(LongStream
				.iterate(57493, n -> n + 1)
				.peek(System.out::println)
				.filter(
						n -> LongStream.rangeClosed(n, n + 3).allMatch(
								Problem047::hasFourPrimeFactors)).findFirst());
	}
}
