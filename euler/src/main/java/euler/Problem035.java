package euler;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import utils.Utils;

public class Problem035 {

	private static Long nextRotation(Long number) {
		String valueOf = String.valueOf(number);
		return Long.valueOf(valueOf.substring(1) + valueOf.charAt(0));
	}

	private static LongStream numberRotations(Long number) {
		String valueOf = String.valueOf(number);
		return LongStream.iterate(number, n -> nextRotation(n))
				.limit(valueOf.length());
	}

	public static void main(String[] args) {
		Integer maximum = 1_000_000;
		Set<Long> primesSet = Utils.getPrimes(p -> p < maximum)
				.collect(Collectors.toSet());
		System.out.println(Utils.getPrimes(p -> p < maximum)
				.filter(p -> !String.valueOf(p).contains("0")
						&& numberRotations(p).allMatch(i -> primesSet.contains(i)))
				.count());
	}
}
