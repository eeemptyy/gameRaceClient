import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * 
 * @author Jompol Sermsook 5610450063
 *		        จอมพล	เสริมสุข 
 */

public class BoxComponent extends JComponent{
	private static final long serialVersionUID = 1L;
	private Box box;
	private Graphics2D g2;
	
	public BoxComponent(int x, int y,int c){
		box = new Box(x, y, c);
	}

	public void paintComponent(Graphics g){
		g2 = (Graphics2D) g;
		box.draw(g2);
	}

	public void run(int x, int y) {
		box.translate(x, y);
		removeAll();
		revalidate();
		repaint();
	}


}
