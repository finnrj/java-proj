package euler;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Problem002 {

	static class FiboEven implements IntSupplier {

		private int a;
		private int b;
		private int max;

		public FiboEven(int max) {
			this.max = max;
			a = b = 1;
		}

		@Override
		public int getAsInt() {
			int c = a + b;
			if (c > max) {
				return 0;
			}
			int temp = a;
			a = a + 2 * b;
			b = 2 * temp + 3 * b;
			return c;
		}
	}

	public static void main(String[] args) {
		System.out.println(IntStream.generate(new FiboEven(4_000_000))//
				.limit(29)//
				.sum());
	}
}
