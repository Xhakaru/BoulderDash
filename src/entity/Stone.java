package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Stone extends Entity{
	
	private GamePanel gp;
	
	private int screenX;
	private int screenY;
	private int fallDelayCounter = 0;
	private int fallDelayTarget;
	private int pushDelayCounter = 0;
	private int pushDelayTarget;
	private boolean isFalling;
	
	private BufferedImage image = null;
	
	public Stone(GamePanel gp, int worldX, int worldY) {
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
		pushDelayTarget = 4;
	}
	
	public void getStoneImage() {
		
		try {
			
			image = ImageIO.read(getClass().getResource("/tiles/stone_dark_gray.png"));
			
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
		return image;
	}
	
	public void stonePush(int Xdirection) {
        int tileCol = worldX / gp.tileSize;
        int tileRow = worldY / gp.tileSize;
        
        if(!isFalling) {
        	if(Xdirection > 0) {
        		if(gp.tileM.mapTileNum[tileCol + 1][tileRow] == gp.tileM.backBlackID && pushDelayCounter >= pushDelayTarget) {
        			gp.tileM.mapTileNum[tileCol + 1][tileRow] = gp.tileM.stoneID;
        			gp.tileM.mapTileNum[tileCol][tileRow] = gp.tileM.backBlackID;
        			worldX += Xdirection;
        			gp.tileM.stoneCollision = false;
        			pushDelayCounter = 0;
        		}
        		else {
        			pushDelayCounter++;
        			gp.tileM.stoneCollision = true;
        		}
        	}
        	if(Xdirection < 0) {
        		if(gp.tileM.mapTileNum[tileCol - 1][tileRow] == gp.tileM.backBlackID && pushDelayCounter >= pushDelayTarget) {
        			gp.tileM.mapTileNum[tileCol - 1][tileRow] = gp.tileM.stoneID;
        			gp.tileM.mapTileNum[tileCol][tileRow] = gp.tileM.backBlackID;
        			worldX += Xdirection;
        			gp.tileM.stoneCollision = false;
        			pushDelayCounter = 0;
        		}
        		else {
        			pushDelayCounter++;
        			gp.tileM.stoneCollision = true;
        		}
        	}
        }
        else {
        	//der  Stein bewegt sich nicht und der Spieler darf nicht gehen
        	gp.tileM.stoneCollision = true;
        }
    }
	
	public void stoneFall() {
		int colP = gp.player.worldX / gp.tileSize;
		int rowP = gp.player.worldY / gp.tileSize;
		int col = worldX / gp.tileSize;
		int row = worldY / gp.tileSize;
		
		//Spieler unter Stein?
		boolean playerUnderStone;
		if(colP == col && rowP == row + 1) {
			playerUnderStone = true;
		}
		else {
			playerUnderStone = false;
		}
		
		if(isFalling && playerUnderStone) {
			gp.player.sterben("Stein Air", 0, 0, "null");
		}
		else if(colP == col && rowP == row) {
			gp.player.sterben("Stein Air", 0, 0, "null");
		}
		else if(!playerUnderStone) {
			if(gp.tileM.mapTileNum[col][row + 1] == gp.tileM.backBlackID) {
				isFalling = true;
				if(fallDelayCounter >= fallDelayTarget) {
					fallDelayCounter = 0;
					gp.tileM.mapTileNum[col][row] = gp.tileM.backBlackID;
					gp.tileM.mapTileNum[col][row + 1] = gp.tileM.stoneID;
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
								gp.tileM.mapTileNum[col - 1][row] = gp.tileM.stoneID;
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
								gp.tileM.mapTileNum[col + 1][row] = gp.tileM.stoneID;
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
		screenXY();
		stoneFall();
	}
	
}
