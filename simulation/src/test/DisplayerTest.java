package test;

import java.awt.Color;

import entities.Location;
import entities.SimuObject;
import simulator.Displayer;

public class DisplayerTest {

	public static void main(String[] args) {
		Displayer displayer = new Displayer();
		SimuObject simuObj = new Obj(Color.BLACK);
		Location location = new Location(0, 0);
		displayer.addObjectAt(simuObj, location);
		displayer.display();
	}

}

class Obj extends SimuObject {
	public Obj(Color color) {
		super.color = color;
	}
}
