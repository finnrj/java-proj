package euler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import euler.util.Utils;

public class Problem049 {

	private static String makeKey(Long target) {
		return target.toString().chars().sorted()
				.map(ch -> Character.getNumericValue(ch)).boxed().map(String::valueOf)
				.collect(Collectors.joining());
	}

	private static void duplicatedDistances(List<Long> candidates) {
		for (int i = 0; i < candidates.size() - 2; i++) {
			for (int j = i + 1; j < candidates.size() - 1; j++) {
				long searchedFor = 2 * candidates.get(j) - candidates.get(i);
				if (candidates.contains(searchedFor)) {
					System.out.println(candidates);
					System.out.println(candidates.get(i) + "" + candidates.get(j) + ""
							+ searchedFor);
				}
			}
		}
	}

	public static void main(String[] args) {
		Stream<Long> fourDigitPrimes = Utils
				.getPrimes(p -> p.toString().length() == 4);
		// System.out.println(Arrays.toString("1487".split("")));
		fourDigitPrimes.collect(Collectors.groupingBy(Problem049::makeKey))
				.values().stream().filter(lst -> lst.size() >= 3)
				.forEach(lst -> duplicatedDistances(lst));
	}
}
