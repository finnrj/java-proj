
package euler;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import utils.Utils;

public class Problem050 {

	public static void main(String[] args) {
		List<Long> primes = Utils.getPrimes(p -> p < 1_000_000)
				.collect(Collectors.toList());
		Set<Long> primesSet = new HashSet<Long>(primes);

		Map<Integer, Long> addititons = new HashMap<Integer, Long>();
		for (int i = 0; i < primes.size() - 1; i++) {
			long sum = primes.get(i);
			for (int j = i + 1; j < primes.size(); j++) {
				sum += primes.get(j);
				if (primesSet.contains(sum)) {
					addititons.put(j - i + 1, sum);
				}
			}
		}
		Instant start = Instant.now();
		System.out.println(addititons.entrySet().stream()
				.max((e1, e2) -> e1.getKey().compareTo(e2.getKey())).get());
		System.out.println(Duration.between(start, Instant.now()));
	}
}
