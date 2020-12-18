package euler;

import euler.Problem066.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem066ChakravalaTest {

    private Problem066 solver;

    @BeforeEach
    void setUp() {
        solver = new Problem066();
    }

    @Test
    void testInitialTriple67() {
        Problem066 solver = new Problem066();
        Triple expected = new Triple(BigInteger.valueOf(8), BigInteger.ONE, BigInteger.valueOf(-3));
        assertEquals(expected, solver.initialTriple(BigInteger.valueOf(67)));
    }

    @Test
    void testInitialTriple2() {
        Triple expected = new Triple(BigInteger.TWO, BigInteger.ONE, BigInteger.TWO);
        assertEquals(expected, solver.initialTriple(BigInteger.valueOf(2)));
    }

    @Test
    void testInitialTriple3() {
        Triple expected = new Triple(BigInteger.TWO, BigInteger.ONE, BigInteger.ONE);
        assertEquals(expected, solver.initialTriple(BigInteger.valueOf(3)));
    }

    @Test
    void testInitialTriple5() {
        Triple expected = new Triple(BigInteger.valueOf(2), BigInteger.ONE, BigInteger.ONE.negate());
        assertEquals(expected, solver.initialTriple(BigInteger.valueOf(5)));
    }

    @Test
    void testSolve2() {
        Triple expected = new Triple(BigInteger.valueOf(3), BigInteger.TWO, BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.TWO));
    }

    @Test
    void testSolve3() {
        Triple expected = new Triple(BigInteger.TWO, BigInteger.ONE, BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.valueOf(3)));
    }

    @Test
    void testSolve5() {
        Triple expected = new Triple(BigInteger.valueOf(9), BigInteger.valueOf(4), BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.valueOf(5)));
    }

    @Test
    void testSolve6() {

        Triple expected = new Triple(BigInteger.valueOf(5), BigInteger.valueOf(2), BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.valueOf(6)));
    }

    @Test
    void testSolve7() {
        Triple expected = new Triple(BigInteger.valueOf(8), BigInteger.valueOf(3), BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.valueOf(7)));
    }

    @Test
    void testSolve67() {
        Triple expected = new Triple(BigInteger.valueOf(48_842), BigInteger.valueOf(5_967), BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.valueOf(67)));
    }

    @Test
    void testSolve61() {
        Triple expected = new Triple(BigInteger.valueOf(1_766_319_049), BigInteger.valueOf(226_153_980), BigInteger.ONE);
        assertEquals(expected, solver.solve(BigInteger.valueOf(61)));
    }

}
