package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {
	
	private GamePanel gp;
	
	public BufferedImage youwon;
	public boolean endanimation = false;
	private int screenX, screenY, endScreenWidth, endScreenHeigth;
	private int counter = 0;
	private boolean tp = false;
	
	public Animation(GamePanel gp) {
		this.gp = gp;
		
		getImages();
		getDefaultValues();
	}
	
	public void getImages() {
		try {
			
			youwon = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youwon.png"));
			
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startEndanimation() {
		gp.animationPause = true;
		endanimation = true;
	}
	
	public void getDefaultValues() {
		endScreenWidth = 16 * gp.tileSize;
		endScreenHeigth = 8 * gp.tileSize;
		screenX = (gp.maxScreenCol * gp.tileSize) / 2 - endScreenWidth / 2;
		screenY = (gp.maxScreenRow * gp.tileSize) / 2 - endScreenHeigth / 2;
	}
	
	public void update() {
		if(tp) {
			counter++;
			tp = false;
		}
		else {
			tp = true;
		}
		if(40 < counter  && counter < 58) {
			endScreenWidth = endScreenWidth - (counter - 40) * 2;
			endScreenHeigth = endScreenHeigth - (counter - 40);
			screenX = (gp.maxScreenCol * gp.tileSize) / 2 - endScreenWidth / 2;
			screenY = (gp.maxScreenRow * gp.tileSize) / 2 - endScreenHeigth / 2 - (counter - 40) * 18;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		if(endanimation == true) {
			g2.drawImage(youwon, screenX, screenY, endScreenWidth, endScreenHeigth, null);
		}
	}
}
