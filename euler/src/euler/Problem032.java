package euler;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import euler.util.Permutations;

public class Problem032 {

	public static void main(String[] args) {
		Predicate<? super String> oneTimesFourPandigital = str -> Integer
				.valueOf(str.substring(0, 1)) * Integer.valueOf(str.substring(1, 5)) == Integer
				.valueOf(str.substring(5));
		Predicate<? super String> twoTimesThreePandigital = str -> Integer
				.valueOf(str.substring(0, 2)) * Integer.valueOf(str.substring(2, 5)) == Integer
				.valueOf(str.substring(5));
		// System.out.println(
		System.out.println(Permutations
				.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
				.limit(Permutations.factorial(8) * 7)
				.map(
						intStream -> intStream.map(String::valueOf).collect(
								Collectors.joining()))
				.filter(
						((Predicate<String>) oneTimesFourPandigital)
								.or(twoTimesThreePandigital)).peek(System.out::println)
				.map(str -> str.substring(5)).collect(Collectors.toSet()).stream()
				.mapToInt(Integer::valueOf).sum());
		;
	}
}
