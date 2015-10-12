import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * 
 * @author Jompol Sermsook 5610450063
 *		        จอมพล	เสริมสุข 
 */

public class Box {
	private Integer x, y, c;
	private Graphics2D g2;
	private Color[] color = new Color[4];
	public Box(int xL, int yL, int c) {
		this.x = xL;
		this.y = yL;
		this.c = c;
		this.color[0] = Color.BLUE;
		this.color[1] = Color.RED;
		this.color[2] = Color.CYAN;
		this.color[3] = Color.MAGENTA;
	}
	public void draw(Graphics g) {
		this.g2 = (Graphics2D) g;
		g2.setColor(this.color[c]);
		g2.fillRect(x, y, 25, 25);
	}
	public void translate(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
