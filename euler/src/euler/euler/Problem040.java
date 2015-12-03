package euler;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Problem040 {

	public static void main(String[] args) {
		String fractionString = LongStream.iterate(1, n -> n + 1).limit(1_085_185)
				.mapToObj(String::valueOf).collect(Collectors.joining());

		System.out.println(IntStream
				.iterate(1, n -> n * 10)
				.limit(7)
				.map(
						i -> Integer.valueOf(Character.getNumericValue(fractionString
								.charAt(i - 1)))).boxed().peek(System.out::println)
				.reduce((x, y) -> x * y));
	}
}
