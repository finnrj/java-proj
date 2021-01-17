package euler;

import org.apache.commons.lang3.tuple.Pair;
import utils.Coordinate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * </div>
 * <h2>Path sum: two ways</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=81"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 22nd October 2004, 06:00 pm; Solved by 33652;<br>Difficulty rating: 10%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 81</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by <b>only moving to the right and down</b>, is indicated in bold red and is equal to 2427.</p>
 * <div class="center">
 * $$ \begin{pmatrix} \color{red}{131} &amp; 673 &amp; 234 &amp; 103 &amp; 18\\ \color{red}{201} &amp; \color{red}{96} &amp; \color{red}{342} &amp; 965 &amp; 150\\ 630 &amp; 803 &amp; \color{red}{746} &amp; \color{red}{422} &amp; 111\\ 537 &amp; 699 &amp; 497 &amp; \color{red}{121} &amp; 956\\ 805 &amp; 732 &amp; 524 &amp; \color{red}{37} &amp; \color{red}{331} \end{pmatrix} $$
 * </div>
 * <p>Find the minimal path sum from the top left to the bottom right by only moving right and down in <a href="project/resources/p081_matrix.txt">matrix.txt</a> (right click and "Save Link/Target As..."), a 31K text file containing an 80 by 80 matrix.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem081 {

    public static List<Coordinate> neighbours (Coordinate target) {
        return null;
    }

    public static int shortestMatrixPath(List<List<Integer>> matrix) {
        Map<Coordinate, Integer> shortestPaths = new HashMap();
        shortestPaths.put(Coordinate.of(0,0), matrix.get(0).get(0));
        int maxIndex = matrix.size() - 1;
//        for (int row = 0; row < matrix.size(); row++) {
//            for (int column = 0; column < matrix.size(); column++) {
//
//            }
//        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> result = Files.lines(Paths.get("src", "main", "docs", "matrix_small.txt"))
                .map(line -> line.split(","))
                .map(Arrays::asList)
                .map(lst -> lst.stream().map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(result);
    }

}
