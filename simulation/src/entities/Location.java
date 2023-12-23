package entities;

/**
 * Location records the location of a SimuObject on a grid map. It is different
 * from the x and y used in the GUI, and requires some calculations to convert
 * it into x and y.
 * 
 * @author pluseven
 */
public class Location {

	private int col;
	private int row;

	/**
	 * Default constructor.
	 */
	public Location() {
		col = 0;
		row = 0;
	}

	/**
	 * Represent a row and column.
	 * 
	 * @param row The row.
	 * @param col The column.
	 */
	public Location(int col, int row) {
		this.col = col;
		this.row = row;
	}

	/**
	 * Implement content equality.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location other = (Location) obj;
			return row == other.getRow() && col == other.getCol();
		} else {
			return false;
		}
	}

	/**
	 * Return a string of the form row,column
	 * 
	 * @return A string representation of the location.
	 */
	public String toString() {
		return col + ", " + row;
	}

	/**
	 * Use the top 16 bits for the row value and the bottom for the column. Except
	 * for very big grids, this should give a unique hash code for each (row, col)
	 * pair.
	 * 
	 * @return A hash code for the location.
	 */
	public int hashCode() {
		return (col << 16) + row;
	}

	/**
	 * Update the location.
	 * 
	 * @param row
	 * @param col
	 */
	public void update(int col, int row) {
		this.col = col;
		this.row = row;
	}

	/**
	 * @return The column.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return The row.
	 */
	public int getRow() {
		return row;
	}

}
