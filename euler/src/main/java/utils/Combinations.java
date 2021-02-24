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

	public final static String SEPARATOR = ";";

	public static Map<Integer, List<String>> partitions (int maxSize) {
		TreeMap<Integer, List<String>> result = new TreeMap<>();
		result.put(1, Collections.singletonList("0"));
		for (int i = 2; i <= maxSize ; i++) {
			int newIndex = i - 1;
			String newIndexStr = String.valueOf(newIndex);
			List<String> newPartition = new ArrayList();
			for (String partition: result.get(newIndex)) {
				newPartition.add(partition + SEPARATOR + newIndexStr);
			}
			for (String partition: result.get(newIndex)) {
				StringBuilder builder = new StringBuilder();
				for (String partPartition: partition.split(SEPARATOR)) {
					int idx = partition.indexOf(partPartition);
					builder.append(partition, 0, idx);
					builder.append(partPartition + newIndexStr);
					builder.append(partition.substring(idx + partPartition.length()));
					newPartition.add(builder.toString());
					builder.delete(0,builder.length());
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

		partitions(11).entrySet()
		.forEach(e -> System.out.println(String.format("""
                                                       %d: %s
                                                       length: %d
                                                       """, e.getKey(), e.getValue(), e.getValue().size())));
	}
}
