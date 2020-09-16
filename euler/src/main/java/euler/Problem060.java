package euler;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import utils.Utils;

/**
 * </div>
 * <h2>Prime pair sets</h2> <div id="problem_info" class="info">
 * <h3>Problem 60</h3> <span>Published on Friday, 2nd January 2004, 06:00 pm;
 * Solved by 16168; Difficulty rating: 20%</span> </div>
 * <div class="problem_content" role="problem">
 * <p>
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes
 * and concatenating them in any order the result will always be prime. For
 * example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these
 * four primes, 792, represents the lowest sum for a set of four primes with
 * this property.
 * </p>
 * <p>
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 * </p>
 * </div> <br>
 * <br>
 */
public class Problem060 {

	// [23, 311, 677, 827]
	// [3, 7, 109, 673]
	// [13, 5197, 5701, 6733, 8389]
	public static void main(String[] args) {
		List<List<Integer>> bases = new ArrayList<>();
		List<Long> primes = Utils.getPrimes(p -> p < 100_000)
				.collect(Collectors.toList());
		int primesCount = primes.size();

		for (int i = 0; i < primesCount - 3; i++) {
			List<Integer> basis = new ArrayList<>(Collections.singletonList(i));
			while (!basis.isEmpty()) {
				Integer last = basis.remove(basis.size() - 1);
				findFromEndindex(primes, last, basis);
				bases.add(basis);
				if (basis.size() >= 5) {
					printLargeSet(primes, basis);
				}
			}
		}
		bases.sort(Comparator.comparingInt(List::size));
		bases.forEach(is -> System.out.println(primesFromIndices(primes, is)));
	}

	private static void printLargeSet(List<Long> primes, List<Integer> basis) {
		System.out.printf("size = %d, basis= %s%n", basis.size(),
				primesFromIndices(primes, basis));
	}

	private static List<Long> primesFromIndices(List<Long> primes,
			List<Integer> basis) {
		return basis.stream().map(primes::get)
				.collect(Collectors.toList());
	}

	private static void findFromEndindex(List<Long> primes, int endIndex,
			List<Integer> basis) {
		for (int j = endIndex + 1; j < primes.size(); j++) {
			final int k = j;
			if (basis.stream().allMatch(b -> concatenatedPrimes()
					.test(new Long[] { primes.get(b), primes.get(k) }))) {
				basis.add(j);
			}
		}
	}

	private static Predicate<? super Long[]> concatenatedPrimes() {
		return l2 -> Utils.isPrimeErastothenes(
				Long.parseLong(String.format("%d%d", l2[0], l2[1])))
				&& Utils.isPrimeErastothenes(
						Long.parseLong(String.format("%d%d", l2[1], l2[0])));
	}
}

// size = 5, basis= [3, 3119, 9887, 36263, 48731]
// size = 5, basis= [3, 5323, 10357, 29587, 31231]
// size = 5, basis= [3, 28277, 44111, 70241, 78509]
// size = 5, basis= [7, 61, 25939, 26893, 63601]
// size = 5, basis= [7, 61, 25939, 61417, 63601]
// size = 5, basis= [7, 61, 25939, 61471, 86959]
// size = 5, basis= [7, 1237, 2341, 12409, 18433]
// size = 5, basis= [7, 2467, 24847, 55213, 92593]
// size = 5, basis= [7, 3361, 30757, 49069, 57331]
// size = 5, basis= [7, 8893, 20899, 95071, 98179]
// size = 5, basis= [7, 12781, 42967, 51871, 64189]
// size = 5, basis= [7, 36607, 38083, 38791, 87181]
// size = 5, basis= [7, 39301, 48889, 68041, 81973]
// size = 5, basis= [11, 239, 11057, 21011, 62303]
// size = 5, basis= [13, 829, 9091, 17929, 72739]
// size = 5, basis= [13, 5197, 5701, 6733, 8389]
// size = 5, basis= [17, 2741, 8537, 40169, 47303]
// size = 5, basis= [19, 8929, 12577, 30427, 61129]
// size = 5, basis= [23, 1493, 16451, 23003, 33647]
// size = 5, basis= [29, 71, 21767, 69809, 71741]
// size = 5, basis= [43, 2749, 19963, 27943, 78511]
// size = 5, basis= [233, 239, 19319, 55733, 81563]
// size = 5, basis= [241, 7741, 54583, 63277, 71341]
// size = 5, basis= [241, 30637, 51241, 54181, 83791]
// size = 5, basis= [241, 36187, 54583, 63277, 71341]
// size = 5, basis= [281, 32309, 84437, 88427, 88469]
// size = 5, basis= [409, 3847, 16567, 68899, 83101]
// size = 5, basis= [467, 941, 2099, 19793, 25253]
// size = 5, basis= [467, 26357, 61343, 63737, 77153]
// size = 5, basis= [647, 7949, 45869, 57503, 81401]
// size = 5, basis= [733, 883, 991, 18493, 55621]
// size = 5, basis= [773, 10847, 17987, 45161, 90011]
// size = 5, basis= [797, 25469, 46049, 49277, 65657]
// size = 5, basis= [1151, 10271, 26981, 32573, 91079]
// size = 5, basis= [1303, 2953, 20101, 70753, 88813]
// size = 5, basis= [1453, 64333, 75133, 84961, 91969]
// size = 5, basis= [1481, 4211, 14717, 21227, 75653]
// size = 5, basis= [1571, 6983, 25583, 34739, 40289]
// size = 5, basis= [1993, 12823, 35911, 69691, 87697]
// size = 5, basis= [2287, 4483, 6793, 27823, 67723]
// size = 5, basis= [3541, 9187, 38167, 44257, 65677]
// size = 5, basis= [4729, 9181, 59497, 90907, 94441]
// size = 5, basis= [4999, 30307, 32911, 37561, 83047]
