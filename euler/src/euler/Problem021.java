package euler;

import java.util.ArrayList;
import java.util.List;

import euler.util.Utils;

public class Problem021 {

	public static void main(String[] args) {
		List<Integer> pairs = new ArrayList<Integer>();
		for (int i = 1; i <= 10_000; i++) {
			int sum = Utils.factorize(i).sum();
			int sum2 = Utils.factorize(sum).sum();
			if (i < sum && sum2 == i) {
				pairs.add(i);
				pairs.add(sum);
			}
		}
		System.out.println(pairs.stream().mapToInt(Integer::new).sum());
	}
}
