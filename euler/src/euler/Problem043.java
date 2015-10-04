package euler;

import java.util.stream.Collectors;

import euler.util.Permutations;

public class Problem043 {

	private static boolean hasDivisibilityProperty(String candidate) {
		return Long.parseLong(candidate.substring(1, 4)) % 2 == 0
				&& Long.parseLong(candidate.substring(2, 5)) % 3 == 0
				&& Long.parseLong(candidate.substring(3, 6)) % 5 == 0
				&& Long.parseLong(candidate.substring(4, 7)) % 7 == 0
				&& Long.parseLong(candidate.substring(5, 8)) % 11 == 0
				&& Long.parseLong(candidate.substring(6, 9)) % 13 == 0
				&& Long.parseLong(candidate.substring(7, candidate.length())) % 17 == 0;
	}

	public static void main(String[] args) {
		System.out
				.println(Permutations
						.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
						.skip(Permutations.factorial(9))
						.map(
								stream -> stream.map(String::valueOf).collect(
										Collectors.joining()))
						.filter(Problem043::hasDivisibilityProperty)
						.peek(System.out::println).mapToLong(Long::parseLong)
						.reduce(Long::sum).getAsLong());
	}
}
