package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Interface {
	
	Graphics2D g2;
	GamePanel gp;
	Font arial_40;
	
	public Interface() {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);

	}
	
	public void menu() {
		int x = 111;
		int y = 111;
		int width = 111;
		int height = 111;
		drawWindow(x, y, width, height);
	}
	public void drawWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0);
		g2.setColor(c);
		g2.fillRect(x, y, width, height);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
//		if(gp.player.menu == true) {
//			menu();
//		}
	}
}