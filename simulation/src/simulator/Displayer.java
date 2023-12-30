package simulator;

import constants.Constants;
import entities.Location;
import entities.SimuObject;
import entities.Size;
import interfaces.Painter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Iterator;

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

	private DisplayArea displayArea;

	/**
	 * Create a simulator window.
	 */
	public Displayer() {

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
		JMenuItem zoomInMenuItem = new JMenuItem("Zoom In(+)");
		JMenuItem zoomOutMenuItem = new JMenuItem("Zoom Out(-)");
		JMenuItem fixMenuItem = new JMenuItem("Fix");
		windowMenu.add(packMenuItem);
		windowMenu.add(zoomInMenuItem);
		windowMenu.add(zoomOutMenuItem);
		windowMenu.add(fixMenuItem);

		// Add Window menu item listeners
		packMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pack();
				displayArea.refresh();
				displayArea.drawAll();
				displayArea.repaint();
			}
		});
		
		zoomInMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayArea.zoom(Constants.ZOOM_IN);
			}
		});
		
		zoomOutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayArea.zoom(Constants.ZOOM_OUT);
			}
		});
		
		fixMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayArea.fix();
			}
		});
		
		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				displayArea.fix();
			}
		});

		setLayout(new BorderLayout());

		displayArea = new DisplayArea();
		getContentPane().add(displayArea, BorderLayout.CENTER);

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
	 * Return the gridMap of displayArea.
	 * 
	 * @return the grid map.
	 */
	public GridMap getGridMap() {
		return displayArea.gridMap;
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
		displayArea.refresh();
		displayArea.drawAll();
		displayArea.repaint();
		setVisible(true);
	}

	public void addObjectAt(SimuObject simuObj, Location location) {
		displayArea.gridMap.addObjectAt(simuObj, location);
	}

	private class DisplayArea extends JPanel {

		private static final long serialVersionUID = 1L;

		private final Size numCR = Constants.NUM_OF_COL_ROW;
		private Size gridSize = Constants.INIT_GRID_SIZE;
		private Size totalSize = new Size();
		private Image image;
		private Graphics graphic;
		private GridMap gridMap;

		private DisplayArea() {
			this(new GridMap());
		}

		private DisplayArea(GridMap gridMap) {
			totalSize.update(numCR.getWidth() * gridSize.getWidth(), numCR.getHeight() * gridSize.getHeight());
			this.gridMap = gridMap;
			setPreferredSize(new Dimension(totalSize.getWidth(), totalSize.getHeight()));
		}
		
		private void fix() {
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
			display();
		}
		
		private void zoom(boolean zoomIn) {
			int newGridWidth = gridSize.getWidth() + (zoomIn ? 1 : -1);
			int newGridHeight = gridSize.getHeight() + (zoomIn ? 1 : -1);
			gridSize.update(newGridWidth, newGridHeight);
			totalSize.update(newGridWidth * numCR.getWidth(), newGridHeight * numCR.getHeight());
			setSize(new Dimension(totalSize.getWidth(), totalSize.getHeight()));
			display();
		}

		private void refresh() {
			image = createImage(totalSize.getWidth(), totalSize.getHeight());
			graphic = image.getGraphics();
		}

		private void drawObject(SimuObject simuObj) {
			Painter painter = simuObj.getPainter();
			Location location = simuObj.getLocation();
			int x = location.getCol() * gridSize.getWidth();
			int y = location.getRow() * gridSize.getHeight();
			graphic.setColor(simuObj.getColor());
			painter.paint(graphic, x, y, gridSize.getWidth(), gridSize.getHeight());
		}

		private void drawAll() {
			for (Iterator<SimuObject> iter : gridMap.getAllIterator()) {
				while (iter.hasNext()) {
					SimuObject simuObj = iter.next();
					drawObject(simuObj);
				}
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(totalSize.getWidth(), totalSize.getHeight());
		}

		public void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}

}
