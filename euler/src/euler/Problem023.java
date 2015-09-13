package euler;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import euler.util.Utils;

public class Problem023 {
	private static List<Integer> abundantNumbers;
	private static long max = 28123;
	static {
		abundantNumbers = Stream.iterate(1, n -> n + 1).limit(max)
				.filter(n -> Utils.factorize(n).sum() > n).collect(Collectors.toList());
	}

	private static Predicate<Integer> isAbundantPairSumPredicate = candidate -> abundantNumbers
			.stream().map(n -> abundantNumbers.contains(candidate - n))
			.reduce(Boolean::logicalOr).get();

	public static void main(String[] args) {
		List<Integer> nonAbundantPairSum = Stream.iterate(1, n -> n + 1).limit(max)
				.filter(isAbundantPairSumPredicate.negate())
				.collect(Collectors.toList());
		System.out.println(abundantNumbers);
		System.out.println("size:" + nonAbundantPairSum.size() + ", "
				+ nonAbundantPairSum);
		System.out
				.println(nonAbundantPairSum.stream().mapToInt(Integer::new).sum());
	}
}
