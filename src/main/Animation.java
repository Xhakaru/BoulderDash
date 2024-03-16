package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {
	
	private GamePanel gp;
	
	public BufferedImage youwon;
	public BufferedImage explosion, explosion_dark_f1, explosion_dark_f2, explosion_dark_f3, explosion_dark_f4, explosion_dark_f5, explosion_dark_f6;
	public BufferedImage youlose, youlose_f1, youlose_f2, youlose_f3, youlose_f4, youlose_f5, youlose_f6, youlose_f7, youlose_f8, youlose_f9, youlose_f10, youlose_f11;
	public boolean endanimation = false;
	public boolean lebenZeroanimation = false;
	private boolean deathanimation = false;
	private int screenX, screenY, screenXb, screenYb, endScreenWidth, endScreenHeigth, lebenZeroScreenWidth, lebenZeroScreenHeigth;
	public int counter = 0;
	private boolean tp = false;
	private String grund;
	private String direction;
	private int spriteCounterExplosion = 0;
	private int spriteNumExplosion = 0;
	private int spriteCounterYouLose = 0;
	private int spriteNumYoulose = 0;
	
	public Animation(GamePanel gp) {
		this.gp = gp;
		
		getImages();
		getDefaultValues();
	}
	
	public void getImages() {
		try {
			
			youwon = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youwon_.png"));
			
			explosion_dark_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f1.png"));
			explosion_dark_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f2.png"));
			explosion_dark_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f3.png"));
			explosion_dark_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f4.png"));
			explosion_dark_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f5.png"));
			explosion_dark_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f6.png"));
			
			youlose_f1 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f1.png"));
			youlose_f2 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f2.png"));
			youlose_f3 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f3.png"));
			youlose_f4 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f4.png"));
			youlose_f5 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f5.png"));
			youlose_f6 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f6.png"));
			youlose_f7 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f7.png"));
			youlose_f8 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f8.png"));
			youlose_f9 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f9.png"));
			youlose_f10 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f10.png"));
			youlose_f11 = ImageIO.read(getClass().getResourceAsStream("/animations/endanimation/youlose/youlose_f11.png"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startEndanimation() {
		gp.animationPause = true;
		endanimation = true;
	}
	
	public void startDeathanimation(String grund, int colb, int rowb, String direction) {
		this.grund = grund;
		this.direction = direction;
		screenXb = colb * gp.tileSize;
		screenYb = rowb * gp.tileSize;
		
		gp.animationPause = true;
		deathanimation = true;
	}
	
	public void getDefaultValues() {
		endScreenWidth = 16 * gp.tileSize;
		endScreenHeigth = 8 * gp.tileSize;
		lebenZeroScreenWidth = 16 * gp.tileSize;
		lebenZeroScreenHeigth = 8 * gp.tileSize;
		screenX = (gp.maxScreenCol * gp.tileSize) / 2 - endScreenWidth / 2;
		screenY = (gp.maxScreenRow * gp.tileSize) / 2 - endScreenHeigth / 2;
	}
	
	public void explosionSprite() {
		spriteCounterExplosion++;
		if(spriteCounterExplosion >= 4) {
			spriteNumExplosion++;
			switch(spriteNumExplosion){
				case(1):
					explosion = gp.tileM.tile[5].image;
					break;
				case(2):
					explosion = explosion_dark_f1;
					break;
				case(3):
					explosion = explosion_dark_f1;
					break;
				case(4):
					explosion = explosion_dark_f2;
					break;
				case(5):
					explosion = explosion_dark_f2;
					break;
				case(6):
					explosion = explosion_dark_f3;
					break;
				case(7):
					explosion = explosion_dark_f3;
					break;
				case(8):
					explosion = explosion_dark_f4;
					break;
				case(9):
					explosion = explosion_dark_f4;
					break;
				case(10):
					explosion = explosion_dark_f5;
					break;
				case(11):
					explosion = explosion_dark_f5;
					break;
				case(12):
					explosion = explosion_dark_f5;
					break;
				case(13):
					explosion = explosion_dark_f6;
					break;
				case(14):
					explosion = explosion_dark_f6;
					break;
				case(15):
					explosion = explosion_dark_f6;
					break;
				case(16):
					explosion = explosion_dark_f6;
					break;
				case(17):
					explosion = gp.tileM.tile[5].image;
					break;
				case(18):
					explosion = gp.tileM.tile[5].image;
					break;
				case(19):
					explosion = gp.tileM.tile[5].image;
					break;
				case(20):
					explosion = gp.tileM.tile[5].image;
					break;
				case(21):
					explosion = gp.tileM.tile[5].image;
					break;
				case(22):
					explosion = gp.tileM.tile[5].image;
					break;
			}
			
			if(spriteNumExplosion == 22) {
				spriteNumExplosion = 1;
			}
			spriteCounterExplosion = 0;
		}
	}
	
	public void youloseSprite() {
		spriteCounterYouLose++;
		if(spriteCounterYouLose >= 4) {
			spriteNumYoulose++;
			switch(spriteNumYoulose){
				case(1):
					youlose = youlose_f1;
					break;
				case(2):
					youlose = youlose_f2;
					break;
				case(3):
					youlose = youlose_f3;
					break;
				case(4):
					youlose = youlose_f4;
					break;
				case(5):
					youlose = youlose_f5;
					break;
				case(6):
					youlose = youlose_f6;
					break;
				case(7):
					youlose = youlose_f7;
					break;
				case(8):
					youlose = youlose_f8;
					break;
				case(9):
					youlose = youlose_f9;
					break;
				case(10):
					youlose = youlose_f10;
					break;
				case(11):
					youlose = youlose_f11;
					break;
			}
			
			if(spriteNumYoulose == 11) {
				spriteNumYoulose = 1;
			}
			spriteCounterYouLose = 0;
		}
	}
	
	public void update() {
		youloseSprite();
		
		if(tp) {
			counter++;
			tp = false;
		}
		else {
			tp = true;
		}
		
		if(deathanimation) {
			explosionSprite();
			if(counter >= 42) {
				if(gp.player.leben == 0) {
					deathanimation = false;
					counter = 0;
					lebenZeroanimation = true;
				}
				else {
					gp.animationPause = false;
					deathanimation = false;
					gp.player.sterben2(grund);
					counter = 0;
				}
			}
		}
		
		if(endanimation == true) {
			if(40 < counter  && counter < 56) {
				endScreenWidth = endScreenWidth - (counter - 40) * 2;
				endScreenHeigth = endScreenHeigth - (counter - 40);
				screenX = (gp.maxScreenCol * gp.tileSize) / 2 - endScreenWidth / 2;
				screenY = (gp.maxScreenRow * gp.tileSize) / 2 - endScreenHeigth / 2 - (counter - 40) * 18;
			}
		}
		
		if(lebenZeroanimation == true) {
			if(40 < counter  && counter < 56) {
				lebenZeroScreenWidth = lebenZeroScreenWidth - (counter - 40) * 2;
				lebenZeroScreenHeigth = lebenZeroScreenHeigth - (counter - 40);
				screenX = (gp.maxScreenCol * gp.tileSize) / 2 - lebenZeroScreenWidth / 2;
				screenY = (gp.maxScreenRow * gp.tileSize) / 2 - lebenZeroScreenHeigth / 2 - (counter - 40) * 18;
			}
			if(200 < counter) {
				lebenZeroanimation = false;
				gp.animationPause = false;
				gp.player.sterben2(grund);
				counter = 0;
				getDefaultValues();
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		if(gp.animationPause) {
			if(deathanimation == true) {
				if(direction == "null") {
					if(gp.tileM.counterXkameraPos == true) {
						gp.player.screenX += gp.tileM.actualXkameraMovement;
					}
					else {
						gp.player.screenX -= gp.tileM.actualXkameraMovement;
					}
					
					if(gp.tileM.counterYkameraPos == true) {
						gp.player.screenY += gp.tileM.actualYkameraMovement;
					}
					else {
						gp.player.screenY -= gp.tileM.actualYkameraMovement;
					}
					g2.drawImage(explosion, gp.player.screenX, gp.player.screenY, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX, gp.player.screenY - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX, gp.player.screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX - gp.tileSize, gp.player.screenY, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX - gp.tileSize, gp.player.screenY - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX - gp.tileSize, gp.player.screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX + gp.tileSize, gp.player.screenY, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX + gp.tileSize, gp.player.screenY - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, gp.player.screenX + gp.tileSize, gp.player.screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
					if(gp.tileM.counterXkameraPos == true) {
						gp.player.screenX -= gp.tileM.actualXkameraMovement;
					}
					else {
						gp.player.screenX += gp.tileM.actualXkameraMovement;
					}
					
					if(gp.tileM.counterYkameraPos == true) {
						gp.player.screenY -= gp.tileM.actualYkameraMovement;
					}
					else {
						gp.player.screenY += gp.tileM.actualYkameraMovement;
					}
				}
				else {
					g2.drawImage(explosion, screenXb, screenYb, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb - gp.tileSize, screenYb, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb - gp.tileSize, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb - gp.tileSize, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb + gp.tileSize, screenYb, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb + gp.tileSize, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
					g2.drawImage(explosion, screenXb + gp.tileSize, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
					switch(direction) {
						case("N"):
							g2.drawImage(explosion, screenXb, screenYb + 2 * gp.tileSize, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb - gp.tileSize, screenYb + 2 * gp.tileSize, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb + gp.tileSize, screenYb + 2 * gp.tileSize, gp.tileSize, gp.tileSize, null);
							break;
						case("O"):
							g2.drawImage(explosion, screenXb - 2 * gp.tileSize, screenYb, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb - 2 * gp.tileSize, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb - 2 * gp.tileSize, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
							break;
						case("S"):
							g2.drawImage(explosion, screenXb, screenYb - 2 * gp.tileSize, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb - gp.tileSize, screenYb - 2 * gp.tileSize, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb + gp.tileSize, screenYb - 2 * gp.tileSize, gp.tileSize, gp.tileSize, null);
							break;
						case("W"):
							g2.drawImage(explosion, screenXb + 2 * gp.tileSize, screenYb, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb + 2 * gp.tileSize, screenYb - gp.tileSize, gp.tileSize, gp.tileSize, null);
							g2.drawImage(explosion, screenXb + 2 * gp.tileSize, screenYb + gp.tileSize, gp.tileSize, gp.tileSize, null);
							break;
					}
				}
			}
			
			if(endanimation == true) {
				g2.drawImage(youwon, screenX, screenY, endScreenWidth, endScreenHeigth, null);
			}
			
			if(lebenZeroanimation == true) {
				g2.drawImage(youlose, screenX, screenY, lebenZeroScreenWidth, lebenZeroScreenHeigth, null);
			}
		}
	}
}
