package euler;

import java.util.List;
import java.util.Set;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Problem045 {

	private static Set<Long> makeSequenceSet(Long start, Long endInclusive,
			LongUnaryOperator mapper) {
		return LongStream.rangeClosed(start, endInclusive).map(mapper).boxed()
				.collect(Collectors.toSet());
	}

	private static List<Long> makeSequenceList(Long start, Long endInclusive,
			LongUnaryOperator mapper) {
		return LongStream.rangeClosed(start, endInclusive).map(mapper).boxed()
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		LongUnaryOperator triangleNumbers = n -> (n * (n + 1)) / 2L;
		LongUnaryOperator pentagonalNumbers = n -> (n * (3 * n - 1)) / 2L;
		LongUnaryOperator hexagonalNumbers = n -> (n * (2 * n - 1));
		List<Long> triangles = makeSequenceList(1L, 100_000L, triangleNumbers);
		Set<Long> pentagonals = makeSequenceSet(165L, 100_000L, pentagonalNumbers);
		Set<Long> hexagonals = makeSequenceSet(143L, 100_000L, hexagonalNumbers);
		triangles.stream()
				.filter(n -> pentagonals.contains(n) && hexagonals.contains(n))
				.limit(2).forEach(System.out::println);
	}
}
