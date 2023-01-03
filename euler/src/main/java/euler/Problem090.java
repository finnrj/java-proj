package euler;

import utils.Combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * </div>
 * <h2>Cube digit pairs</h2>
 * <div id="problem_icons" class="noprint">
 * <a href="minimal=90"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 * <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 4th March 2005, 06:00 pm; Solved by 10839;<br>Difficulty rating: 40%</span></span>
 * </div>
 * <div id="problem_info">
 * <h3>Problem 90</h3>
 * </div>
 * <div class="problem_content" role="problem">
 * <p>Each of the six faces on a cube has a different digit (0 to 9) written on it; the same is done to a second cube. By placing the two cubes side-by-side in different positions we can form a variety of 2-digit numbers.</p>
 * <p>For example, the square number 64 could be formed:</p>
 * <div class="center">
 * <img src="project/images/p090.png" class="dark_img" alt="">
 * <br>
 * </div>
 * <p>In fact, by carefully choosing the digits on both cubes it is possible to display all of the square numbers below one-hundred: 01, 04, 09, 16, 25, 36, 49, 64, and 81.</p>
 * <p>For example, one way this can be achieved is by placing {0, 5, 6, 7, 8, 9} on one cube and {1, 2, 3, 4, 8, 9} on the other cube.</p>
 * <p>However, for this problem we shall allow the 6 or 9 to be turned upside-down so that an arrangement like {0, 5, 6, 7, 8, 9} and {1, 2, 3, 4, 6, 7} allows for all nine square numbers to be displayed; otherwise it would be impossible to obtain 09.</p>
 * <p>In determining a distinct arrangement we are interested in the digits on each cube, not the order.</p>
 * <p class="margin_left">{1, 2, 3, 4, 5, 6} is equivalent to {3, 6, 4, 1, 2, 5}<br> {1, 2, 3, 4, 5, 6} is distinct from {1, 2, 3, 4, 5, 9}</p>
 * <p>But because we are allowing 6 and 9 to be reversed, the two distinct sets in the last example both represent the extended set {1, 2, 3, 4, 5, 6, 9} for the purpose of forming 2-digit numbers.</p>
 * <p>How many distinct arrangements of the two cubes allow for all of the square numbers to be displayed?</p>
 * </div>
 * <br>
 * <br>
 */
public class Problem090 {
    record DicePair(List<Integer> first, List<Integer> second) {
        public boolean areEquivalent(DicePair other) {
            if (! (other.first.containsAll(first) || other.second.containsAll(first))) {
                return false;
            }
            return other.second.containsAll(second) || other.first.containsAll(second);
        }
    }

    ;

    public static boolean fullfillRequirement(DicePair candidate) {
        return fullfill25(candidate)
                && fullfill81(candidate)
                && fullfillSmallSquares(candidate)
                && fullfillLargerSquares(candidate);
    }


    static boolean fullfill25(DicePair candidate) {
        return candidate.first.contains(2) && candidate.second.contains(5)
                || candidate.first.contains(5) && candidate.second.contains(2);
    }

    static boolean fullfill81(DicePair candidate) {
        return candidate.first.contains(1) && candidate.second.contains(8)
                || candidate.first.contains(1) && candidate.second.contains(8);
    }

    static boolean fullfillSmallSquares(DicePair candidate) {
        List<Integer> both = new ArrayList<>(candidate.first);
        both.addAll(candidate.second);
        return candidate.first.contains(0) && candidate.second.containsAll(List.of(1, 4)) &&
                (candidate.second.contains(6) || candidate.second.contains(9))
                || candidate.second.contains(0) && candidate.first.containsAll(List.of(1, 4)) &&
                (candidate.first.contains(6) || candidate.first.contains(9))
                || candidate.first.contains(0) && candidate.second.contains(0) && both.containsAll(List.of(1, 4)) &&
                (both.contains(6) || both.contains(9));
    }

    static boolean fullfillLargerSquares(DicePair candidate) {
        List<Integer> both = new ArrayList<>(candidate.first);
        both.addAll(candidate.second);
        return (candidate.first.contains(6) || candidate.first.contains(9)) && candidate.second.containsAll(List.of(1, 3, 4)) ||
                candidate.first.containsAll(List.of(1, 3, 4)) && (candidate.second.contains(6) || candidate.second.contains(9)) ||
                (candidate.first.contains(6) || candidate.first.contains(9)) && (candidate.second.contains(6) || candidate.second.contains(9)) &&
                        both.containsAll(List.of(1, 3, 4));
    }


    static List<DicePair> makeCombos() {
        List<List<Integer>> first = Combinations.combinations(6, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
//    System.out.println(first);
        List<DicePair> combis = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            for (int j = i; j < first.size(); j++) {
                combis.add(new DicePair(first.get(i), first.get(j)));
            }
        }
        return combis;
    }

    public static void main(String[] args) {
        List<DicePair> combis = makeCombos();
        System.out.println(combis.size());
        List<DicePair> solutions = combis.stream().filter(Problem090::fullfillRequirement).collect(Collectors.toList());
        System.out.println(solutions);
        System.out.println(solutions.size());

        List<DicePair> extendedSolutions = solutions.stream().map(dicePair -> {
            if (dicePair.first.contains(6) && !dicePair.first.contains(9)) {
                dicePair.first.add(9);
            } else if (dicePair.first.contains(9) && !dicePair.first.contains(6)) {
                dicePair.first.add(6);
            }
            if (dicePair.second.contains(6) && !dicePair.second.contains(9)) {
                dicePair.second.add(9);
            } else if (dicePair.second.contains(9) && !dicePair.second.contains(6)) {
                dicePair.second.add(6);
            }
            return dicePair;
        }).collect(Collectors.toList());
        System.out.println(extendedSolutions);

        System.out.println(extendedSolutions.size());
        for (int i = extendedSolutions.size(); i < 0; i--) {
            for (int j = i - 1; j <= 0; j--) {
                if (extendedSolutions.get(i).areEquivalent(extendedSolutions.get(j))) {
                    System.out.println("removing index: " + j);
                    extendedSolutions.remove(j);
                }
            }
        }
        System.out.println(extendedSolutions.size());
    }

}
