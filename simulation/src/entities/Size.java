package entities;

/**
 * Records the size of something, usually a rectangle.
 * 
 * @author pluseven
 */
public class Size extends Pair<Integer, Integer> {

	/**
	 * Default constructor.
	 */
	public Size() {
		left = 0;
		right = 0;
	}

	/**
	 * Represent a width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public Size(int width, int height) {
		left = width;
		right = height;
	}

	/**
	 * Update the size.
	 * 
	 * @param width
	 * @param height
	 */
	public void update(int width, int height) {
		left = width;
		right = height;
	}
	
	/**
	 * @return width
	 */
	public int getWidth() {
		return left;
	}
	
	/**
	 * @return height
	 */
	public int getHeight() {
		return right;
	}
	
	@Override
	public int compareTo(Pair<Integer, Integer> o) {
		return this.left == o.left ? this.right - o.right : this.left - o.left;
	}

}
