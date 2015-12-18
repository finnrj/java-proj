package euler;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem019 {

	public static void main(String[] args) throws IOException {
		IntStream yearsFrom1901 = IntStream.iterate(1901, y -> y + 1);
		// yearsFrom1901.limit(100).peek(i -> System.out.println(i));
		int[] dayAdvances = yearsFrom1901.limit(100).map(y -> {
			if (y % 4 != 0)
				return 1;
			else if (y % 400 != 0)
				return 2;
			else
				return 1;
		}).toArray();

		ArrayList<Integer> leapAdvances = new ArrayList<Integer>();
		leapAdvances.add(dayAdvances[0]);
		for (int i = 1; i < 100; i++) {
			leapAdvances.add(dayAdvances[i] + leapAdvances.get(i - 1));
		}

		List<Integer> simpleAdvances = Stream.<Integer> iterate(1, n -> n + 1)
				.limit(100).collect(Collectors.toList());

		DayOfWeek jan01_1900 = DayOfWeek.MONDAY;
		DayOfWeek feb01_1900 = DayOfWeek.THURSDAY;

		DayOfWeek mar01_1900 = DayOfWeek.THURSDAY;
		DayOfWeek apr01_1900 = DayOfWeek.SUNDAY;
		DayOfWeek mai01_1900 = DayOfWeek.TUESDAY;
		DayOfWeek jun01_1900 = DayOfWeek.FRIDAY;
		DayOfWeek jul01_1900 = DayOfWeek.SUNDAY;
		DayOfWeek aug01_1900 = DayOfWeek.WEDNESDAY;
		DayOfWeek sep01_1900 = DayOfWeek.SATURDAY;
		DayOfWeek oct01_1900 = DayOfWeek.MONDAY;
		DayOfWeek nov01_1900 = DayOfWeek.THURSDAY;
		DayOfWeek dec01_1900 = DayOfWeek.SATURDAY;

		Stream<DayOfWeek> simple = Stream.of(jan01_1900, feb01_1900);
		Stream<DayOfWeek> leapStream = Stream.of(mar01_1900, apr01_1900,
				mai01_1900, jun01_1900, jul01_1900, aug01_1900, sep01_1900, oct01_1900,
				nov01_1900, dec01_1900);

		// findSundays(leapAdvances, jan01_1900);

		Long jan = Stream.<DayOfWeek> of(jan01_1900)
				.map(d -> findSundays(simpleAdvances, d)).reduce((a, b) -> a + b).get();
		Stream.<DayOfWeek> of(jan01_1900).forEach(d -> System.out.println(d));
		System.out.println(findSundays(simpleAdvances, jan01_1900));

		Long janAndFeb = simple.map(d -> findSundays(simpleAdvances, d))
				.reduce((a, b) -> a + b).get();
		Long mar2Dec = leapStream.map(d -> findSundays(leapAdvances, d))
				.reduce((a, b) -> a + b).get();
		System.out.println(jan + ", " + janAndFeb + ", " + mar2Dec);
		System.out.println("summed sundays the 1'th.: " + (janAndFeb + mar2Dec));
		// dayAdvances.forEach(i -> System.out.println(i));
		// for (DayOfWeek day : DayOfWeek.values()) {
		// System.out.println(day + ", " + day.getValue());
		// }
		// jan01.forEach(dow -> System.out.println(dow));
	}

	private static long findSundays(List<Integer> advances, DayOfWeek dow) {
		return advances.stream().map(da -> dow.plus(da))
				.filter(d -> d == DayOfWeek.SUNDAY).count();
	}
}
