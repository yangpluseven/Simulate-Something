package entities;

/**
 * Records the location of a SimuObject on a grid map. It is different
 * from the x and y used in the GUI, and requires some calculations to convert
 * it into x and y.
 * 
 * @author pluseven
 */
public class Location extends Pair<Integer, Integer>{

	/**
	 * Default constructor.
	 */
	public Location() {
		left = 0;
		right = 0;
	}

	/**
	 * Represent a row and column.
	 * 
	 * @param row The row.
	 * @param col The column.
	 */
	public Location(int col, int row) {
		left = col;
		right = row;
	}

	/**
	 * Update the location.
	 * 
	 * @param row
	 * @param col
	 */
	public void update(int col, int row) {
		left = col;
		right = row;
	}

	/**
	 * @return The column.
	 */
	public int getCol() {
		return left;
	}

	/**
	 * @return The row.
	 */
	public int getRow() {
		return right;
	}

	@Override
	public int compareTo(Pair<Integer, Integer> o) {
		return this.left == o.left ? this.right - o.right : this.left - o.left;
	}

}
