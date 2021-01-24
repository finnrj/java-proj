package euler;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * </div>
 * <h2>Counting rectangles</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=85"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 17th December 2004, 06:00 pm; Solved by 24065;<br>Difficulty rating: 15%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 85</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles:</p>
 * <div class="center">
 * <img src="project/images/p085.png" class="dark_img" alt="">
 * </div>
 * <p>Although there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution.</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem085 {

    public static void main(String[] args) {
        record Rectangle(Integer parentRow, Integer parentCol, Integer rectangles, Integer area) {
        }

        List<Rectangle> rectangles = new ArrayList();
        int rectangleSum = 0;
        int rowParent;
        int colParent = 0;
        for (rowParent = 1; rowParent <= 100; rowParent++) {
            for (colParent = 1; colParent <= rowParent; colParent++) {
                rectangleSum = 0;
                for (int row = 1; row <= rowParent; row++) {
                    for (int col = 1; col <= colParent; col++) {
                        rectangleSum += (rowParent - row + 1) * (colParent - col + 1);
                    }
                }
                rectangles.add(new Rectangle(rowParent, colParent, rectangleSum,
                        rowParent * colParent));
            }
        }

        System.out.println(rectangles.stream()
                .filter(rectangle -> 1_900_000 < rectangle.rectangles() && rectangle.rectangles() < 2_100_000)
                .map(rectangle -> Pair.of(Pair.of(rectangle.parentRow, rectangle.parentCol), Math.abs(2_000_000 - rectangle.rectangles())))
                .min(Comparator.comparing(Pair::getValue))
                .map(pairIntegerPair -> pairIntegerPair.getLeft().getLeft() * pairIntegerPair.getLeft().getRight())
                .orElseThrow());
    }

}
