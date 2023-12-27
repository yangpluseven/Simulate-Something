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

	public static final Size INIT_GRID_SIZE = new Size(10, 10);
	public static final Size NUM_OF_COL_ROW = new Size(50, 30);
	public static final int BACKGROUND_Z_ORDER = 0;
	public static final int TERRAIN_BOTTOM_Z_ORDER = 1;
	public static final int CREATURE_BOTTOM_Z_ORDER = 6;
	public static final boolean ZOOM_IN = true;
	public static final boolean ZOOM_OUT = false;
	
}
