package solver;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordleSolverTest {

    @Test
    public void testBuild() {
        List<String> words = Stream.of("jetty", "wizzo", "cuppa", "cohoe", "gurks", "squad", "beisa", "shrug", "fossa", "fluyt", "camus", "speed", "mamil", "array", "polio", "barns", "panes", "souts", "limas", "fetch", "queck", "twink", "graze", "crock", "almud")
                .collect(Collectors.toList());
        String actual = "rossa";
    }
}