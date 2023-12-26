package entities.painters;

import java.awt.Color;
import java.awt.Graphics;

import interfaces.Painter;

public class Oval implements Painter {

	private double scale;
	private boolean verticalOrNot;
	
	public Oval() {
		scale = 1D;
		verticalOrNot = true;
	}
	
	public Oval(double scale) {
		this.scale = scale;
		verticalOrNot = true;
	}
	
	public void setDirection(boolean verticalOrNot) {
		this.verticalOrNot = verticalOrNot;
	}
	
	@Override
	public void paint(Graphics g, int x, int y, int w, int h) {
		int xCenter, yCenter;
		xCenter = x + w / 2;
		yCenter = y + h / 2;
		int xReal, yReal, wReal, hReal;
		if (verticalOrNot == true) {
			yReal = y;
			hReal = h;
			xReal = xCenter - (int) (scale * w / 2);
			wReal = (int) (scale * w);
		} else {
			xReal = x;
			wReal = w;
			yReal = yCenter - (int) (scale * h / 2);
			hReal = (int) (scale * h);
		}
		g.fillOval(xReal, yReal, wReal, hReal);
	}

}
