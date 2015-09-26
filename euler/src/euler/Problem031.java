package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem031 {
	final static List<Integer> COINS = Arrays.asList(200, 100, 50, 20, 10, 5, 2,
			1);

	private static List<Integer> fetchMostPossible(Integer max, Integer coin) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < max / coin; i++) {
			result.add(coin);
		}
		return result;
	}

	public static void main(String[] args) {
		Integer goal = 200;
		List<List<Integer>> partitions = new ArrayList<List<Integer>>();
		for (Integer coin : COINS) {
			if (coin <= goal) {
				partitions.add(new ArrayList<Integer>(Arrays.asList(coin)));
			}
		}
		for (Integer coin : COINS) {
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
		System.out.println(partitions.size());
		partitions.forEach(System.out::println);
	}
}
