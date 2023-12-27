package entities.painters;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import interfaces.Painter;

/**
 * Print out some lines.
 * 
 * @author pluseven
 */
public class Line implements Painter {
	
	private Set<Integer> directions;

	public Line() {
		directions = new HashSet<Integer>();
	}
	
	public void refresh() {
		directions.clear();
	}
	
	public void addDirection(int x, int y) {
		if (x == 0 && y == 0)
			return;
		if (x < 0)
			x = -1;
		if (x > 0)
			x = 1;
		if (y < 0)
			y = -1;
		if (y > 0)
			y = 1;
		directions.add((x + 1) * 10 + y + 1);
	}
	
	@Override
	public void paint(Graphics g, int x, int y, int w, int h) {
		double xCenter, yCenter;
		xCenter = x + (double) w / 2;
		yCenter = y + (double) h / 2;
		for (Integer i : directions) {
			int xSign, ySign;
			ySign = i % 10 - 1;
			xSign = i / 10 - 1;
			int xEnd, yEnd;
			xEnd = (int) (xCenter + xSign * (double) w / 2);
			yEnd = (int) (yCenter + ySign * (double) h / 2);
			g.drawLine((int) xCenter, (int) yCenter, xEnd, yEnd);
		}
	}

}
