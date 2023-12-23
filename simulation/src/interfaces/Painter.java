package interfaces;

import java.awt.Graphics;

/**
 * Used to paint a simple graphic.
 * 
 * @author pluseven
 */
@FunctionalInterface
public interface Painter {
	public abstract void paint(Graphics g, int x, int y, int w, int h);
}
