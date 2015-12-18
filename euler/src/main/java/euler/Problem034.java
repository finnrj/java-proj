package euler;

import java.util.ArrayList;
import java.util.List;

import utils.Permutations;

public class Problem034 {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(String.format("%d:%6d", i, Permutations.factorial(i)));
		}
		List<Integer> collector = new ArrayList<Integer>();
		for (int i = 11; i < 1_000_000; i++) {
			if (i % 10 == 0) {
				continue;
			}
			Long factorialSum = String.valueOf(i).chars()
					.map(Character::getNumericValue).boxed().map(Permutations::factorial)
					.reduce(Long::sum).get();
			if (factorialSum.compareTo(Long.valueOf(i)) == 0) {
				// System.out.println(i);
				collector.add(i);
			}
		}
		System.out.println("sum: " + collector.stream().reduce(Integer::sum).get());
	}
}
