package euler;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem030 {

	public static void main(String[] args) {
		Map<Integer, Integer> collect = IntStream.range(0, 10).boxed()
				.collect(Collectors.toMap(i -> i, i -> (int) Math.pow(1.0 * i, 5d)));
		Stream<Integer> filter = IntStream
				.range(10, 1_000_000)
				.boxed()
				.filter(
						i -> String.valueOf(i).chars()
								.map(s -> collect.get(Character.getNumericValue(s))).sum() == i);
		System.out.println("sum: "
				+ filter.peek(System.out::println).reduce((i1, i2) -> i1 + i2).get());
	}
}
