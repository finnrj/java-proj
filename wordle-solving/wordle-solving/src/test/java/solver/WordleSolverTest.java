package solver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        String candidate = "rossa";
        String target = "jetty";
        assertEquals((short) 0, solver.compare(candidate, target));
    }

    @Test
    void oneCorrectPosition() {
        String candidate = "rossa";
        String target = "cuppa";
        assertEquals((short)2, solver.compare(candidate, target));
    }

    @Test
    void oneCorrectLetters() {
        String candidate = "rossa";
        String target = "array";
        assertEquals((short)10001, solver.compare(candidate, target)  );
    }

    @Test
    void mixedResult() {
        String candidate = "rossa";
        String target = "graze";
        assertEquals((short)1100, solver.compare(candidate, target) );
    }

    @Test
    public void testBuild10() {
        List<String> words = Stream.of(
                "jetty", "wizzo", "cuppa", "cohoe", "gurks", 
                        "squad", "beisa", "shrug", "fossa", "fluyt")
                .collect(Collectors.toList());
        String candidate = "rossa";  //0,1000,2,2000, 10110, 111, 122, 10110, 2222, 0
        Map<Short, List<String>> result = solver.build(candidate, words);
        assertEquals(8, result.size() );
        assertTrue(result.containsKey((short) 0));
        assertEquals(2, result.get((short)0).size());
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
        Map<Short, List<String>> result = solver.build(candidate, words);
    }
}