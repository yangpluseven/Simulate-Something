package test;

import simulator.SimulatorView;
import entities.Location;
import entities.View;

import javax.swing.SwingUtilities;

/**
 * Test for the GUI.
 * 
 * @author pluseven
 */
public class GUITest {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			SimulatorView simuView = new SimulatorView();
			Location location = new Location(99, 59);
			View view = new View(simuView);
			view.setLocation(location);
			simuView.addView(view);
//			view.delete(simuView);
		});
	}

}
