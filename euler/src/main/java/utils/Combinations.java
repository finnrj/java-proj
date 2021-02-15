package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public static void main(String[] args) {
		System.out.println(combinations(3, Arrays.asList(1, 2, 3, 4, 5)));
		System.out.println(combinations(5, Arrays.asList(1, 2, 3, 4, 5)));
		System.out.println(combinations(1, Arrays.asList(1, 2, 3, 4, 5)));
		System.out.println(combinations(3, Arrays.asList(1, 2, 3, 4, 5, 6, 7))
				.size());
	}
}
