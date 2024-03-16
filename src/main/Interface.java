package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.AlphaComposite;

public class Interface {
	
	Graphics2D g2;
	GamePanel gp;
	Font arial_40;
	boolean test = false;
	boolean menu = false;
	public BufferedImage W, A, S, D, SHIFT, backspaceLeft, backspaceRight;
	public int row = 0;
	public int col = 0;
	Font ueberschrift = new Font("Arial", Font.PLAIN, 25);
	Font menuUeberschrift = new Font("Arial", Font.PLAIN, 35);
	Font text = new Font("Arial", Font.PLAIN, 15);
	Color transparentBlack = new Color(0, 0, 0, (int) ((float) 0.85 * 255));
	Color transparentGrey = new Color(0, 0, 0, (int) ((float) 0.50 * 255));
	Color transparentDarkGrey = new Color(0, 0, 0, (int) ((float) 0.65 * 255));
	
	public Interface(GamePanel gp) {
		this.gp = gp;
		getImages();
	}
	
	public void getImages() {
		try {
			
			W = ImageIO.read(getClass().getResourceAsStream("/tiles/W.png"));
			A = ImageIO.read(getClass().getResourceAsStream("/tiles/A.png"));
			S = ImageIO.read(getClass().getResourceAsStream("/tiles/S.png"));
			D = ImageIO.read(getClass().getResourceAsStream("/tiles/D.png"));
			SHIFT = ImageIO.read(getClass().getResourceAsStream("/tiles/Shift.png"));
			backspaceLeft = ImageIO.read(getClass().getResourceAsStream("/tiles/backspaceLeft.png"));
			backspaceRight = ImageIO.read(getClass().getResourceAsStream("/tiles/backspaceRight.png"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawMenu(Graphics2D g2) {
			if(gp.keyH.escPressed == true) {
			g2.setColor(transparentGrey);
			g2.fillRoundRect(0, 0, gp.worldWidth, gp.worldHeight, 10, 10);
			g2.setColor(transparentDarkGrey);
			g2.fillRoundRect(192, 144, 1054, 575, 10, 10);
			
			Color white = new Color(255, 255, 255);
			g2.setColor(white);
			g2.setFont(menuUeberschrift);
			g2.drawString("Menu", 660, 192);
			g2.setColor(white);
			g2.setFont(text);
			g2.drawString("press ESC to close", 643, 690);
			g2.drawString("KILL", gp.tileSize *11, gp.tileSize *6);
			g2.drawString("WALK", gp.tileSize *8, gp.tileSize *6);
			g2.drawString("SHIFT", gp.tileSize *5, gp.tileSize *7);
			g2.drawImage(W, gp.tileSize *8, gp.tileSize *6, gp.tileSize, gp.tileSize, null);
			g2.drawImage(A, gp.tileSize *7, gp.tileSize *7, gp.tileSize, gp.tileSize, null);
			g2.drawImage(S, gp.tileSize *8, gp.tileSize *7, gp.tileSize, gp.tileSize, null);
			g2.drawImage(D, gp.tileSize *9, gp.tileSize *7, gp.tileSize, gp.tileSize, null);
			g2.drawImage(SHIFT, gp.tileSize *5, gp.tileSize *7, gp.tileSize, gp.tileSize, null);
			g2.drawImage(backspaceLeft, gp.tileSize *11, gp.tileSize *6, gp.tileSize, gp.tileSize, null);
			g2.drawImage(backspaceRight, gp.tileSize *12, gp.tileSize *6, gp.tileSize, gp.tileSize, null);
			gp.animationPause = true;
		}
		if(gp.keyH.stopAnimation == false) {
			if(gp.keyH.lever == false) {
				gp.animationPause = false;
				gp.animation.counter = 0;
				gp.keyH.lever = true;
			}
		}
	}
	
	public void drawStats(Graphics2D g2) {;
        g2.setColor(transparentBlack);
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.85);
        g2.setComposite(alphaComposite);
		g2.fillRoundRect(440, 235, 500, 550, 10, 10);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}
	
	public void Rubiencounter(Graphics2D g2) {
		Color white = new Color(255, 255, 255);
		g2.setColor(white);
		g2.setFont(ueberschrift);
		g2.drawString("Rubienegesammelt: " + gp.player.rubinCounter, 460, 280);
	}
	
	public void Zeit(Graphics2D g2) {
		Color white = new Color(255, 255, 255);
		g2.setColor(white);
		g2.setFont(ueberschrift);
		g2.drawString("Zeit: ", 460, 380);
	}
	
	public void Todesgrund(Graphics2D g2) {
		Color white = new Color(255, 255, 255);
		g2.setColor(white);
		g2.setFont(ueberschrift);
		g2.drawString("Todesursache: " + "", 460, 380);
	}
	
	public void Controls(Graphics2D g2) {
		Color white = new Color(255, 255, 255);
		g2.setColor(white);
		g2.setFont(ueberschrift);
		g2.drawString("Todesursache: " + "", 460, 380);
	}
	
	public void ESC(Graphics2D g2) {
		Color white = new Color(255, 255, 255);
		g2.setColor(white);
		g2.setFont(ueberschrift);
		g2.drawString("Press ESC to close", 660, 380);
	}
	
	public void draw(Graphics2D g2) {
		drawMenu(g2);
		if(test == true) {
			drawStats(g2);
			Rubiencounter(g2);
			Zeit(g2);
		}
	}
}