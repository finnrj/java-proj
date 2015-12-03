package euler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Problem017 {

	static Map<Integer, String> num2Length = new HashMap<Integer, String>();

	static final String AND = "and";
	static final String HUNDRED = "hundred";
	static final String THOUSAND = "thousand";

	static {
		num2Length.put(0, "");
		num2Length.put(1, "one");
		num2Length.put(2, "two");
		num2Length.put(3, "three");
		num2Length.put(4, "four");
		num2Length.put(5, "five");
		num2Length.put(6, "six");
		num2Length.put(7, "seven");
		num2Length.put(8, "eight");
		num2Length.put(9, "nine");
		num2Length.put(10, "ten");
		num2Length.put(11, "eleven");
		num2Length.put(12, "twelve");
		num2Length.put(13, "thirteen");
		num2Length.put(14, "fourteen");
		num2Length.put(15, "fifteen");
		num2Length.put(16, "sixteen");
		num2Length.put(17, "seventeen");
		num2Length.put(18, "eighteen");
		num2Length.put(19, "nineteen");
		num2Length.put(20, "twenty");
		num2Length.put(30, "thirty");
		num2Length.put(40, "forty");
		num2Length.put(50, "fifty");
		num2Length.put(60, "sixty");
		num2Length.put(70, "seventy");
		num2Length.put(80, "eighty");
		num2Length.put(90, "ninety");
		num2Length.put(1000, "onethousand");
	}

	public static void main(String[] args) {

		for (int j = 21; j < 100; j++) {
			int tenPart = j - (j % 10);
			int onePart = j % 10;
			num2Length.put(j, num2Length.get(tenPart) + num2Length.get(onePart));
		}

		for (int j = 100; j < 1000; j++) {
			int hundredPart = j / 100;
			int restPart = (j - hundredPart * 100);
			num2Length.put(j, num2Length.get(hundredPart) + HUNDRED
					+ ((j % 100 == 0) ? "" : AND + num2Length.get(restPart)));
		}

		int max = 1000;
		System.out.println(IntStream.iterate(1, i -> i + 1).limit(max)
				.mapToObj(num2Length::get).peek(s -> System.out.println(s))
				.mapToInt(String::length).sum());

		System.out.println(num2Length.get(342) + ", "
				+ num2Length.get(342).length());
		System.out.println(num2Length.get(115) + ", "
				+ num2Length.get(115).length());

	}
}
