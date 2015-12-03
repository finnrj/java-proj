package euler;

import java.util.stream.LongStream;

public class Problem006 {

	public static long searcedDifference(long max) {
		double productOfSums = Math.pow((max * (max + 1)) / 2, 2);
		long sumOfProducts = LongStream.rangeClosed(1, max).map(x -> x * x).sum();
		return (long) (productOfSums - sumOfProducts);
	}

	public static void main(String[] args) {
		System.out.println(searcedDifference(10));
		System.out.println(searcedDifference(100));
	}
}
