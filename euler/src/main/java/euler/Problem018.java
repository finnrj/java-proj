package euler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Coordinate;
import utils.Utils;

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
		}

		public int getColumn() {
			return coordinate.getColumn();
		}

		public Cell[] filterNeighbours(Collection<Cell> candidates) {
			int column = getColumn();
			return candidates.stream()
					.filter(c -> c.getColumn() == column || c.getColumn() == column + 1)
					.toArray(Cell[]::new);
		}

		public void evaluateValue(long pushedValue) {
			this.value = Math.max(value, initialValue + pushedValue);
		}

		@Override
		public String toString() {
			return "Cell [coordinate=" + coordinate + ", initialValue=" + initialValue
					+ ", value=" + value + "]";
		}

		public long getValue() {
			return value;
		}
	}

	private class Matrix {
		private Map<Integer, List<Cell>> cells = new HashMap<Integer, List<Cell>>();
		private int rowCount = 0;

		public Matrix(int[] initialValues) {
			int row = 0;
			int colDiff = 0;
			for (int i = 0; i < initialValues.length; i++) {
				if ((i + 1) > (row * (row + 1)) / 2) {
					row += 1;
					colDiff = i - 1;
				}
				List<Cell> rowElements = cells.getOrDefault(row, new ArrayList<Cell>());
				rowElements
						.add(new Cell(initialValues[i], new Coordinate(row, i - colDiff)));
				cells.put(row, rowElements);
			}
			rowCount = row;
		}

		public void solve() {
			cells.get(1).get(0).evaluateValue(0);

			for (int i = 1; i < rowCount; i++) {
				for (Cell cell : cells.get(i)) {
					for (Cell neighbour : cell.filterNeighbours(cells.get(i + 1))) {
						neighbour.evaluateValue(cell.getValue());
					}
				}
			}
			for (Cell lastRowElement : cells.get(rowCount)) {
				System.out.println(lastRowElement);
			}
			System.out.println("max value = " + cells.get(rowCount).stream()
					.mapToLong(Cell::getValue).max().getAsLong());
		}
	}

	public static void main(String[] args) throws IOException {
		Problem018 p = new Problem018();

		Path smallTriangle = Paths.get(".", "docs/p018_small.txt");
		Path largeTriangle = Paths.get(".", "docs/p018_large.txt");
		Path reallyLargeTriangle = Paths.get(".", "docs/p067_triangle.txt");
		for (Path path : Arrays.asList(smallTriangle, largeTriangle,
				reallyLargeTriangle)) {
			System.out.println(path);
			p.new Matrix(Utils.readNumberTriangle(path)).solve();
			System.out.println();
		}
	}
}