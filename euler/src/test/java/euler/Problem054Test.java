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

	// * <li><b>High Card</b>: Highest value card.</li>
	// * <li><b>One Pair</b>: Two cards of the same value.</li>
	// * <li><b>Two Pairs</b>: Two different pairs.</li>
	// * <li><b>Three of a Kind</b>: Three cards of the same value.</li>
	// * <li><b>Straight</b>: All cards are consecutive values.</li>
	// * <li><b>Flush</b>: All cards of the same suit.</li>
	// * <li><b>Full House</b>: Three of a kind and a pair.</li>
	// * <li><b>Four of a Kind</b>: Four cards of the same value.</li>
	// * <li><b>Straight Flush</b>: All cards are consecutive values of same suit.
	// * </li>

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

}
