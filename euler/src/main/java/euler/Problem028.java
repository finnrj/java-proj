package euler;

import java.util.stream.LongStream;

public class Problem028 {

	public static void main(String[] args) {
		System.out.println(LongStream.iterate(3, i -> i + 2).limit(500)
				.map(i -> 4 * i * i - 6 * (i - 1)).sum() + 1);
	}
}
