
package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import utils.Utils;

public class Problem051 {

	public static void main(String[] args) {
		Long maximum = 1_000_000L;
		int familySize = 8;

		Set<Long> candidateSet = new HashSet<Long>(
				Utils.getPrimes(p -> p > 10 && p < maximum).filter(hasRepeatedDigits())
						// .peek(System.out::println)
						.collect(Collectors.toList()));
		System.out
				.println(
						candidateSet.stream()
								.map(
										p -> makeFamily(p).stream().filter(candidateSet::contains)
												.collect(
														Collectors.toList()))
								.filter(l -> l.size() == familySize && l.stream()
										.allMatch(p -> p.toString()
												.length() == maximum.toString().length() - 1))
				.collect(Collectors.toSet()));

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
				ArrayList<Long> newList = new ArrayList<Long>();
				for (String replacement : "0123456789".split("")) {
					String newCandidate = candidate.toString()
							.replaceAll(entry.getKey().toString(), replacement);
					newList.add(new Long(newCandidate));
				}
				result = (newList.size() > result.size()) ? newList : result;
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