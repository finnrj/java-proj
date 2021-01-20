package euler;

import utils.Coordinate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * </div>
 * <h2>Path sum: three ways</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=82"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 5th November 2004, 06:00 pm; Solved by 20541;<br>Difficulty rating: 20%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 82</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p class="small_notice">NOTE: This problem is a more challenging version of <a href="problem=81">Problem 81</a>.</p>
 * <p>The minimal path sum in the 5 by 5 matrix below, by starting in any cell in the left column and finishing in any cell in the right column, and only moving up, down, and right, is indicated in red and bold; the sum is equal to 994.</p>
 * <div class="center">
 * $$ \begin{pmatrix} 131 &amp; 673 &amp; \color{red}{234} &amp; \color{red}{103} &amp; \color{red}{18}\\ \color{red}{201} &amp; \color{red}{96} &amp; \color{red}{342} &amp; 965 &amp; 150\\ 630 &amp; 803 &amp; 746 &amp; 422 &amp; 111\\ 537 &amp; 699 &amp; 497 &amp; 121 &amp; 956\\ 805 &amp; 732 &amp; 524 &amp; 37 &amp; 331 \end{pmatrix} $$
 * </div>
 * <p>Find the minimal path sum from the left column to the right column in <a href="project/resources/p082_matrix.txt">matrix.txt</a> (right click and "Save Link/Target As..."), a 31K text file containing an 80 by 80 matrix.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem082 {
    public static Coordinate[] fetchNeighbours(Coordinate target, int maxIndex) {
        int resultRow = target.getRow();
        int resultColumn = target.getColumn();
        if (resultColumn == maxIndex) {
            return new Coordinate[0];
        }
        Coordinate right = Coordinate.of(resultRow, resultColumn + 1);
        if (resultColumn == 0) {
            return new Coordinate[]{right};
        }
        Coordinate down = Coordinate.of(resultRow + 1, resultColumn);
        if (resultRow == 0) {
            return new Coordinate[]{right, down};
        }
        Coordinate up = Coordinate.of(resultRow - 1, resultColumn);
        if (resultRow == maxIndex) {
            return new Coordinate[]{right, up};
        }
        return new Coordinate[]{right, down, up};
    }

    private static Integer getValueFor(List<List<Integer>> matrix, Coordinate coordinate) {
        return matrix.get(coordinate.getRow()).get(coordinate.getColumn());
    }

    private static boolean isImprovement(Map<Coordinate, Integer> shortestPaths, Coordinate coordinate, int candidateValue) {
        return !shortestPaths.containsKey(coordinate) || shortestPaths.get(coordinate) > candidateValue;
    }

    public static int shortestMatrixPath(List<List<Integer>> matrix) {
        Map<Coordinate, Integer> shortestPaths;
        int maxIndex = matrix.size() - 1;
        Coordinate start;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= maxIndex; i++) {
            shortestPaths = new TreeMap<>();
            start = Coordinate.of(i, 0);
            shortestPaths.put(start, getValueFor(matrix, start));
            List<Coordinate> neighbours = new ArrayList<>();
            neighbours.add(start);
            while (neighbours.size() > 0) {
                Coordinate actual = neighbours.remove(0);
                Coordinate[] newNeighbours = fetchNeighbours(actual, maxIndex);
                for (Coordinate coordinate : newNeighbours) {
                    int candidateValue = shortestPaths.get(actual) + getValueFor(matrix, coordinate);
                    if (isImprovement(shortestPaths, coordinate, candidateValue)) {
                        shortestPaths.put(coordinate, candidateValue);
                        neighbours.add(coordinate);
                    }
                }
            }
            int newResult = shortestPaths.entrySet().stream()
                    .filter(e -> e.getKey().getColumn() == maxIndex)
                    .mapToInt(Map.Entry::getValue)
                    .min().orElse(-1);
            result = Math.min(result, newResult);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src", "main", "docs", "matrix.txt");
//        Path path = Paths.get("src", "main", "docs", "matrix_small.txt");
        List<List<Integer>> result = Files.lines(path)
                .map(line -> line.split(","))
                .map(Arrays::asList)
                .map(lst -> lst.stream().map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
        Instant start = Instant.now();
        System.out.println("shortest path is: " + shortestMatrixPath(result));
        System.out.println("it took " + Duration.between(start, Instant.now()));

    }

}
