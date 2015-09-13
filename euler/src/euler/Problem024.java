package euler;

import java.util.stream.Collectors;

import euler.util.Permutations;

public class Problem024 {

	public static void main(String[] args) {
		Permutations.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).skip(999_999).limit(1)
				.map(integerStream -> integerStream.map(String::valueOf).collect(Collectors.joining()))
				.forEach(System.out::print);
	}
}
