package euler;

import java.util.Arrays;
import java.util.stream.Collectors;

import utils.Permutations;

public class Problem024 {

	public static void main(String[] args) {
		System.out
				.println(
						Permutations
								.permutation(999_999,
										Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
								.stream().map(String::valueOf).collect(Collectors.joining()));

		System.out.println();

		Permutations.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
				.skip(999_999).limit(1).map(integerStream -> integerStream
						.map(String::valueOf).collect(Collectors.joining()))
				.forEach(System.out::print);

	}
}