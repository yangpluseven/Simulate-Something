package constants;

import entities.Size;

/**
 * Includes some of the global constants. From a certain perspective, this class
 * should be eliminated, and the constants here should be added to their
 * corresponding classes. But I don't want to run into those classes to search
 * for the constants when changing parameters.
 * 
 * @author pluseven
 */
public class Constants {

	public static final int INIT_GRID_WIDTH = 5;
	public static final int INIT_GRID_HEIGHT = 5;
	public static final int NUM_COLS = 100;
	public static final int NUM_ROWS = 60;
	public static final int BACKGROUND_Z_ORDER = 0;
	public static final int TERRAIN_BOTTOM_Z_ORDER = 1;
	public static final int CREATURE_BOTTOM_Z_ORDER = 6;
	public static final Size INIT_GRID_SIZE = new Size(5, 5);

}
