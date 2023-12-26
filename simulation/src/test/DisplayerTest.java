package test;

import java.awt.Color;

import entities.Location;
import entities.SimuObject;
import entities.painters.Oval;
import interfaces.Painter;
import simulator.Displayer;

public class DisplayerTest {

	public static void main(String[] args) {
		Displayer displayer = new Displayer();
		
		SimuObject simuObjA = new Obj(Color.BLUE);
		Location locationA = new Location(0, 0);
		simuObjA.moveTo(locationA);
		displayer.addObjectAt(simuObjA, locationA);
		
		SimuObject simuObjB = new Obj(Color.BLUE);
		Location locationB = new Location(99, 59);
		simuObjB.moveTo(locationB);
		displayer.addObjectAt(simuObjB, locationB);
		
		SimuObject simuObjC = new Obj(Color.BLACK, new Oval());
		Location locationC = new Location(99, 59);
		simuObjC.moveTo(locationC);
		displayer.addObjectAt(simuObjC, locationC);
		
		displayer.display();
	}

}

class Obj extends SimuObject {
	public Obj(Color color) {
		super.color = color;
	}
	public Obj(Color color, Painter painter) {
		super.color = color;
		super.painter = painter;
	}
}
