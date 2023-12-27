package test;

import java.awt.Color;

import entities.*;
import entities.painters.*;
import interfaces.Painter;
import simulator.Displayer;
import simulator.GridMap;

public class DisplayerTest {

	public static void main(String[] args) {
		Displayer displayer = new Displayer();
		
		Line line = new Line();
		line.addDirection(0, -1);
		line.addDirection(1, 0);
		SimuObject simuObjA = new Obj(Color.BLACK, line, displayer.getGridMap());
		Location locationA = new Location(0, 0);
		simuObjA.moveTo(locationA);
		displayer.addObjectAt(simuObjA, locationA);
		
		SimuObject simuObjB = new Obj(Color.BLUE, new Rectangle(), displayer.getGridMap());
		Location locationB = new Location(49, 29);
		simuObjB.moveTo(locationB);
		displayer.addObjectAt(simuObjB, locationB);
		
		SimuObject simuObjC = new Obj(Color.RED, new Oval(), displayer.getGridMap());
		Location locationC = new Location(49, 29);
		simuObjC.moveTo(locationC);
		displayer.addObjectAt(simuObjC, locationC);
		
		simuObjA.moveTo(new Location(10, 10));
		
		displayer.display();
	}

}

class Obj extends SimuObject {
	public Obj(Color color, Painter painter, GridMap gridMap) {
		super.color = color;
		super.painter = painter;
		super.gridMap = gridMap;
	}
	@Override
	public void forward() {
		// TODO Auto-generated method stub
		
	}
}
