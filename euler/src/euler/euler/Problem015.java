package euler;

import static utils.Utils.factorial;

import java.util.HashMap;
import java.util.Map;

import utils.Coordinate;

public class Problem015 {

	private class Cell {
		private Coordinate coordinate;
		private Matrix matrix;
		private Cell rowSuccessor;
		private Cell colSuccessor;
		private Coordinate rowPredecessor;
		private Coordinate colPredecessor;

		private long value = -1;

		public Cell() {
		}

		public Cell(Coordinate coordinate, Matrix matrix) {
			this.coordinate = coordinate;
			this.matrix = matrix;
			rowSuccessor = this.matrix.addCell(
					new Coordinate(coordinate.getRow() + 1, coordinate.getColumn()));

			colSuccessor = this.matrix.addCell(
					new Coordinate(coordinate.getRow(), coordinate.getColumn() + 1));

			rowPredecessor = new Coordinate(coordinate.getRow() - 1,
					coordinate.getColumn());
			colPredecessor = new Coordinate(coordinate.getRow(),
					coordinate.getColumn() - 1);

			// System.out.println(this.coordinate);
		}

		public boolean hasValue() {
			return value != -1;
		}

		public long getValue() {
			return value;
		}

		public void setValue(long value) {
			this.value = value;
			System.out.println(this);
			matrix.notifyNeighbour(rowPredecessor);
			matrix.notifyNeighbour(colPredecessor);
		}

		public void notifyNeighbourValueSet() {
			if (!hasValue() && rowSuccessor.hasValue() && colSuccessor.hasValue()) {
				setValue(
						Math.max(rowSuccessor.getValue() + colSuccessor.getValue(), 1));
			}
		}

		@Override
		public String toString() {
			return "Cell [coordinate=" + coordinate + ", value=" + value + "]";
		}
	}

	private class Matrix {
		private final Cell NULL_CELL = new NullCell();

		private class NullCell extends Cell {
			public NullCell() {
				super();
			}

			@Override
			public long getValue() {
				return 0;
			}

			@Override
			public boolean hasValue() {
				return true;
			}
		};

		private Map<Coordinate, Cell> cells = new HashMap<Coordinate, Cell>();
		private int max;

		public Matrix(int max) {
			this.max = max;
		}

		public void notifyNeighbour(Coordinate neighbour) {
			if (cells.keySet().contains(neighbour)) {
				cells.get(neighbour).notifyNeighbourValueSet();
			}
		}

		public Cell addCell(Coordinate candidate) {
			if (!cells.keySet().contains(candidate) && candidate.getRow() <= max
					&& candidate.getColumn() <= max) {
				Cell newCell = new Cell(candidate, this);
				cells.put(candidate, newCell);
				return newCell;
			}
			if (cells.keySet().contains(candidate)) {
				return cells.get(candidate);
			}
			return NULL_CELL;
		}
	}

	public static void main(String[] args) {
		Problem015 p = new Problem015();
		int max = 20;
		Matrix matrix = p.new Matrix(max);
		matrix.addCell(new Coordinate(0, 0));
		matrix.notifyNeighbour(new Coordinate(max, max));

		System.out.println("factorial solution: "
				+ factorial(40).divide(factorial(20).multiply(factorial(20))));
	}

}
