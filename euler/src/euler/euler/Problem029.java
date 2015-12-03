package euler;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Problem029 {

	public static void main(String[] args) {
		System.out.println(LongStream
				.rangeClosed(2, 100)
				.boxed()
				.flatMap(
						i -> {
							return IntStream.rangeClosed(2, 100).boxed()
									.map(j -> BigInteger.valueOf(i).pow(j));
						}).collect(Collectors.toSet()).size());
	}
}
