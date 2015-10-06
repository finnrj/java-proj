package euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Problem044 {

	public static void main(String[] args) {
		List<Long> pentagonNumbers = LongStream.rangeClosed(0, 1_000_000)
				.map(n -> (n * (3 * n - 1)) / 2).boxed().collect(Collectors.toList());
		Set<Long> collect = LongStream.rangeClosed(0, 1_000_000)
				.map(n -> (n * (3 * n - 1)) / 2).boxed().collect(Collectors.toSet());

		long index = 0;
		List<Long> differences = new ArrayList<Long>();
		differences.add(0L);
		List<Long> results = new ArrayList<Long>();
		while (index < 1_000_000) {
			index += 1;
			Long difference = 3 * index + 1;
			for (int i = 1; i < differences.size(); i++) {
				differences.set(i, differences.get(i) + difference);
			}
			differences.add(difference);
			for (int j = 1; j < differences.size(); j++) {
				if (collect.contains(differences.get(j))
						&& collect
								.contains(2 * pentagonNumbers.get(j) + differences.get(j))) {
					System.out.println(String.format("Pj: %d, Pk: %d, difference: %d", j,
							differences.size(), differences.get(j)));
					System.out
							.println(String.format(
									"%d - %d = %d",
									pentagonNumbers.get(differences.size()),
									pentagonNumbers.get(j),
									pentagonNumbers.get(differences.size())
											- pentagonNumbers.get(j)));
					results.add(differences.get(j));
				}
			}
		}
		Collections.sort(results);
		System.out.println("minimum difference: " + results.get(0));
	}
}
