package euler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.Test;

public class
Problem054Test {

	private List<Integer> royalFlush = Arrays.asList(141, 131, 121, 111,
			101);
	private List<Integer> straightFlush = Arrays.asList(123, 113, 103, 93,
			83);
	private List<Integer> fourOfAKind = Arrays.asList(73, 71, 60, 72, 70);
	private List<Integer> fullHouse = Arrays.asList(73, 71, 60, 72, 63);
	private List<Integer> twoPairs = Arrays.asList(123, 113, 120, 93, 112);

	@Test
	public void testConvert() {
		String hands = "8C TS KC 9H 4S 7D 2S 5D 3S AC";
		List<List<Integer>> collect = Problem054.convert(hands)
				.collect(Collectors.toList());
		assertThat(collect.get(0),
				Matchers.containsInAnyOrder(80, 103, 130, 92, 43));
		assertThat(collect.get(1),
				Matchers.containsInAnyOrder(71, 23, 51, 33, 140));
	}

	@Test
	public void testIsRoyalFlush() {
		assertThat(Problem054.royalFlush.test(royalFlush), is(true));

		assertThat(
				Problem054.royalFlush.test(Arrays.asList(141, 131, 121, 110, 101)),
				is(false));

		assertThat(
				Problem054.royalFlush.test(Arrays.asList(131, 121, 111, 101, 91)),
				is(false));
	}

	@Test
	public void testStraightFlush() {
		assertThat(Problem054.straightFlush.test(straightFlush), is(true));

		assertThat(
				Problem054.royalFlush.test(Arrays.asList(123, 113, 103, 92, 83)),
				is(false));

		assertThat(Problem054.straightFlush
				.test(Arrays.asList(123, 113, 103, 92, 83)), is(false));

		assertThat(Problem054.straightFlush
				.test(Arrays.asList(123, 113, 103, 93, 73)), is(false));
	}

	@Test
	public void testFourOfAKind() {
		assertThat(Problem054.fourOfAKind.test(fourOfAKind), is(true));
		assertThat(
				Problem054.fourOfAKind.test(Arrays.asList(73, 71, 72, 80, 70)),
				is(true));
		assertThat(
				Problem054.fourOfAKind.test(Arrays.asList(73, 71, 60, 72, 61)),
				is(false));
	}

	@Test
	public void testFullHouse() {
		assertThat(Problem054.fullHouse.test(fullHouse), is(true));
		assertThat(
				Problem054.fullHouse.test(Arrays.asList(73, 51, 60, 72, 63)),
				is(false));
	}

	@Test
	public void testFlush() {
		assertThat(Problem054.flush.test(Arrays.asList(123, 113, 103, 93, 73)),
				is(true));
		assertThat(Problem054.flush.test(Arrays.asList(123, 112, 103, 93, 73)),
				is(false));
		assertThat(Problem054.flush.test(royalFlush), is(false));
		assertThat(Problem054.flush.test(straightFlush), is(false));
	}

	@Test
	public void testStraight() {
		assertThat(
				Problem054.straight.test(Arrays.asList(123, 113, 100, 93, 82)),
				is(true));
		assertThat(
				Problem054.straight.test(Arrays.asList(113, 113, 100, 93, 82)),
				is(false));
		assertThat(Problem054.straight.test(royalFlush), is(false));
		assertThat(Problem054.straight.test(straightFlush), is(false));
		assertThat(
				Problem054.straight.test(Arrays.asList(80, 100, 103, 111, 120)),
				is(false));
	}

	@Test
	public void testThreeOfAKind() {
		assertThat(Problem054.threeOfAKind
				.test(Arrays.asList(123, 113, 120, 93, 122)), is(true));
		assertThat(Problem054.threeOfAKind
				.test(Arrays.asList(123, 113, 120, 121, 122)), is(false));
		assertThat(
				Problem054.threeOfAKind.test(Arrays.asList(123, 70, 20, 121, 22)),
				is(false));
	}

	@Test
	public void testTwoPairs() {
		assertThat(Problem054.twoPairs.test(twoPairs), is(true));
		assertThat(
				Problem054.twoPairs.test(Arrays.asList(123, 113, 120, 121, 122)),
				is(false));
		assertThat(Problem054.twoPairs.test(Arrays.asList(73, 71, 60, 72, 63)),
				is(false));
		assertThat(Problem054.twoPairs.test(Arrays.asList(63, 71, 30, 72, 53)),
				is(false));
	}

	@Test
	public void testOnePair() {
		assertThat(
				Problem054.onePair.test(Arrays.asList(123, 113, 60, 93, 112)),
				is(true));
		assertThat(
				Problem054.onePair.test(Arrays.asList(123, 113, 120, 93, 112)),
				is(false));
		assertThat(
				Problem054.onePair.test(Arrays.asList(123, 113, 120, 93, 122)),
				is(false));
		assertThat(
				Problem054.onePair.test(Arrays.asList(123, 113, 100, 93, 82)),
				is(false));
	}

	@Test
	public void testScoreRoyalFlush() {
		List<List<Integer>> hands = Collections.singletonList(royalFlush);
		List<Integer> expects = Collections.singletonList(10411194);
		assertThat(Problem054.convert2Score(hands), is(expects));

	}

	@Test
	public void testScoreStraightFlush() {
		List<List<Integer>> hands = Collections.singletonList(straightFlush);
		List<Integer> expects = Collections.singletonList(9222808);
		assertThat(Problem054.convert2Score(hands), is(expects));

	}

	@Test
	public void testScoreFourOfAKind() {
		List<List<Integer>> hands = Collections.singletonList(fourOfAKind);
		List<Integer> expects = Collections.singletonList(7829366);
		assertThat(Problem054.convert2Score(hands), is(expects));
	}

	@Test
	public void testScoreFourOfAKindHighCard() {
		List<Integer> fourOfAKindHighCard = Arrays.asList(73, 71, 80, 72, 70);
		List<List<Integer>> hands = Collections.singletonList(fourOfAKindHighCard);
		List<Integer> expects = Collections.singletonList(7829368);
		assertThat(Problem054.convert2Score(hands), is(expects));
	}

	@Test
	public void testScoreFullHouse() {
		List<List<Integer>> hands = Collections.singletonList(fullHouse);
		List<Integer> expects = Collections.singletonList(6780774);
		assertThat(Problem054.convert2Score(hands), is(expects));

		List<Integer> fullHousePairHighest = Arrays.asList(73, 61, 60, 72, 63);
		hands = Collections.singletonList(fullHousePairHighest);
		expects = Collections.singletonList(6710903);
		assertThat(Problem054.convert2Score(hands), is(expects));
	}

	@Test
	public void testScoreTwoPairs() {
		List<List<Integer>> hands = Collections.singletonList(twoPairs);
		List<Integer> expects = Collections.singletonList(2935737);
		assertThat(Problem054.convert2Score(hands), is(expects));

		List<Integer> twoPairsHighCard = Arrays.asList(20, 32, 51, 23, 31);
		hands = Collections.singletonList(twoPairsHighCard);
		expects = Collections.singletonList(2306597);
		assertThat(Problem054.convert2Score(hands), is(expects));
	}

	@Test
	public void testScoreHigestCard() {
		List<Integer> highestCard = Arrays.asList(20, 32, 51, 103, 141);
		List<List<Integer>> hands = Collections.singletonList(highestCard);
		List<Integer> expects = Collections.singletonList(959794);
		assertThat(Problem054.convert2Score(hands), is(expects));
	}

}
