package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Rubin extends Entity{
	
	private GamePanel gp;
	
	private int screenX;
	private int screenY;
	private int fallDelayCounter = 0;
	private int fallDelayTarget;
	private boolean isFalling;
	private int spriteCounter = 0;
	private int spriteNum = 0;
	private int spriteCTarget = 16;
	
	private BufferedImage rubin_f1, rubin_f2, rubin_f3, rubin_f4, rubin_f5, rubin_f6, rubin_f7, rubin_f8;
	
	public Rubin(GamePanel gp, int worldX, int worldY) {
		this.gp = gp;
		this.worldX = worldX;
		this.worldY = worldY;
		
		setDefaultValues();
		getStoneImage();
	}
	
	public void setDefaultValues(){
		direction = "down";
		isFalling = false;
		fallDelayTarget = 12;
	}
	
	public void getStoneImage() {
		
		try {
			
			rubin_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f1.png"));
			rubin_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f2.png"));
			rubin_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f3.png"));
			rubin_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f4.png"));
			rubin_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f5.png"));
			rubin_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f6.png"));
			rubin_f7 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f7.png"));
			rubin_f8 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f8.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setIsFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}
	
	public boolean getIsFalling() {
		return isFalling;
	}
	
	public int getScreenX() {
		return screenX;
	}
	
	public int getScreenY() {
		return screenY;
	}
	
	public BufferedImage getImage() {
		switch(spriteNum) {
			case(1):
				return rubin_f1;
			case(2):
				return rubin_f1;
			case(3):
				return rubin_f2;
			case(4):
				return rubin_f3;
			case(5):
				return rubin_f4;
			case(6):
				return rubin_f5;
			case(7):
				return rubin_f6;
			case(8):
				return rubin_f7;
			case(9):
				return rubin_f8;
			case(10):
				return rubin_f1;
			case(11):
				return rubin_f1;
			case(12):
				return rubin_f1;
			case(13):
				return rubin_f1;
			case(14):
				return rubin_f1;
			case(15):
				return rubin_f1;
			case(16):
				return rubin_f1;
		}
		return null;
	}
	
	public void rubinFall() {
		int colP = gp.player.worldX / gp.tileSize;
		int rowP = gp.player.worldY / gp.tileSize;
		int col = worldX / gp.tileSize;
		int row = worldY / gp.tileSize;
		
		//Spieler unter Rubin?
		boolean playerUnderRubin;
		if(colP == col && rowP == row + 1) {
			playerUnderRubin = true;
		}
		else {
			playerUnderRubin = false;
		}
		
		if(isFalling && playerUnderRubin) {
			gp.player.sterben("Rubin Air", 0, 0, "null");
		}
		else if(colP == col && rowP == row) {
			gp.rubinM.removeMe(worldX, worldY);
		}
		else if(!playerUnderRubin) {
			if(gp.tileM.mapTileNum[col][row + 1] == gp.tileM.backBlackID) {
				isFalling = true;
				if(fallDelayCounter >= fallDelayTarget) {
					fallDelayCounter = 0;
					gp.tileM.mapTileNum[col][row] = gp.tileM.backBlackID;
					gp.tileM.mapTileNum[col][row + 1] = gp.tileM.rubinID;
					worldY += gp.tileSize;
					//gp.playSE(5);
				}
			}
			else if(gp.tileM.mapTileNum[col][row + 1] == gp.tileM.stoneID 
					|| gp.tileM.mapTileNum[col][row + 1] == gp.tileM.brickID 
					|| gp.tileM.mapTileNum[col][row + 1] == gp.tileM.rubinID) {
				if(gp.tileM.mapTileNum[col - 1][row + 1] == gp.tileM.backBlackID && gp.tileM.mapTileNum[col - 1][row] == gp.tileM.backBlackID) {
					if(colP != col - 1 || rowP != row + 1) {
						if(colP != col - 1 || rowP != row) {
							isFalling = true;
							if(fallDelayCounter >= fallDelayTarget) {
								fallDelayCounter = 0;
								gp.tileM.mapTileNum[col][row] = gp.tileM.backBlackID;
								gp.tileM.mapTileNum[col - 1][row] = gp.tileM.rubinID;
								worldX -= gp.tileSize;
								//gp.playSE(5);
							}
						}
					}
				}
				else if(gp.tileM.mapTileNum[col + 1][row + 1] == gp.tileM.backBlackID && gp.tileM.mapTileNum[col + 1][row] == gp.tileM.backBlackID) {
					if(colP != col + 1 || rowP != row + 1) {
						if(colP != col + 1 || rowP != row) {
							isFalling = true;
							if(fallDelayCounter >= fallDelayTarget) {
								fallDelayCounter = 0;
								gp.tileM.mapTileNum[col][row] = gp.tileM.backBlackID;
								gp.tileM.mapTileNum[col + 1][row] = gp.tileM.rubinID;
								worldX += gp.tileSize;
								//gp.playSE(5);
							}
						}
					}
				}
			}
		}
		else {
			fallDelayCounter = 0;
		}
		if(isFalling) {
			fallDelayCounter++;
		}
		isFalling = false;
	}
	
	public void screenXY() {
		if(gp.tileM.counterXkameraPos == true) {
			screenX = worldX - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbLeft * gp.tileSize + gp.tileM.actualXkameraMovement;
		}
		else {
			screenX = worldX - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbLeft * gp.tileSize - gp.tileM.actualXkameraMovement;
		}
		
		if(gp.tileM.counterYkameraPos == true) {
			screenY = worldY - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbUp * gp.tileSize + gp.tileSize + gp.tileM.actualYkameraMovement;
		}
		else {
			screenY = worldY - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbUp * gp.tileSize + gp.tileSize - gp.tileM.actualYkameraMovement;
		}
	}
	
	public void update() {
		spriteCounter++;
		if(spriteCounter > 6) {
			if(spriteNum >= 16) {
				spriteNum = 0;
			}
			spriteNum++;
			spriteCounter = 0;
		}
		screenXY();
		rubinFall();
	}
	
}
