package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Test for the GUI.
 * 
 * @author pluseven
 */
public class GUITest {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			F f = new F();
			f.p.prepare();
			f.p.draw(0, 0, Color.BLUE);
			f.p.draw(10, 10, Color.BLACK);
		});
	}

}

class F extends JFrame {
	
	public P p;
	
	public F() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new P();
		add(p, BorderLayout.CENTER);
		pack();
		setVisible(true);
		p.repaint();
	}
	
}

class P extends JPanel {

	public Image i;
	public Graphics g;

	public P() {
		setPreferredSize(new Dimension(100, 60));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(100, 60);
	}
	
	public void prepare() {
		i = createImage(100, 60);
		g = i.getGraphics();
	}
	
	public void draw(int x, int y, Color color) {
		g.setColor(color);
		g.fillRect(x, y, 10, 10);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
	}

}
