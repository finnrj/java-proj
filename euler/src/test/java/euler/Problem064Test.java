package euler;

import euler.Problem064.Fraction;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem064Test {


    private Fraction fraction;

    @BeforeEach
    void setUp() {
        fraction= new Fraction(13, 5);
    }

    @Test
    void reducedFraction() {
        Fraction second = new Fraction(39, 15);
        Fraction third= new Fraction(65, 25);

        assertEquals(fraction, second);
        assertEquals(fraction, third);
    }

    @Test
    void testInverse() {
        assertEquals(Fraction.of(5,13), fraction.inverse());
    }

    @Test
    void testIntegerPart() {
        assertEquals(2, fraction.integerPart());
        assertEquals(0, fraction.inverse().integerPart());
    }

    @Test
    void testParts() {
        assertEquals(Pair.of(2L, Fraction.of(3, 5)) , fraction.parts());
    }
}