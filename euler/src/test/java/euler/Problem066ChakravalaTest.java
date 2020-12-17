package euler;

import euler.Problem066Chakravala.Triple;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem066ChakravalaTest {

    @Test
    void testInitialTriple() {
        Problem066Chakravala solver = new Problem066Chakravala();
        Triple expected = new Triple(BigInteger.valueOf(8), BigInteger.ONE, BigInteger.valueOf(-3));
        assertEquals(expected, solver.initialtriple(BigInteger.valueOf(67)));

        expected = new Triple(BigInteger.TWO, BigInteger.ONE, BigInteger.TWO);
        assertEquals(expected, solver.initialtriple(BigInteger.valueOf(2)));

        expected = new Triple(BigInteger.TWO, BigInteger.ONE, BigInteger.ONE);
        assertEquals(expected, solver.initialtriple(BigInteger.valueOf(3)));

    }
}