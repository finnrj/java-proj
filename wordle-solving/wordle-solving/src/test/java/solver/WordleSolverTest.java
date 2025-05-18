package solver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.WordleSolver.BuildResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordleSolverTest {

    private WordleSolver solver;

    @BeforeEach
    void setUp() {
        solver = new WordleSolver();
    }

    @Test
    void noMatchAtAll() {
        String target    = "jetty";
        String candidate = "rossa";
        assertEquals( 0, solver.compare(candidate, target));
    }

    @Test
    void oneCorrectPosition() {
        String target    = "cuppa";
        String candidate = "rossa";
        assertEquals(2, solver.compare(candidate, target));
    }

    @Test
    void twoCorrectLetters() {
        String target    = "array";
        String candidate = "rossa";
        assertEquals(10001, solver.compare(candidate, target)  );
    }

    @Test
    void mixedResult() {
        String target    = "graze";
        String candidate = "rossa";
        assertEquals(10001, solver.compare(candidate, target) );
    }

    @Test
    void mixedResult2() {
        String target    = "surge";
        String candidate = "rossa";
        assertEquals(10100, solver.compare(candidate, target) );
    }

    @Test
    void mixedResult3() {
        String target    = "slots";
        String candidate = "rossa";
        assertEquals(1110, solver.compare(candidate, target) );
    }

    @Test
    void testIgnoreCase() {
        String target    = "sLOtS";
        String candidate = "rossa";
        assertEquals(1110, solver.compare(candidate, target) );
    }

    @Test
    public void testBuild10() {
        List<String> words = Stream.of(
                "jetty", "wizzo", "cuppa", "cohoe", "gurks", 
                        "squad", "beisa", "shrug", "fossa", "fluyt")
                .collect(Collectors.toList());
        String candidate = "rossa";  //0,1000,2,2000, 10110, 111, 122, 10110, 2222, 0
        Map<Integer, List<String>> result = solver.build(candidate, words).results();
        assertEquals(8, result.size() );
        assertTrue(result.containsKey(0));
        assertEquals(2, result.get(0).size());
    }

    @Test
    public void testBuild25() {
        List<String> words = Stream.of(
                "jetty", "wizzo", "cuppa", "cohoe",
                        "gurks", "squad", "beisa", "shrug",
                        "fossa", "fluyt", "camus", "speed",
                        "mamil", "array", "polio", "barns",
                        "panes", "souts", "limas", "fetch",
                        "queck", "twink", "graze", "crock", "almud")
                .collect(Collectors.toList());
        String candidate = "rossa";
        Map<Integer, List<String>> result = solver.build(candidate, words).results();
        assertEquals(14, result.size() );
        assertTrue(result.containsKey(0));
        assertEquals(5, result.get(0).size());
    }


}