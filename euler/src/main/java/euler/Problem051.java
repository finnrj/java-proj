
package euler;

import java.util.Arrays;
import java.util.HashSet;

import utils.Utils;

public class Problem051 {

	public static void main(String[] args) {
		Long maximum = 1_000_000L;

		Utils.getPrimes(p -> p > 10 && p < maximum).filter(p -> {
			String endChopped = p.toString().substring(0, p.toString().length() - 1);
			return new HashSet<String>(Arrays.asList(endChopped.split("")))
					.size() != endChopped.length();
		}).peek(System.out::println).count();
	}
}

// Stream<Long> primes = Utils.getPrimes(p -> p < maximum);
// primes.filter(p -> {
// Map<Integer, Long> countMap = p.toString()
// .subSequence(0, p.toString().length() - 1).chars().boxed()
// .collect(Collectors.groupingBy(Function.identity(),
// Collectors.counting()));
// return countMap.size() != p.toString().length() - 1;
// }).peek(System.out::println).count();
