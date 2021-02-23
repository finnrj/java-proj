package utils;

import java.util.*;

public class Combinations {

	public static <T> List<List<T>> combinations(Integer elementCount,
			List<T> target) {
		ArrayList<List<T>> accumulator = new ArrayList();
		if (elementCount >= target.size()) {
			accumulator.add(target);
			return accumulator;
		}
		return doFindCombinations(elementCount, target, accumulator);
	}

	private static <T> List<List<T>> doFindCombinations(Integer elementCount,
			List<T> target, List<List<T>> accumulator) {
		if (elementCount == 0) {
			accumulator.add(new ArrayList<T>());
		} else {
			for (int idx = 0; idx <= target.size() - elementCount; idx++) {
				for (List<T> list : doFindCombinations(elementCount - 1,
						target.subList(idx + 1, target.size()), accumulator)) {
					if (list.size() < elementCount) {
						list.add(0, target.get(idx));
					}
				}
			}
		}
		return accumulator;
	}

	public static Map<Integer, List<int[][]>> partitions (int maxSize) {
		TreeMap<Integer, List<int[][]>> result = new TreeMap<>();
		int[] inner = new int[]{0};
		int[][] outer = new int[][] {inner};
		result.put(1, Collections.singletonList(outer));
		for (int i = 2; i <= maxSize ; i++) {
			int newIndex = i - 1;
			List<int[][]> newPartition = new ArrayList();
			for (int[][] ia: result.get(newIndex)) {
				newPartition.add(Arrays.copyOf(ia, ia.length));
			}
			for (int[][] p: newPartition) {
				int[][] singleAddition = Arrays.copyOf(p, p.length + 1);
				singleAddition[p.length] = new int []{newIndex};
				newPartition.add(singleAddition);
				for (int[] innerP: p) {
					int[] extended = Arrays.copyOf(innerP, inner.length + 1);
					extended[p.length] = newIndex;
				}
			}
			result.put(i, newPartition);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(combinations(3, Arrays.asList(1, 2, 3, 4, 5)));
		System.out.println(combinations(5, Arrays.asList(1, 2, 3, 4, 5)));
		System.out.println(combinations(1, Arrays.asList(1, 2, 3, 4, 5)));
		System.out.println(combinations(3, Arrays.asList(1, 2, 3, 4, 5, 6, 7))
				.size());

		System.out.println(partitions(3));
	}
}
