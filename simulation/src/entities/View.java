package entities;

import java.awt.Graphics;
import javax.swing.JPanel;

import constants.Constants;
import entities.painters.Rectangle;
import interfaces.Painter;
import simulator.SimulatorView;

/**
 * View is a part of the simulation object. A instance of View can disaplay a
 * SimuObject on the displayArea in a simulatorView(as a simple graphic like
 * rectangle).
 * 
 * @author pluseven
 */
public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	private int gridX;
	private int gridY;
	private int gridW;
	private int gridH;
	private Painter painter;

	/**
	 * Default constructor.
	 */
	public View() {
		gridW = Constants.INIT_GRID_WIDTH;
		gridH = Constants.INIT_GRID_HEIGHT;
		painter = new Rectangle();
	}

	/**
	 * Create view using the gridWidth and gridHeight in a simulatorView.
	 * 
	 * @param simuView
	 */
	public View(SimulatorView simuView) {
		gridW = simuView.getGridWidth();
		gridH = simuView.getGridHeight();
		painter = new Rectangle();
	}

	/**
	 * Create view using the gridWidth and gridHeight in a simulatorView and a
	 * specified painter.
	 * 
	 * @param simuView
	 */
	public View(SimulatorView simuView, Painter painter) {
		this.gridW = simuView.getGridWidth();
		this.gridH = simuView.getGridHeight();
		this.painter = painter;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		painter.paint(g, gridX, gridY, gridW, gridH);
	}

	/**
	 * Update the state of this view using width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public void updateSize(int width, int height) {
		gridX = (gridX / gridW) * width;
		gridY = (gridY / gridH) * height;
		gridW = width;
		gridH = height;
		repaint();
	}

	/**
	 * Update the state of this view using x and y.
	 * 
	 * @param x
	 * @param y
	 */
	public void updateCoordinate(int x, int y) {
		gridW = x / (gridX / gridW);
		gridH = y / (gridY / gridH);
		gridX = x;
		gridY = y;
		repaint();
	}

	/**
	 * Set the location of this view.
	 * 
	 * @param location
	 */
	public void setLocation(Location location) {
		gridX = location.getCol() * gridW;
		gridY = location.getRow() * gridH;
		repaint();
	}

	/**
	 * Delete this view in the simulatorView.
	 * 
	 * @param simuView the simulatorView.
	 */
	public void delete(SimulatorView simuView) {
		simuView.remove(this);
		simuView.revalidate();
		simuView.repaint();
	}
}
