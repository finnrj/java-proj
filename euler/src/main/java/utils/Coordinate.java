package utils;

public class Coordinate implements Comparable<Coordinate> {

	private int column;
	private int row;

	public Coordinate(int row, int column) {
		this.column = column;
		this.row = row;
	}

	public static Coordinate of(int row, int column) {
		return new Coordinate(row, column);
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", column=" + column + "]";
	}

	@Override
	public int compareTo(Coordinate o) {
		if (row < o.row) {
			return -1;
		}
		if (row > o.row) {
			return 1;
		}

		if (column < o.column) {
			return -1;
		}
		if (column > o.column) {
			return 1;
		}
		return 0;
	}
}
