package euler;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * It can be seen that the number, 125874, and its double, 251748, contain
 * exactly the same digits, but in a different order. Find the smallest positive
 * integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
public class Problem052 {

	public static void main(String[] args) {
		Integer searchedNumber = IntStream.iterate(300, n -> n + 3).boxed()
				.filter(n -> {
					Set<Integer> targetDigits = extractDigits(n);
					return IntStream.rangeClosed(1, 6).mapToObj(f -> extractDigits(f * n))
							.allMatch(ds -> ds.containsAll(targetDigits)
									&& ds.size() == targetDigits.size());
				}).findFirst().get();
		System.out.println(IntStream.rangeClosed(1, 6).map(f -> f * searchedNumber)
				.boxed().collect(Collectors.toList()));
	}

	private static Set<Integer> extractDigits(Integer n) {
		return n.toString().chars().boxed().collect(Collectors.toSet());
	}
}
