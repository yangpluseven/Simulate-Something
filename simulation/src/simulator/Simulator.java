package simulator;

import java.util.Iterator;

import entities.SimuObject;

// TODO Simulator doc
public class Simulator {

	private GridMap gridMap;
	private Displayer displayer;
	private Reporter reporter;
	
	public Simulator() {
		// TODO Auto-generated constructor stub
	}
	
	public void simulateOneStep() {
		for (Iterator<SimuObject> iter : gridMap.getAllIterator()) {
			while (iter.hasNext()) {
				SimuObject simuObj = iter.next();
				simuObj.forward();
			}
		}
		// TODO report
		// TODO display
	}

}
