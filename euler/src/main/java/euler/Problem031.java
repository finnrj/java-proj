package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem031 {
	final static List<Integer> COINS_DESC = Arrays.asList(200, 100, 50, 20, 10,
			5, 2, 1);
	final static List<Integer> COINS_ASC = new ArrayList<Integer>(COINS_DESC);
	static {
		Collections.reverse(COINS_ASC);
	}

	private static List<Integer> fetchMostPossible(Integer max, Integer coin) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < max / coin; i++) {
			result.add(coin);
		}
		return result;
	}

	private static List<List<Integer>> primitiveFinnSolution(List<Integer> coins,
			Integer goal) {
		List<List<Integer>> partitions = new ArrayList<List<Integer>>();
		for (Integer coin : coins) {
			if (coin <= goal) {
				partitions.add(new ArrayList<Integer>(Arrays.asList(coin)));
			}
		}
		for (Integer coin : coins) {
			List<List<Integer>> newPartitions = new ArrayList<List<Integer>>();
			for (List<Integer> list : partitions) {
				int missing = goal - list.stream().reduce(Integer::sum).get();
				if (missing >= coin && list.get(list.size() - 1) >= coin) {
					List<Integer> fetched = fetchMostPossible(missing, coin);
					if (coin > 1) {
						while (!fetched.isEmpty()) {
							List<Integer> copy = new ArrayList<Integer>(list);
							copy.addAll(fetched);
							newPartitions.add(copy);
							fetched.remove(0);
						}
					} else {
						list.addAll(fetched);
					}
				}
			}
			partitions.addAll(newPartitions);
		}
		return partitions;
	}

	private static List<Integer> dynamicProgrammingSolutionFromProjectEuler(
			List<Integer> coins, Integer maxValue) {
		List<Integer> partitions = Stream.<Integer> iterate(1, i -> 0)
				.limit(maxValue + 1).collect(Collectors.toList());

		for (Integer coin : coins) {
			for (int i = coin; i < partitions.size(); i++) {
				partitions.set(i, partitions.get(i) + partitions.get(i - coin));
			}
		}
		return partitions;
	}

	public static void main(String[] args) {
		List<Integer> partitionsCount = dynamicProgrammingSolutionFromProjectEuler(
				COINS_ASC, 200);
		System.out.println(partitionsCount.get(partitionsCount.size() - 1));

		List<List<Integer>> partitions = primitiveFinnSolution(COINS_DESC, 200);
		System.out.println(partitions.size());
		// partitions.forEach(System.out::println);
	}

}
