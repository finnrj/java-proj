package euler;

import java.util.HashMap;
import java.util.Map;

import euler.util.Coordinate;

public class Problem018 {

	/**** 3 */
	/*** 7 4 */
	/** 2 4 6 */
	/* 8 5 9 3 */

	/*************** 75 */
	/************** 95 64 */
	/************* 17 47 82 */
	/************ 18 35 87 10 */
	/*********** 20 04 82 47 65 */
	/********** 19 01 23 75 03 34 */
	/********* 88 02 77 73 07 63 67 */
	/******** 99 65 04 28 06 16 70 92 */
	/******* 41 41 26 56 83 40 80 70 33 */
	/****** 41 48 72 33 47 32 37 16 94 29 */
	/***** 53 71 44 65 25 43 91 52 97 51 14 */
	/**** 70 11 33 28 77 73 17 78 39 68 17 57 */
	/*** 91 71 52 38 17 14 91 43 58 50 27 29 48 */
	/** 63 66 04 68 89 53 67 30 73 16 69 87 40 31 */
	/* 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23 */

	// 75
	// 95 64
	// 17 47 82
	// 18 35 87 10
	// 20 04 82 47 65
	// 19 01 23 75 03 34
	// 88 02 77 73 07 63 67
	// 99 65 04 28 06 16 70 92
	// 41 41 26 56 83 40 80 70 33
	// 41 48 72 33 47 32 37 16 94 29
	// 53 71 44 65 25 43 91 52 97 51 14
	// 70 11 33 28 77 73 17 78 39 68 17 57
	// 91 71 52 38 17 14 91 43 58 50 27 29 48
	// 63 66 04 68 89 53 67 30 73 16 69 87 40 31
	// 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

	private class Cell {
		private Coordinate coordinate;

		private long value = -1;
		private long initialValue;

		public Cell(long initialValue, Coordinate coordinate) {
			this.initialValue = initialValue;
			this.coordinate = coordinate;
			// System.out.println(this);
		}

		public void evaluateValue(long pushedValue) {
			this.value = Math.max(value, initialValue + pushedValue);
		}

		@Override
		public String toString() {
			return "Cell [coordinate=" + coordinate + ", initialValue="
					+ initialValue + ", value=" + value + "]";
		}

		public long getValue() {
			return value;
		}
	}

	private class Matrix {
		private Map<Coordinate, Cell> cells = new HashMap<Coordinate, Cell>();
		private int rowCount = 0;

		public Matrix(int[] initialValues) {
			int row = 0;
			int colDiff = 0;
			for (int i = 0; i < initialValues.length; i++) {
				if ((i + 1) > (row * (row + 1)) / 2) {
					row += 1;
					colDiff = i - 1;
				}
				Coordinate key = new Coordinate(row, i - colDiff);
				cells.put(key, new Cell(initialValues[i], key));
			}
			rowCount = row;
		}

		public void solve() {
			for (Coordinate c : cells.keySet()) {
				if (c.getRow() == 1) {
					Cell firstCell = cells.get(c);
					firstCell.evaluateValue(0);
				}
			}

			for (int i = 1; i < rowCount; i++) {
				for (Map.Entry<Coordinate, Cell> entry : cells.entrySet()) {
					Coordinate key = entry.getKey();
					if (key.getRow() == i) {
						long value = entry.getValue().getValue();
						cells.get(new Coordinate(key.getRow() + 1, key.getColumn()))
								.evaluateValue(value);
						cells.get(new Coordinate(key.getRow() + 1, key.getColumn() + 1))
								.evaluateValue(value);
					}
				}
			}
			for (Coordinate coordinate : cells.keySet()) {
				if (coordinate.getRow() == rowCount) {
					System.out.println(cells.get(coordinate));
				}
			}
		}
	}

	public static void main(String[] args) {
		Problem018 p = new Problem018();

		int[] testValues = new int[] { 3, 7, 4, 2, 4, 6, 8, 5, 9, 3 };

		int[] values = new int[] { 75, 95, 64, 17, 47, 82, 18, 35, 87, 10, 20, 4,
				82, 47, 65, 19, 1, 23, 75, 3, 34, 88, 2, 77, 73, 7, 63, 67, 99, 65, 4,
				28, 6, 16, 70, 92, 41, 41, 26, 56, 83, 40, 80, 70, 33, 41, 48, 72, 33,
				47, 32, 37, 16, 94, 29, 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14, 70,
				11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57, 91, 71, 52, 38, 17, 14, 91,
				43, 58, 50, 27, 29, 48, 63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87,
				40, 31, 4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23 };

		Matrix matrix = p.new Matrix(values);
		matrix.solve();
	}
}