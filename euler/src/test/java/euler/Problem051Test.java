package euler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Collection;

import org.junit.Test;

public class Problem051Test {

	@Test
	public void testGetFamilySize() {
		Long candidate = 1234547L;
		Collection<Long> actual = Problem051.makeFamily(candidate);
		assertThat(actual.size(), is(10));
	}

	@Test
	public void testGetFamilyMembersTwoDigits() {
		Long candidate = 123437L;
		Collection<Long> actual = Problem051.makeFamily(candidate);
		System.out.println(actual);
		assertThat(actual.contains(120407L), is(true));
		assertThat(actual.contains(124447L), is(true));
		assertThat(actual.contains(123438L), is(false));
	}

}
