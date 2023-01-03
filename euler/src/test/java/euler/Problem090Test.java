package euler;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static euler.Problem090.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Problem090Test {

    @Test
    public void test25() {
        DicePair noFive = new DicePair(List.of(0, 1, 2, 3, 4, 6), List.of(1, 2, 3, 6, 7, 8));
        DicePair doFit = new DicePair(List.of(0, 1, 2, 3, 4, 5), List.of(3, 4, 5, 6, 7, 8));
        DicePair noFit = new DicePair(List.of(0, 1, 2, 3, 4, 6), List.of(1, 3, 4, 6, 7, 8));

        assertThat(fullfill25(noFive), Matchers.is(false));
        assertThat(fullfill25(doFit), Matchers.is(true));
        assertThat(fullfill25(noFit), Matchers.is(false));
    }
    @Test
    public void test81() {
        DicePair noEight = new DicePair(List.of(0, 1, 2, 3, 4, 6), List.of(0, 1, 2, 3, 6, 7));
        DicePair doFit = new DicePair(List.of(0, 1, 2, 3, 4, 5), List.of(3, 4, 5, 6, 7, 8));
        DicePair noFit = new DicePair(List.of(0, 2, 3, 4, 6, 7), List.of(1, 3, 4, 5, 7, 8));

        assertThat(fullfill81(noEight), Matchers.is(false));
        assertThat(fullfill81(doFit), Matchers.is(true));
        assertThat(fullfill81(noFit), Matchers.is(false));
    }
    @Test
    public void testSmallSquares() {
        DicePair doFit = new DicePair(List.of(1, 2, 3, 4, 6, 7), List.of(0, 1, 2, 3, 6, 7));
        DicePair alsoFit = new DicePair(List.of(0, 1, 2, 3, 4, 5), List.of(0, 1, 5, 6, 7, 8));
        DicePair noZero = new DicePair(List.of(1, 2, 3, 4, 6, 7), List.of(1, 3, 4, 5, 7, 8));

        assertThat(fullfillSmallSquares(doFit), Matchers.is(true));
        assertThat(fullfillSmallSquares(alsoFit), Matchers.is(true));
        assertThat(fullfillSmallSquares(noZero), Matchers.is(false));
    }
    @Test
    public void testEqiuvalent() {
        DicePair equivalent = new DicePair(List.of(1, 2, 3, 4, 6, 7), List.of(1, 2, 3, 4, 7, 9));
        DicePair alsoEquivalent = new DicePair(List.of(1, 2, 3, 4, 7, 9), List.of(1, 2, 3, 4, 6, 7));
        DicePair notEquivalent = new DicePair(List.of(0, 1, 2, 3, 4, 6), List.of(0, 1, 2, 3, 5, 9));

        assertThat(equivalent.areEquivalent(notEquivalent), Matchers.is(false));
        assertThat(alsoEquivalent.areEquivalent(notEquivalent), Matchers.is(false));
        assertThat(alsoEquivalent.areEquivalent(equivalent), Matchers.is(true));
        assertThat(equivalent.areEquivalent(alsoEquivalent), Matchers.is(true));
    }
    @Test
    public void testLastSquares() {
        DicePair doFit = new DicePair(List.of(1, 2, 3, 4, 7, 8), List.of(0, 1, 2, 3, 6, 7));
        DicePair alsoFit = new DicePair(List.of(2, 3, 4, 5, 6, 7), List.of(0, 1, 5, 6, 7, 8));
        DicePair no36 = new DicePair(List.of(1, 2, 3, 4, 6, 7), List.of(1, 2, 4, 5, 7, 8));
        DicePair no49 = new DicePair(List.of(1, 2, 3, 5, 6, 7), List.of(1, 2, 3, 5, 6, 7));

        assertThat(fullfillLargerSquares(doFit), Matchers.is(true));
        assertThat(fullfillLargerSquares(alsoFit), Matchers.is(true));
        assertThat(fullfillLargerSquares(no36), Matchers.is(false));
        assertThat(fullfillLargerSquares(no49), Matchers.is(false));
    }
    @Test
    public void testFullfillRequirement() {
        DicePair doFit = new DicePair(List.of(1, 2, 3, 4, 7, 8), List.of(0, 1, 2, 3, 6, 7));
        DicePair alsoFit = new DicePair(List.of(2, 3, 4, 5, 6, 7), List.of(0, 1, 5, 6, 7, 8));
        DicePair no36 = new DicePair(List.of(1, 2, 3, 4, 6, 7), List.of(1, 2, 4, 5, 7, 8));
        DicePair no49 = new DicePair(List.of(1, 2, 3, 5, 6, 7), List.of(1, 2, 3, 5, 6, 7));

        assertThat(fullfillLargerSquares(doFit), Matchers.is(true));
        assertThat(fullfillLargerSquares(alsoFit), Matchers.is(true));
        assertThat(fullfillLargerSquares(no36), Matchers.is(false));
        assertThat(fullfillLargerSquares(no49), Matchers.is(false));
    }
}