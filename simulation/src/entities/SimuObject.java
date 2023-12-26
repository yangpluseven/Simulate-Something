package entities;

import java.awt.Color;

import entities.painters.Rectangle;
import interfaces.Painter;

// TODO SimuObject doc
public abstract class SimuObject {
	
	protected Location location;
	protected Painter painter;
	protected Color color;
	
	public SimuObject() {
		location = new Location();
		painter = new Rectangle();
	}

	public Color getColor() {
		return color;
	}

	public Painter getPainter() {
		return painter;
	}

	public Location getLocation() {
		return location;
	}

}
