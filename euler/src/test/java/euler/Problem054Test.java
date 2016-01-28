package euler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class Problem054Test {

	private Problem054 problem054;

	@Before
	public void setUp() {
		problem054 = new Problem054();
	}

	@Test
	public void testConvert() throws Exception {
		String hands = "8C TS KC 9H 4S 7D 2S 5D 3S AC";
		List<IntStream> streams = Problem054.convert(hands)
				.collect(Collectors.toList());
		assertThat(streams.get(0).boxed().collect(Collectors.toList()),
				Matchers.contains(80, 103, 130, 92, 43));
		assertThat(streams.get(1).boxed().collect(Collectors.toList()),
				Matchers.contains(71, 23, 51, 33, 140));
	}

	@Test
	public void testIsRoyalFlush() throws Exception {
		assertThat(problem054.isRoyalFlush(IntStream.of(141, 131, 121, 111, 101)),
				is(true));

		assertThat(problem054.isRoyalFlush(IntStream.of(141, 131, 121, 110, 101)),
				is(false));

		assertThat(problem054.isRoyalFlush(IntStream.of(131, 121, 111, 101, 91)),
				is(false));
	}

	@Test
	public void testStraightFlush() throws Exception {
		assertThat(problem054.isStraightFlush(IntStream.of(123, 113, 103, 93, 83)),
				is(true));

		assertThat(problem054.isStraightFlush(IntStream.of(123, 113, 103, 92, 83)),
				is(false));

		assertThat(problem054.isStraightFlush(IntStream.of(123, 113, 103, 93, 73)),
				is(false));
	}

	@Test
	public void testFourOfAKind() throws Exception {
		assertThat(problem054.isFourOfAKind(IntStream.of(73, 71, 60, 72, 70)),
				is(true));

		assertThat(problem054.isFourOfAKind(IntStream.of(73, 71, 72, 80, 70)),
				is(true));

		assertThat(problem054.isFourOfAKind(IntStream.of(73, 71, 60, 72, 61)),
				is(false));
	}

	@Test
	public void testFullHouse() throws Exception {
		assertThat(problem054.isFullHouse(IntStream.of(73, 71, 60, 72, 63)),
				is(true));

		assertThat(problem054.isFullHouse(IntStream.of(73, 51, 60, 72, 63)),
				is(false));
	}

	@Test
	public void testFlush() throws Exception {
		assertThat(problem054.isFlush(IntStream.of(123, 113, 103, 93, 73)),
				is(true));

		assertThat(problem054.isFlush(IntStream.of(123, 112, 103, 93, 73)),
				is(false));
	}

	@Test
	public void testStraight() throws Exception {
		assertThat(problem054.isStraight(IntStream.of(123, 113, 100, 93, 82)),
				is(true));

		assertThat(problem054.isStraight(IntStream.of(113, 113, 100, 93, 82)),
				is(false));
	}

	@Test
	public void testThreeOfAKind() throws Exception {
		assertThat(problem054.isThreeOfAKind(IntStream.of(123, 113, 120, 93, 122)),
				is(true));

		assertThat(problem054.isThreeOfAKind(IntStream.of(123, 113, 120, 121, 122)),
				is(false));

		assertThat(problem054.isThreeOfAKind(IntStream.of(123, 70, 20, 121, 22)),
				is(false));
	}

	@Test
	public void testTwoPairs() throws Exception {
		assertThat(problem054.isTwoPairs(IntStream.of(123, 113, 120, 93, 112)),
				is(true));

		assertThat(problem054.isTwoPairs(IntStream.of(123, 113, 120, 121, 122)),
				is(false));

		assertThat(problem054.isTwoPairs(IntStream.of(73, 71, 60, 72, 63)),
				is(false));

		assertThat(problem054.isTwoPairs(IntStream.of(63, 71, 30, 72, 53)),
				is(false));
	}

	@Test
	public void testOnePair() throws Exception {
		assertThat(problem054.isOnePair(IntStream.of(123, 113, 60, 93, 112)),
				is(true));

		assertThat(problem054.isOnePair(IntStream.of(123, 113, 120, 93, 112)),
				is(false));

		assertThat(problem054.isOnePair(IntStream.of(123, 113, 120, 93, 122)),
				is(false));

		assertThat(problem054.isOnePair(IntStream.of(123, 113, 100, 93, 82)),
				is(false));
	}
}
