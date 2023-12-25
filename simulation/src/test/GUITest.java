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
			f.p.drawRec(0, 0, Color.BLUE);
			f.p.drawCir(0, 0, Color.BLACK);
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
		setPreferredSize(new Dimension(200, 150));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 150);
	}
	
	public void prepare() {
		i = createImage(200, 150);
		g = i.getGraphics();
	}
	
	public void drawRec(int x, int y, Color color) {
		g.setColor(color);
		g.fillRect(x, y, 30, 30);
	}
	
	public void drawCir(int x, int y, Color color) {
		g.setColor(color);
		g.fillOval(x, y, 30, 30);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
	}

}
