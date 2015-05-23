package euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem008 {
	public static void main(String[] args) {
		String targetNumber = "73167176531330624919225119674426574742355349194934"
				+ "96983520312774506326239578318016984801869478851843"
				+ "85861560789112949495459501737958331952853208805511"
				+ "12540698747158523863050715693290963295227443043557"
				+ "66896648950445244523161731856403098711121722383113"
				+ "62229893423380308135336276614282806444486645238749"
				+ "30358907296290491560440772390713810515859307960866"
				+ "70172427121883998797908792274921901699720888093776"
				+ "65727333001053367881220235421809751254540594752243"
				+ "52584907711670556013604839586446706324415722155397"
				+ "53697817977846174064955149290862569321978468622482"
				+ "83972241375657056057490261407972968652414535100474"
				+ "82166370484403199890008895243450658541227588666881"
				+ "16427171479924442928230863465674813919123162824586"
				+ "17866458359124566529476545682848912883142607690042"
				+ "24219022671055626321111109370544217506941658960408"
				+ "07198403850962455444362981230987879927244284909188"
				+ "84580156166097919133875499200524063689912560717606"
				+ "05886116467109405077541002256983155200055935729725"
				+ "71636269561882670428252483600823257530420752963450";

		// 7316717653133
		// 6249192251196744265747423553491949349698352
		// 6326239578318
		// 18694788518438586156
		// 7891129494954595
		// 17379583319528532
		// 698747158523863
		// 435576689664895
		// 4452445231617318564
		// 987111217223831136222989342338
		// 81353362766142828
		// 64444866452387493
		// 1724271218839987979
		// 9377665727333
		// 594752243525849
		// 632441572215539753697817977846174
		// 86256932197846862248283972241375657
		// 79729686524145351
		// 6585412275886668811642717147992444292823
		// 863465674813919123162824586178664583591245665294765456828489128831426
		// 96245544436298123
		// 9878799272442849
		// 979191338754992
		// 559357297257163626956188267

		System.out.println(targetNumber);
		List<Integer> numbers = Arrays.asList(targetNumber.split("")).stream()
				.mapToInt(s -> Integer.parseInt(s)).boxed()
				.collect(Collectors.toList());
		// .peek(n -> System.out.print(n + ", "));
		// System.out.println("\n" + numbers);

		int maxIndex = 12;
		List<String> numbers2 = Arrays.asList(targetNumber.split("0")).stream()
				.filter(s -> s.length() > maxIndex).collect(Collectors.toList());// .collect(Collectors.joining());

		for (String string : numbers2) {
			System.out.println(string);
		}

		// .mapToInt(Integer::parseInt).boxed(); 2091059712, 2143260000, 2091059712,
		// 2091059712, 23514624000

		int maxUntillNow = 0;
		for (int i = maxIndex; i < numbers.size(); i++) {
			int sum = numbers.subList(i - maxIndex, i + 1).stream()
					.mapToInt(Integer::intValue).reduce(1, (a, b) -> a * b);
			if (sum > maxUntillNow) {
				maxUntillNow = sum;
			}
		}
		System.out.println("numbers: " + maxUntillNow);

		List<Integer> maxs = new ArrayList<Integer>();
		for (String string : numbers2) {
			List<Integer> nums = Arrays.asList(string.split("")).stream()
					.mapToInt(s -> Integer.parseInt(s)).boxed()
					.collect(Collectors.toList());
			maxUntillNow = 0;
			for (int i = maxIndex; i < nums.size(); i++) {
				int sum = nums.subList(i - maxIndex, i + 1).stream()
						.mapToInt(Integer::intValue).reduce(1, (a, b) -> a * b);
				if (sum > maxUntillNow) {
					maxUntillNow = sum;
					for (Integer integer : nums.subList(i - maxIndex, i + 1)) {
						System.out.print(integer);
					}
					System.out.print(" = " + maxUntillNow);
					System.out.println();
				}
			}
			System.out.println("numbers3: " + maxUntillNow);
			maxs.add(maxUntillNow);
		}

		new BigInteger(targetNumber);

		System.out.println("\nMaxs:");

		for (Integer integer : maxs.stream().sorted().collect(Collectors.toList())) {
			System.out.println(integer);
		}

		// System.out.println(maxs.stream().sorted()); solution= ('5576689664895',
		// 23514624000)

	}
}
