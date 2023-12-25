package simulator;

import constants.Constants;
import entities.Location;
import entities.SimuObject;
import entities.Size;
import interfaces.Painter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Display the main window of the simulation.
 * 
 * @author pluseven
 */
public class Displayer extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Size numCR = Constants.NUM_OF_COL_ROW;
	private Size gridSize = Constants.INIT_GRID_SIZE;
	private Size totalSize = new Size();
	private DisplayArea displayArea;
	private GridMap gridMap;

	/**
	 * Create a simulator window.
	 */
	public Displayer() {
		totalSize.update(numCR.getWidth() * gridSize.getWidth(), numCR.getHeight() * gridSize.getHeight());

		setTitle("simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Create Simulator menu
		JMenu simulatorMenu = new JMenu("Simulator");
		menuBar.add(simulatorMenu);

		// Add Simulator menu items
		JMenuItem startMenuItem = new JMenuItem("Start");
		JMenuItem stepMenuItem = new JMenuItem("Step");
		JMenuItem stopMenuItem = new JMenuItem("Stop");
		simulatorMenu.add(startMenuItem);
		simulatorMenu.add(stepMenuItem);
		simulatorMenu.add(stopMenuItem);

		// Add Simulator menu item listeners
		startMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO start menu item
				showMenuItemName("start");
			}
		});

		stepMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO step menu item
				showMenuItemName("step");
			}
		});

		stopMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO show menu item
				showMenuItemName("stop");
			}
		});

		// Create Window menu
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);

		// Add Window menu items
		JMenuItem packMenuItem = new JMenuItem("Pack");
		windowMenu.add(packMenuItem);

		// Add Window menu item listeners
		packMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pack();
			}
		});

		setLayout(new BorderLayout());

		displayArea = new DisplayArea();
		add(displayArea, BorderLayout.CENTER);

		// Add component listener for resize operate
		addComponentListener(new ComponentAdapter() {

			/**
			 * Resize the display area and all the simulation objects' view when the window
			 * is resized.
			 * 
			 * @param e component event.
			 */
			@Override
			public void componentResized(ComponentEvent e) {
				double xScale = (double) getWidth() / totalSize.getWidth();
				double yScale = (double) getHeight() / totalSize.getHeight();
				double scale = Math.min(xScale, yScale);
				int newHeight = totalSize.getHeight(), newWidth = totalSize.getWidth();
				int tmpTotalWidth = (int) (totalSize.getWidth() * scale);
				int tmpTotalHeight = (int) (totalSize.getHeight() * scale);
				// Ensure that the view of the simulator object is still an integer after being
				// resized.
				for (int i = tmpTotalWidth; i >= 0; i--)
					if (i % numCR.getWidth() == 0) {
						newWidth = i;
						break;
					}

				for (int i = tmpTotalHeight; i >= 0; i--)
					if (i % numCR.getHeight() == 0) {
						newHeight = i;
						break;
					}

				// Resize the display area.
				displayArea.setPreferredSize(new Dimension(newWidth, newHeight));

				// Resize all the simulation objects' view.
				totalSize.update(newWidth, newHeight);
				int newGridWidth = newWidth / numCR.getWidth();
				int newGridHeight = newHeight / numCR.getHeight();
				gridSize.update(newGridWidth, newGridHeight);
				displayArea.repaint();
			}
		});

		// Add a panel for the bottom area
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Add the speed label and value
		JLabel speedLabel = new JLabel("Speed:");
		// TODO display speed
		JLabel speedValueLabel = new JLabel(Integer.toString(0));

		bottomPanel.add(speedLabel);
		bottomPanel.add(speedValueLabel);

		// Add the bottom panel to the frame
		add(bottomPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	/**
	 * Display the name of the menu item in a message dialog for testing.
	 * 
	 * @param menuItemName the name of the menu item.
	 */
	private void showMenuItemName(String menuItemName) {
		JOptionPane.showMessageDialog(this, "Menu Item Clicked: " + menuItemName);
	}

	/**
	 * Display the window.
	 */
	public void display() {
		setVisible(true);
	}

	private class DisplayArea extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private Image image;
		private Graphics graphic;

		private DisplayArea() {
			setPreferredSize(new Dimension(totalSize.getWidth(), totalSize.getHeight()));
		}

		public Dimension getPreferredSize() {
			return new Dimension(totalSize.getWidth(), totalSize.getHeight());
		}

		public void refresh() {
			image = createImage(totalSize.getWidth(), totalSize.getHeight());
			graphic = image.getGraphics();
		}

		public void drawObject(SimuObject simuObj) {
			Painter painter = simuObj.getPainter();
			Location location = simuObj.getLocation();
			int x = location.getCol() * gridSize.getWidth();
			int y = location.getRow() * gridSize.getHeight();
			graphic.setColor(simuObj.getColor());
			painter.paint(graphic, x, y, gridSize.getWidth(), gridSize.getHeight());
		}

		public void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}

}
