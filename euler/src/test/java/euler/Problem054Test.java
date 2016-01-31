package euler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.google.common.collect.Lists;

public class Problem054Test {

	private List<Integer> royalFlush = Lists.newArrayList(141, 131, 121, 111,
			101);
	private List<Integer> straightFlush = Lists.newArrayList(123, 113, 103, 93,
			83);
	private List<Integer> fourOfAKind = Lists.newArrayList(73, 71, 60, 72, 70);

	@Test
	public void testConvert() throws Exception {
		String hands = "8C TS KC 9H 4S 7D 2S 5D 3S AC";
		List<List<Integer>> collect = Problem054.convert(hands)
				.collect(Collectors.toList());
		assertThat(collect.get(0),
				Matchers.containsInAnyOrder(80, 103, 130, 92, 43));
		assertThat(collect.get(1),
				Matchers.containsInAnyOrder(71, 23, 51, 33, 140));
	}

	@Test
	public void testIsRoyalFlush() throws Exception {
		assertThat(Problem054.royalFlush.test(royalFlush), is(true));

		assertThat(
				Problem054.royalFlush.test(Lists.newArrayList(141, 131, 121, 110, 101)),
				is(false));

		assertThat(
				Problem054.royalFlush.test(Lists.newArrayList(131, 121, 111, 101, 91)),
				is(false));
	}

	@Test
	public void testStraightFlush() throws Exception {
		assertThat(Problem054.straightFlush.test(straightFlush), is(true));

		assertThat(
				Problem054.royalFlush.test(Lists.newArrayList(123, 113, 103, 92, 83)),
				is(false));

		assertThat(Problem054.straightFlush
				.test(Lists.newArrayList(123, 113, 103, 92, 83)), is(false));

		assertThat(Problem054.straightFlush
				.test(Lists.newArrayList(123, 113, 103, 93, 73)), is(false));
	}

	@Test
	public void testFourOfAKind() throws Exception {
		assertThat(Problem054.fourOfAKind.test(fourOfAKind), is(true));
		assertThat(
				Problem054.fourOfAKind.test(Lists.newArrayList(73, 71, 72, 80, 70)),
				is(true));
		assertThat(
				Problem054.fourOfAKind.test(Lists.newArrayList(73, 71, 60, 72, 61)),
				is(false));
	}

	@Test
	public void testFullHouse() throws Exception {
		assertThat(
				Problem054.fullHouse.test(Lists.newArrayList(73, 71, 60, 72, 63)),
				is(true));
		assertThat(
				Problem054.fullHouse.test(Lists.newArrayList(73, 51, 60, 72, 63)),
				is(false));
	}

	@Test
	public void testFlush() throws Exception {
		assertThat(Problem054.flush.test(Lists.newArrayList(123, 113, 103, 93, 73)),
				is(true));
		assertThat(Problem054.flush.test(Lists.newArrayList(123, 112, 103, 93, 73)),
				is(false));
		assertThat(Problem054.flush.test(royalFlush), is(false));
		assertThat(Problem054.flush.test(straightFlush), is(false));
	}

	@Test
	public void testStraight() throws Exception {
		assertThat(
				Problem054.straight.test(Lists.newArrayList(123, 113, 100, 93, 82)),
				is(true));
		assertThat(
				Problem054.straight.test(Lists.newArrayList(113, 113, 100, 93, 82)),
				is(false));
		assertThat(Problem054.straight.test(royalFlush), is(false));
		assertThat(Problem054.straight.test(straightFlush), is(false));

	}

	@Test
	public void testThreeOfAKind() throws Exception {
		assertThat(Problem054.threeOfAKind
				.test(Lists.newArrayList(123, 113, 120, 93, 122)), is(true));
		assertThat(Problem054.threeOfAKind
				.test(Lists.newArrayList(123, 113, 120, 121, 122)), is(false));
		assertThat(
				Problem054.threeOfAKind.test(Lists.newArrayList(123, 70, 20, 121, 22)),
				is(false));
	}

	@Test
	public void testTwoPairs() throws Exception {
		assertThat(
				Problem054.twoPairs.test(Lists.newArrayList(123, 113, 120, 93, 112)),
				is(true));
		assertThat(
				Problem054.twoPairs.test(Lists.newArrayList(123, 113, 120, 121, 122)),
				is(false));
		assertThat(Problem054.twoPairs.test(Lists.newArrayList(73, 71, 60, 72, 63)),
				is(false));
		assertThat(Problem054.twoPairs.test(Lists.newArrayList(63, 71, 30, 72, 53)),
				is(false));
	}

	@Test
	public void testOnePair() throws Exception {
		assertThat(
				Problem054.onePair.test(Lists.newArrayList(123, 113, 60, 93, 112)),
				is(true));
		assertThat(
				Problem054.onePair.test(Lists.newArrayList(123, 113, 120, 93, 112)),
				is(false));
		assertThat(
				Problem054.onePair.test(Lists.newArrayList(123, 113, 120, 93, 122)),
				is(false));
		assertThat(
				Problem054.onePair.test(Lists.newArrayList(123, 113, 100, 93, 82)),
				is(false));
	}

	@Test
	public void testConvert2Score() throws Exception {
		List<Integer> highestCard = Lists.newArrayList(20, 32, 51, 103, 141);
		@SuppressWarnings("unchecked")
		List<List<Integer>> hands = Lists.newArrayList(royalFlush, straightFlush,
				fourOfAKind, highestCard);
		List<Integer> expects = Lists.newArrayList(10411194, 9222808, 7829366,
				959794);
		assertThat(Problem054.convert2Score(hands), is(expects));
	}

}
