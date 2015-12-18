
package euler;

import java.util.stream.IntStream;

import utils.Utils;

public class Problem036 {

	private static String binaryString(int target) {
		if (target == 0) {
			return "0";
		}
		String result = "";
		while (target > 0) {
			result = target % 2 + result;
			target = target / 2;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out
				.println(IntStream.range(1, 1_000_000)
						.filter(i -> Utils.isPalindrome(i)
								&& Utils.isPalindrome(binaryString(i)))
				.peek(i -> System.out
						.println(String.format("% 5d = %s", i, binaryString(i)))).sum());
	}
}
