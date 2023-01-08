package euler;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static euler.Problem090.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Problem090Test {

   @Test
    public void testMatches() {
        DicePair doFit = new DicePair(List.of(1, 2, 3, 4, 7, 8), List.of(0, 1, 2, 3, 6, 7));
        DicePair alsoFit = new DicePair(List.of(2, 3, 4, 5, 6, 7), List.of(0, 1, 5, 6, 7, 8));

        assertThat( doFit.matches(0,1), Matchers.is(true));
        assertThat( doFit.matches(0,9), Matchers.is(false));
        assertThat( doFit.matches(2,5), Matchers.is(false));
        assertThat( doFit.matches(4,9), Matchers.is(true));
        assertThat( doFit.matches(8,1), Matchers.is(true));

        assertThat( alsoFit.matches(0,1), Matchers.is(false));
        assertThat( alsoFit.matches(0,9), Matchers.is(true));
        assertThat( alsoFit.matches(2,5), Matchers.is(true));
        assertThat( alsoFit.matches(4,9), Matchers.is(true));
        assertThat( alsoFit.matches(8,1), Matchers.is(false));
    }
}