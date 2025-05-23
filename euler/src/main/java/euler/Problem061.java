package euler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.tuple.Pair;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * </div>
 * <h2>Cyclical figurate numbers</h2> <div id="problem_info" class="info">
 * <h3>Problem 61</h3> <span>Published on Friday, 16th January 2004, 06:00 pm;
 * Solved by 15984; Difficulty rating: 20%</span> </div>
 * <div class="problem_content" role="problem">
 * <p>
 * Triangle, square, pentagonal, hexagonal, heptagonal, and octagonal numbers
 * are all figurate (polygonal) numbers and are generated by the following
 * formulae:
 * </p>
 * <table>
 * <tbody>
 * <tr>
 * <td>Triangle</td>
 * <td>&nbsp;</td>
 * <td>P<sub>3,<i>n</i></sub>=<i>n</i>(<i>n</i>+1)/2</td>
 * <td>&nbsp;</td>
 * <td>1, 3, 6, 10, 15, ...</td>
 * </tr>
 * <tr>
 * <td>Square</td>
 * <td>&nbsp;</td>
 * <td>P<sub>4,<i>n</i></sub>=<i>n</i><sup>2</sup></td>
 * <td>&nbsp;</td>
 * <td>1, 4, 9, 16, 25, ...</td>
 * </tr>
 * <tr>
 * <td>Pentagonal</td>
 * <td>&nbsp;</td>
 * <td>P<sub>5,<i>n</i></sub>=<i>n</i>(3<i>n</i>−1)/2</td>
 * <td>&nbsp;</td>
 * <td>1, 5, 12, 22, 35, ...</td>
 * </tr>
 * <tr>
 * <td>Hexagonal</td>
 * <td>&nbsp;</td>
 * <td>P<sub>6,<i>n</i></sub>=<i>n</i>(2<i>n</i>−1)</td>
 * <td>&nbsp;</td>
 * <td>1, 6, 15, 28, 45, ...</td>
 * </tr>
 * <tr>
 * <td>Heptagonal</td>
 * <td>&nbsp;</td>
 * <td>P<sub>7,<i>n</i></sub>=<i>n</i>(5<i>n</i>−3)/2</td>
 * <td>&nbsp;</td>
 * <td>1, 7, 18, 34, 55, ...</td>
 * </tr>
 * <tr>
 * <td>Octagonal</td>
 * <td>&nbsp;</td>
 * <td>P<sub>8,<i>n</i></sub>=<i>n</i>(3<i>n</i>−2)</td>
 * <td>&nbsp;</td>
 * <td>1, 8, 21, 40, 65, ...</td>
 * </tr>
 * </tbody>
 * </table>
 * <p>
 * The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three
 * interesting properties.
 * </p>
 * <ol>
 * <li>The set is cyclic, in that the last two digits of each number is the
 * first two digits of the next number (including the last number with the
 * first).</li>
 * <li>Each polygonal type: triangle (P<sub>3,127</sub>=8128), square (P
 * <sub>4,91</sub>=8281), and pentagonal (P<sub>5,44</sub>=2882), is represented
 * by a different number in the set.</li>
 * <li>This is the only set of 4-digit numbers with this property.</li>
 * </ol>
 * <p>
 * Find the sum of the only ordered set of six cyclic 4-digit numbers for which
 * each polygonal type: triangle, square, pentagonal, hexagonal, heptagonal, and
 * octagonal, is represented by a different number in the set.
 * </p>
 * </div> <br>
 * <br>
 */
public class Problem061 {

    public enum Polygonal {
        TRIANGLE, SQUARE, PENTAGONAL, HEXAGONAL, HEPTAGONAL, OCTAGONAL;
    }

    private static class Node {
        private Polygonal type;
        private String key;
        private String value;
        private Node next;
        private List<Polygonal> excludes = new ArrayList<>();

        public Node(Polygonal type, String key, String value) {
            this.type = type;
            this.key = key;
            this.value = value;
            next = null;
            excludes.add(this.type);
        }

        public void extendAncestors(StringBuilder accumulator,
                                    List<Polygonal> predecessors) {
            excludes.addAll(predecessors);
            push(accumulator);

            if (cycleFound(accumulator.substring(0, 2))) {
                System.out.println("cycle found !!!");
                String cycle = accumulator.toString();
                System.out.println(cycle);
                List<Integer> result = new ArrayList<>();
                while (cycle.length() > 0) {
                    result.add(Integer.valueOf(cycle.substring(0, 4)));
                    cycle = cycle.substring(4);
                }
                System.out.println(result + ": " + result.stream().mapToInt(Integer::intValue).sum());
            }
        }

        private void push(StringBuilder accumulator) {
//            System.out.println("push !!!");
            accumulator.append(String.format("%s%s", key, value));
//            System.out.println(accumulator);
        }

        private boolean cycleFound(String originalKey) {
            return excludes.size() == 6
                    && value.equalsIgnoreCase(originalKey);
        }

        public void setNext(Node successor, StringBuilder accumulator) {
            next = successor;
            next.extendAncestors(accumulator, excludes);
        }

        public boolean isNextCandidate(String searchValue,
                                       List<Polygonal> predecessors) {
            return key.equalsIgnoreCase(searchValue) && !predecessors.contains(type) && predecessors.size() < 6;
        }

        public void findNext(List<Node> candidates, StringBuilder accumulator) {
            if (excludes.size() == 6) {
                pop(accumulator, "pop, excludes == 6 !!!");
                return;
            }

            List<Node> filtered = candidates.stream().filter(n -> n.isNextCandidate(value, excludes)).collect(Collectors.toList());
            for (Node node : filtered) {
                setNext(node, accumulator);
                node.findNext(candidates, accumulator);
            }
            pop(accumulator, "pop, no more candidates !!!");
        }

        private void pop(StringBuilder accumulator, String reason) {
            if (accumulator.length() < 4) {
                return;
            }
//            System.out.println(reason);
            accumulator.delete(accumulator.length() - 4, accumulator.length());
//            System.out.println(accumulator);
            excludes = new ArrayList<>();
            excludes.add(type);
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        IntUnaryOperator triangleGenerator = n -> n * (n + 1) / 2;
        IntUnaryOperator squareGenerator = n -> n * n;
        IntUnaryOperator pentagonalGenerator = n -> (n * (3 * n - 1)) / 2;
        IntUnaryOperator hexagonalGenerator = n -> n * (2 * n - 1);
        IntUnaryOperator heptagonalGenerator = n -> n * (5 * n - 3) / 2;
        IntUnaryOperator octagonalGenerator = n -> n * (3 * n - 2);

        // List<Map<String, List<String>>> polygonalMaps = Lists
        Map<Polygonal, Map<String, List<String>>> polygonal2map = Utils
                .zip(Arrays.stream(Polygonal.values()), Arrays.asList(
                        triangleGenerator, squareGenerator,
                        pentagonalGenerator, hexagonalGenerator, heptagonalGenerator,
                        octagonalGenerator)
                        .stream().map(Problem061::collectToMap).collect(Collectors.toList())
                        .stream(), (t, m) -> Pair.of(t, m))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

//        polygonal2map.forEach((k, v) -> System.out.println(k + ":" + v));

        List<Node> nodes = new ArrayList();
        polygonal2map.forEach((polygonal, map) -> {
            map.entrySet().stream().forEach(e -> e.getValue().stream()
                    .forEach(v -> nodes.add(new Node(polygonal, e.getKey(), v))));
        });
//        nodes.forEach(System.out::println);

        for (Node node : nodes) {
            StringBuilder accumulator = new StringBuilder();
            accumulator.append(String.format("%s%s", node.key, node.value));
            node.findNext(nodes, accumulator);
        }
    }

    private static Map<String, List<String>> collectToMap(
            IntUnaryOperator polygonalGenerator) {
        return collectToMap(polygonalGenerator, 141);
    }

    private static Map<String, List<String>> collectToMap(
            IntUnaryOperator polygonalGenerator, int maxValue) {
        return IntStream.range(1, maxValue).map(polygonalGenerator)
                .filter(i -> 999 < i && i < 10_000)
                // .peek(System.out::println)
                .boxed()
                .collect(Collectors.groupingBy(i -> String.valueOf(i).substring(0, 2),
                        Collectors.mapping(i -> String.valueOf(i).substring(2),
                                Collectors.toList())));
    }
}
// Map<String, List<String>> triangles = collectToMap(triangleGenerator, 141);
// Map<String, List<String>> squares = collectToMap(squareGenerator, 100);
// Map<String, List<String>> pentagonals = collectToMap(pentagonalGenerator,
// 81);
// Map<String, List<String>> hexagonals = collectToMap(hexagonalGenerator, 70);
// Map<String, List<String>> heptagonals = collectToMap(heptagonalGenerator,
// 63);
// Map<String, List<String>> octogonals = collectToMap(octogonalGenerator, 58);
