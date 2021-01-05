package euler;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
*</div> 
<h2>Coin partitions</h2>
<div id="problem_icons" class="noprint">
 <a href="minimal=78"><img src="images/icons/file_html.png" title="Show HTML problem content" class="icon"></a>&nbsp;
 <span class="tooltip"><img src="images/icons/info.png" class="icon"><span class="tooltiptext_right">Published on Friday, 10th September 2004, 06:00 pm; Solved by 16293;<br>Difficulty rating: 30%</span></span>
</div>
<div id="problem_info">
 <h3>Problem 78</h3>
</div> 
<div class="problem_content" role="problem"> 
 <p>Let p(<i>n</i>) represent the number of different ways in which <i>n</i> coins can be separated into piles. For example, five coins can be separated into piles in exactly seven different ways, so p(5)=7.</p> 
 <div class="margin_left">
   OOOOO
  <br> OOOO&nbsp; &nbsp;O
  <br> OOO&nbsp; &nbsp;OO
  <br> OOO&nbsp; &nbsp;O&nbsp; &nbsp;O
  <br> OO&nbsp; &nbsp;OO&nbsp; &nbsp;O
  <br> OO&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O
  <br> O&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O&nbsp; &nbsp;O 
 </div> 
 <p>Find the least value of <i>n</i> for which p(<i>n</i>) is divisible by one million.</p> 
</div>
<br> 
<br>
*/
public class Problem078 {

public static void main(String[] args) {
    int cellCount = 1_000_000;
    Map<Integer, Integer> table = IntStream.rangeClosed(1, cellCount).boxed()
            .collect(Collectors.toMap(Function.identity(), (k) -> 1));

    for (int toAdd = 1; toAdd < cellCount; toAdd++) {
        for (int tableCell = toAdd + 1; tableCell <= cellCount; tableCell++) {
            int previous = tableCell - toAdd;
            int newAddition = previous > toAdd ? 0 : 1;
            table.put(tableCell, table.get(tableCell)
                    + table.get(previous)
                    + newAddition);
        }
    }
    System.out.println(table.entrySet().stream().filter(e -> e.getValue() % 1_000_000 == 0)
            .min(Comparator.comparing(Map.Entry::getKey)));
}

}

