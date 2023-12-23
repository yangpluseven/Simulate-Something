package simulator;

import constants.Constants;
import entities.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Display the main window of the simulation.
 * 
 * @author pluseven
 */
public class SimulatorView extends JFrame {

	private static final long serialVersionUID = 1L;

	private final int numCols = Constants.NUM_COLS;
	private final int numRows = Constants.NUM_ROWS;
	private int gridWidth = Constants.INIT_GRID_WIDTH;
	private int gridHeight = Constants.INIT_GRID_HEIGHT;
	private int totalWidth;
	private int totalHeight;
	private JPanel displayArea;

	/**
	 * Create a simulator window.
	 */
	public SimulatorView() {
		this.totalWidth = gridWidth * numCols;
		this.totalHeight = gridHeight * numRows;

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

		displayArea = new JPanel();
		displayArea.setPreferredSize(new Dimension(totalWidth, totalHeight));
		displayArea.setLayout(new BorderLayout());
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
				double xScale = (double) getWidth() / totalWidth;
				double yScale = (double) getHeight() / totalHeight;
				double scale = Math.min(xScale, yScale);
				int newHeight = totalHeight, newWidth = totalWidth;
				int tmpTotalWidth = (int) (totalWidth * scale);
				int tmpTotalHeight = (int) (totalHeight * scale);
				// Ensure that the view of the simulator object is still an integer after being
				// resized.
				for (int i = tmpTotalWidth; i >= 0; i--)
					if (i % numCols == 0) {
						newWidth = i;
						break;
					}

				for (int i = tmpTotalHeight; i >= 0; i--)
					if (i % numRows == 0) {
						newHeight = i;
						break;
					}

				// Resize the display area.
				displayArea.setPreferredSize(new Dimension(newWidth, newHeight));

				// Resize all the simulation objects' view.
				int newGridWidth = newWidth / numCols;
				int newGridHeight = newHeight / numRows;
				Component[] components = displayArea.getComponents();
				for (Component component : components) {
					if (component instanceof View) {
						View view = (View) component;
						view.updateSize(newGridWidth, newGridHeight);
					}
				}
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

	
	/**
	 * Add a simulation objects' view.
	 * 
	 * @param view
	 */
	public void addView(View view) {
		displayArea.add(view);
		revalidate();
		repaint();
	}

	/**
	 * Getter for girdWidth.
	 * 
	 * @return gridWidth
	 */
	public int getGridWidth() {
		return gridWidth;
	}

	/**
	 * Getter for gridHeight
	 * 
	 * @return gridHeight
	 */
	public int getGridHeight() {
		return gridHeight;
	}
}
