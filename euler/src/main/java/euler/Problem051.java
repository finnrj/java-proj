
package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import utils.Utils;

public class Problem051 {

	public static void main(String[] args) {
		Long maximum = 1_000_000L;

		List<Long> candidates = Utils.getPrimes(p -> p > 10 && p < maximum)
				.filter(hasRepeatedDigits()).peek(System.out::println)
				.collect(Collectors.toList());
		Set<Long> candidateSet = new HashSet<Long>(candidates);
	}

	private static Predicate<? super Long> hasRepeatedDigits() {
		return p -> {
			String lastDigitChopped = p.toString().substring(0,
					p.toString().length() - 1);
			return new HashSet<String>(Arrays.asList(lastDigitChopped.split("")))
					.size() != lastDigitChopped.length();
		};
	}

	public static Collection<Long> makeFamily(Long candidate) {
		Map<Integer, Long> digitByCount = candidate.toString().chars().boxed()
				.collect(Collectors.groupingBy(c -> Character.getNumericValue(c),
						Collectors.counting()));
		ArrayList<Long> result = new ArrayList<Long>();
		for (Map.Entry<Integer, Long> entry : digitByCount.entrySet()) {
			if (entry.getValue() > 1) {
				for (String replacement : "0123456789".split("")) {
					result.add(new Long(candidate.toString()
							.replaceAll(entry.getKey().toString(), replacement)));
				}
			}
		}
		return result;
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
