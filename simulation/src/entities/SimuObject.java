package entities;

import java.awt.Color;

import entities.painters.Rectangle;
import interfaces.Painter;
import simulator.GridMap;

// TODO SimuObject doc
public abstract class SimuObject {
	
	protected Painter painter;
	protected GridMap gridMap;
	protected Location location;
	protected Color color;
	
	public SimuObject() {
		// TODO SimuObject constructor
	}
	
	public void moveTo(Location location) {
		gridMap.remove(this.location);
		this.location = location;
		gridMap.addObjectAt(this, location);
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
	
	public abstract void forward();

}
