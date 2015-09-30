package euler;

import java.util.stream.Collectors;

import euler.util.Permutations;

public class Problem038 {

	private static boolean multipleWithTwo(String candidate) {
		Integer firstTwo = Integer.valueOf(candidate.substring(0, 2));
		return 2 * firstTwo == Integer.valueOf(candidate.substring(2, 5))
				&& 3 * firstTwo == Integer.valueOf(candidate.substring(5));
	}

	private static boolean multipleWithFour(String candidate) {
		Integer firstThree = Integer.valueOf(candidate.substring(0, 4));
		return 2 * firstThree == Integer.valueOf(candidate.substring(4));
	}

	public static void main(String[] args) {
		System.out.println(Permutations.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
				.map(s -> s.map(i -> i.toString()).collect(Collectors.joining()))
				.skip(8 * Permutations.factorial(8))
				.filter(perm -> multipleWithTwo(perm) || multipleWithFour(perm))
				.peek(System.out::println).max(String::compareTo).get());

	}
}
