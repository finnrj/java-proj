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
 * <h2>Path sum: four ways</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=83"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 19th November 2004, 06:00 pm; Solved by 17608;<br>Difficulty rating: 25%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 83</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p class="small_notice">NOTE: This problem is a significantly more challenging version of <a href="problem=81">Problem 81</a>.</p>
 * <p>In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by moving left, right, up, and down, is indicated in bold red and is equal to 2297.</p>
 * <div class="center">
 * $$ \begin{pmatrix} \color{red}{131} &amp; 673 &amp; \color{red}{234} &amp; \color{red}{103} &amp; \color{red}{18}\\ \color{red}{201} &amp; \color{red}{96} &amp; \color{red}{342} &amp; 965 &amp; \color{red}{150}\\ 630 &amp; 803 &amp; 746 &amp; \color{red}{422} &amp; \color{red}{111}\\ 537 &amp; 699 &amp; 497 &amp; \color{red}{121} &amp; 956\\ 805 &amp; 732 &amp; 524 &amp; \color{red}{37} &amp; \color{red}{331} \end{pmatrix} $$
 * </div>
 * <p>Find the minimal path sum from the top left to the bottom right by moving left, right, up, and down in <a href="project/resources/p083_matrix.txt">matrix.txt</a> (right click and "Save Link/Target As..."), a 31K text file containing an 80 by 80 matrix.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem083 {
    public static Coordinate[] fetchNeighbours(Coordinate target, int maxIndex) {
        int resultRow = target.getRow();
        int resultColumn = target.getColumn();
        if (resultRow == maxIndex && resultColumn == maxIndex) {
            return new Coordinate[0];
        }
        Coordinate left = Coordinate.of(resultRow, resultColumn - 1);
        Coordinate right = Coordinate.of(resultRow, resultColumn + 1);
        Coordinate up = Coordinate.of(resultRow - 1, resultColumn);
        Coordinate down = Coordinate.of(resultRow + 1, resultColumn);
        if (resultRow == 0 && resultColumn == 0) {
            return new Coordinate[]{right, down};
        }
        if (resultRow == maxIndex && resultColumn == 0) {
            return new Coordinate[]{right, up};
        }
        if (resultRow == 0 && resultColumn == maxIndex) {
            return new Coordinate[]{left, down};
        }

        if (resultRow == 0) {
            return new Coordinate[]{right, left, down};
        }
        if (resultColumn == 0) {
            return new Coordinate[]{right, up, down};
        }
        if (resultRow == maxIndex) {
            return new Coordinate[]{right, left, up};
        }
        if (resultColumn == maxIndex) {
            return new Coordinate[]{left, up, down};
        }

        return new Coordinate[]{right, left, up, down};
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
        Coordinate start = Coordinate.of(0, 0);

        shortestPaths = new TreeMap<>();
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
        return shortestPaths.get(Coordinate.of(maxIndex, maxIndex));
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
