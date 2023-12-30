package entities.painters;

import java.awt.Color;
import java.awt.Graphics;

import interfaces.Painter;

/**
 * Paint a small rectangle.
 * 
 * @author pluseven
 */
public class Rectangle implements Painter {

	private double scale;

	public Rectangle() {
		scale = 1D;
	}
	
	public Rectangle(double scale) {
		this.scale = scale;
	}

	@Override
	public void paint(Graphics g, int x, int y, int w, int h) {
		double xCenter, yCenter;
		xCenter = x + (double) w / 2;
		yCenter = y + (double) h / 2;
		int xReal, yReal, wReal, hReal;
		xReal = (int) (xCenter - (scale * w / 2));
		yReal = (int) (yCenter - (scale * h / 2));
		wReal = (int) (scale * w);
		hReal = (int) (scale * h);
		g.fillRect(xReal, yReal, wReal, hReal);
	}

}
