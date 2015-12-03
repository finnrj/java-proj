package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.Permutations;
import utils.Utils;

public class Problem041 {
	private static List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,
			9);

	public static void main(String[] args) {
		Instant start = Instant.now();
		IntStream.of(4, 7).mapToObj(Problem041::findSolution)
				.forEach(System.out::println);
		System.out.println(Duration.between(start, start = Instant.now()));
		// 4231
		// 7652413
		for (long i = 4_321; i >= 1_234; i--) {
			if (Utils.isPrime(i) && Utils.isPandigital(i)) {
				System.out.println(i);
				break;
			}
		}
		for (long i = 7_654_321; i >= 1_234_567; i--) {
			if (Utils.isPrime(i) && Utils.isPandigital(i)) {
				System.out.println(i);
				break;
			}
		}
		System.out.println(Duration.between(start, start = Instant.now()));
	}

	private static String findSolution(int maxExclusive) {
		List<String> collect = Permutations.of(numbers.subList(0, maxExclusive))
				.map(
						stream -> stream.map(String::valueOf).collect(Collectors.joining()))
				.filter(Utils::isPrimeErastothenes).collect(Collectors.toList());
		Collections.reverse(collect);
		return collect.get(0);
	}
}
