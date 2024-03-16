package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class EnemyNoLoot extends Entity{
	
	private GamePanel gp;
	
	public int screenX;
	public int screenY;
	private int[] worldSpawnX = {9999, 9999, 9999, 4, 9999};
	private int[] worldSpawnY = {9999, 9999, 9999, 14, 9999};
	
	private BufferedImage image = null;
	
	private int updateCounter = 0;
	
	public EnemyNoLoot(GamePanel gp) {
		this.gp = gp;
		
		setDefaultValues();
		getEnemyImage();
	}
	
	public void setDefaultValues(){
		worldX = worldSpawnX[gp.welt - 1] * gp.tileSize;
		worldY = worldSpawnY[gp.welt - 1] * gp.tileSize;
		direction = "up";
	}
	
	public void getEnemyImage() {
		
		try {
			
			enemy_gray_f1 = ImageIO.read(getClass().getResource("/enemy/enemy_f1.png"));
			enemy_gray_f2 = ImageIO.read(getClass().getResource("/enemy/enemy_f2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkPlayer() {
		if(worldX == gp.player.worldX && worldY == gp.player.worldY) {
			gp.player.sterben("Gegner", screenX / gp.tileSize, screenY / gp.tileSize, "0");
		}
		if(worldX == gp.player.worldX + gp.tileSize && worldY == gp.player.worldY) {
			gp.player.sterben("Gegner", screenX / gp.tileSize, screenY / gp.tileSize, "O");
		}
		if(worldX == gp.player.worldX - gp.tileSize && worldY == gp.player.worldY) {
			gp.player.sterben("Gegner", screenX / gp.tileSize, screenY / gp.tileSize, "W");
		}
		if(worldX == gp.player.worldX && worldY == gp.player.worldY + gp.tileSize) {
			gp.player.sterben("Gegner", screenX / gp.tileSize, screenY / gp.tileSize, "S");
		}
		if(worldX == gp.player.worldX && worldY == gp.player.worldY - gp.tileSize) {
			gp.player.sterben("Gegner", screenX / gp.tileSize, screenY / gp.tileSize, "N");
		}
	}
	
	public void enemyMovement() {
		
		switch(direction) {
			case("up"):
				if(gp.tileM.mapTileNum[worldX/gp.tileSize + 1][worldY/gp.tileSize] == 5) {
					direction = "right";
					worldX += gp.tileSize;
				}
				else if(gp.tileM.mapTileNum[worldX/gp.tileSize][worldY/gp.tileSize - 1] != 5) {
					direction = "left";
				}
				else {
					worldY -= gp.tileSize;
				}
				break;
			
			case("left"):
				if(gp.tileM.mapTileNum[worldX/gp.tileSize][worldY/gp.tileSize - 1] == 5) {
					direction = "up";
					worldY -= gp.tileSize;
				}
				else if(gp.tileM.mapTileNum[worldX/gp.tileSize - 1][worldY/gp.tileSize] != 5) {
					direction = "down";
				}
				else {
					worldX -= gp.tileSize;
				}
				break;
			
			case("down"):
				if(gp.tileM.mapTileNum[worldX/gp.tileSize - 1][worldY/gp.tileSize] == 5) {
					direction = "left";
					worldX -= gp.tileSize;
				}
				else if(gp.tileM.mapTileNum[worldX/gp.tileSize][worldY/gp.tileSize + 1] != 5) {
					direction = "right";
				}
				else {
					worldY += gp.tileSize;
				}
				break;
			
			case("right"):
				if(gp.tileM.mapTileNum[worldX/gp.tileSize][worldY/gp.tileSize + 1] == 5) {
					direction = "down";
					worldY += gp.tileSize;
				}
				else if(gp.tileM.mapTileNum[worldX/gp.tileSize + 1][worldY/gp.tileSize] != 5) {
					direction = "up";
				}
				else {
					worldX += gp.tileSize;
				}
				break;
			
		}
	}
	
	public void steinAufKopf() {
		if(gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize] == 4 || 
				gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize - 1] == 4 || 
				gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize] == 1 || 
				gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize - 1] == 1) {
			gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize] = 7;
			if(gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize - 1] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize - 1] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize - 1][worldY / gp.tileSize - 1] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize - 1][worldY / gp.tileSize - 1] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize + 1][worldY / gp.tileSize - 1] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize + 1][worldY / gp.tileSize - 1] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize + 1] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize][worldY / gp.tileSize + 1] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize - 1][worldY / gp.tileSize + 1] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize - 1][worldY / gp.tileSize + 1] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize + 1][worldY / gp.tileSize + 1] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize + 1][worldY / gp.tileSize + 1] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize - 1][worldY / gp.tileSize] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize - 1][worldY / gp.tileSize] = 7;
			}
			if(gp.tileM.mapTileNum[worldX / gp.tileSize + 1][worldY / gp.tileSize] != 2) {
				gp.tileM.mapTileNum[worldX / gp.tileSize + 1][worldY / gp.tileSize] = 7;
			}
			worldSpawnX[gp.welt - 1] = 9999;
		}
	}
	
	public void enemySprite() {
		if(spriteNum == 1) {
			image = enemy_gray_f1;
		}
		if(spriteNum == 2) {
			image = enemy_gray_f1;
		}
		if(spriteNum == 3) {
			image = enemy_gray_f1;
		}
		if(spriteNum == 4) {
			image = enemy_gray_f2;
		}
		if(spriteNum == 5) {
			image = enemy_gray_f2;
		}
		if(spriteNum == 6) {
			image = enemy_gray_f2;
		}
	}
	
	public void update() {
		if(worldSpawnX[gp.welt - 1] != 9999) {
			
			checkPlayer();
			
			steinAufKopf();
			enemySprite();
			
			//Gegner updatet sich nur alle 11 Frames
			updateCounter++;
			if(updateCounter > 10) {
				enemyMovement();
				updateCounter = 0;
			}
			
			//könnte zu problemen kommen, weil sprites über Entity läuft, funktioniert aber
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					spriteNum = 5;
				}
				else if(spriteNum == 5) {
					spriteNum = 6;
				}
				else if(spriteNum == 6) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		if(worldSpawnX[gp.welt - 1] != 9999) {
			
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
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
		}
	}
}
