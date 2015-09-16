package euler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Problem026 {

	public static void main(String[] args) {

		for (int i = 1; i < 1000; i++) {
			BigDecimal divide = BigDecimal.ONE.divide(new BigDecimal(i), 10000,
					RoundingMode.UP);
			if (10 % i == 0 || 100 % i == 0 || 1000 % i == 0) {
				continue;
			}
			int multiplicator = (i < 10) ? 10 : (i < 100) ? 100 : 1000;
			BigDecimal result = divide.multiply(new BigDecimal(multiplicator));
			String str = result.toString();
			int startIndex = 2;
			while (str.charAt(startIndex) == '0') {
				startIndex++;
			}
			for (int sequenceLength = 1; sequenceLength < 1000; sequenceLength++) {
				if (startIndex + 2 * sequenceLength > str.length()) {
					System.out.println(String.format(
							"%03d, no period smaller than : %03d", i, sequenceLength));
					break;
				}

				if (str.subSequence(startIndex, startIndex + sequenceLength).equals(
						str.subSequence(startIndex + sequenceLength, startIndex + 2
								* sequenceLength))) {
					System.out
							.println(String.format("%d, period: %d", i, sequenceLength));
					break;
				}
			}
		}
	}
}
