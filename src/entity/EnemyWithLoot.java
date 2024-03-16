package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class EnemyWithLoot extends Entity{
	private GamePanel gp;
	
	private int[] screenX = new int[20];
	private int[] screenY = new int[20];
	private int[] worldX = new int[20];
	private int[] worldY = new int[20];
	private String[] direction = new String[20];
	
	private BufferedImage image = null;
	
	private int enemyCounter = 0;
	private int updateCounter = 0;
	
	public EnemyWithLoot(GamePanel gp) {
		
		this.gp = gp;
		
		getEnemyImage();
	}
	
	public void clearEnemyWithLoot() {
		enemyCounter = 0;
	}
	
	public void spawnEnemyWithLoot(int col, int row) {
		enemyCounter++;
		worldX[enemyCounter] = col * gp.tileSize;
		worldY[enemyCounter] = row * gp.tileSize + gp.tileSize;
		direction[enemyCounter] = "up";
	}
	
	public void getEnemyImage() {
		try {
			butterfly_f1 = ImageIO.read(getClass().getResource("/enemy/butterfly_f1.png"));
			butterfly_f2 = ImageIO.read(getClass().getResource("/enemy/butterfly_f2.png"));
			butterfly_f3 = ImageIO.read(getClass().getResource("/enemy/butterfly_f3.png"));
			butterfly_f4 = ImageIO.read(getClass().getResource("/enemy/butterfly_f4.png"));
			butterfly_f1_red = ImageIO.read(getClass().getResource("/enemy/butterfly_f1_red.png"));
			butterfly_f2_red = ImageIO.read(getClass().getResource("/enemy/butterfly_f2_red.png"));
			butterfly_f3_red = ImageIO.read(getClass().getResource("/enemy/butterfly_f3_red.png"));
			butterfly_f4_red = ImageIO.read(getClass().getResource("/enemy/butterfly_f4_red.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkPlayer() {
		for(int i = 1; i <= enemyCounter; i++) {
			if(worldX[i] == gp.player.worldX && worldY[i] == gp.player.worldY) {
				gp.player.sterben("Gegner mit Loot", screenX[i] / gp.tileSize, screenY[i] / gp.tileSize, "0");
			}
			if(worldX[i] == gp.player.worldX + gp.tileSize && worldY[i] == gp.player.worldY) {
				gp.player.sterben("Gegner mit Loot", screenX[i] / gp.tileSize, screenY[i] / gp.tileSize, "O");
			}
			if(worldX[i] == gp.player.worldX - gp.tileSize && worldY[i] == gp.player.worldY) {
				gp.player.sterben("Gegner mit Loot", screenX[i] / gp.tileSize, screenY[i] / gp.tileSize, "W");
			}
			if(worldX[i] == gp.player.worldX && worldY[i] == gp.player.worldY + gp.tileSize) {
				gp.player.sterben("Gegner mit Loot", screenX[i] / gp.tileSize, screenY[i] / gp.tileSize, "S");
			}
			if(worldX[i] == gp.player.worldX && worldY[i] == gp.player.worldY - gp.tileSize) {
				gp.player.sterben("Gegner mit Loot", screenX[i] / gp.tileSize, screenY[i] / gp.tileSize, "N");
			}
		}
	}
	
	public void enemyMovement() {
		for(int i = 1; i <= enemyCounter; i++) {
			switch(direction[i]) {
				case("up"):
					if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize + 1][worldY[i]/gp.tileSize] == 5) {
						direction[i] = "right";
						worldX[i] += gp.tileSize;
					}
					else if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize][worldY[i]/gp.tileSize - 1] != 5) {
						direction[i] = "left";
					}
					else {
						worldY[i] -= gp.tileSize;
					}
					break;
					
				case("left"):
					if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize][worldY[i]/gp.tileSize - 1] == 5) {
						direction[i] = "up";
						worldY[i] -= gp.tileSize;
					}
					else if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize - 1][worldY[i]/gp.tileSize] != 5) {
						direction[i] = "down";
					}
					else {
						worldX[i] -= gp.tileSize;
					}
					break;
					
				case("down"):
					if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize - 1][worldY[i]/gp.tileSize] == 5) {
						direction[i] = "left";
						worldX[i] -= gp.tileSize;
					}
					else if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize][worldY[i]/gp.tileSize + 1] != 5) {
						direction[i] = "right";
					}
					else {
						worldY[i] += gp.tileSize;
					}
					break;
					
				case("right"):
					if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize][worldY[i]/gp.tileSize + 1] == 5) {
						direction[i] = "down";
						worldY[i] += gp.tileSize;
					}
					else if(gp.tileM.mapTileNum[worldX[i]/gp.tileSize + 1][worldY[i]/gp.tileSize] != 5) {
						direction[i] = "up";
					}
					else {
						worldX[i] += gp.tileSize;
					}
					break;
					
			}
		}
	}
	
	public void enemySprite() {
		if(spriteNum == 1) {
			image = butterfly_f1_red;
		}
		if(spriteNum == 2) {
			image = butterfly_f1_red;
		}
		if(spriteNum == 3) {
			image = butterfly_f1_red;
		}
		if(spriteNum == 4) {
			image = butterfly_f2_red;
		}
		if(spriteNum == 5) {
			image = butterfly_f3_red;
		}
		if(spriteNum == 6) {
			image = butterfly_f4_red;
		}
		if(spriteNum == 7) {
			image = butterfly_f3_red;
		}
		if(spriteNum == 8) {
			image = butterfly_f2_red;
		}
		if(spriteNum == 9) {
			image = butterfly_f1_red;
		}
		if(spriteNum == 10) {
			image = butterfly_f1_red;
		}
		if(spriteNum == 11) {
			image = butterfly_f1_red;
		}
		if(spriteNum == 12) {
			image = butterfly_f1_red;
		}
	}
	
	public void steinAufKopf() {
		for(int i = 1; i <= enemyCounter; i++) {
			if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize] == 4 || 
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize - 1] == 4 || 
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize] == 1 || 
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize - 1] == 1) {
				gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize] = 1;
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize - 1] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize - 1] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize - 1][worldY[i] / gp.tileSize - 1] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize - 1][worldY[i] / gp.tileSize - 1] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize + 1][worldY[i] / gp.tileSize - 1] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize + 1][worldY[i] / gp.tileSize - 1] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize + 1] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize][worldY[i] / gp.tileSize + 1] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize - 1][worldY[i] / gp.tileSize + 1] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize - 1][worldY[i] / gp.tileSize + 1] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize + 1][worldY[i] / gp.tileSize + 1] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize + 1][worldY[i] / gp.tileSize + 1] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize - 1][worldY[i] / gp.tileSize] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize - 1][worldY[i] / gp.tileSize] = 1;
				}
				if(gp.tileM.mapTileNum[worldX[i] / gp.tileSize + 1][worldY[i] / gp.tileSize] != 2) {
					gp.tileM.mapTileNum[worldX[i] / gp.tileSize + 1][worldY[i] / gp.tileSize] = 1;
				}
				int tp = 0;
				int worldXtp[] = new int[20];
				for(int j = 1; j <= enemyCounter; j++) {
					worldXtp[j] = worldX[j];
				}
				tp = 0;
				for(int j = 1; j <= enemyCounter; j++) {
					if(j == i) {
						tp = 1;
					}
					else {
						worldX[j-tp] = worldXtp[j];
					}
				}
				int worldYtp[] = new int[20];
				for(int j = 1; j <= enemyCounter; j++) {
					worldYtp[j] = worldY[j];
				}
				tp = 0;
				for(int j = 1; j <= enemyCounter; j++) {
					if(j == i) {
						tp++;
					}
					else {
						worldY[j-tp] = worldYtp[j];
					}
				}
				String directiontp[] = new String[20];
				for(int j = 1; j <= enemyCounter; j++) {
					directiontp[j] = direction[j];
				}
				tp = 0;
				for(int j = 1; j <= enemyCounter; j++) {
					if(j == i) {
						tp++;
					}
					else {
						direction[j-tp] = directiontp[j];
					}
				}
				enemyCounter--;
			}
		}
	}
	
	public void update() {
		checkPlayer();
		
		steinAufKopf();
		enemySprite();
		
		//Gegner updatet sich nur alle 11 Frames
		updateCounter++;
		if(updateCounter > 10) {
			enemyMovement();
			updateCounter = 0;
		}
		
		//funktioniert
		spriteCounter++;
		if(spriteCounter > 5) {
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
				spriteNum = 7;
			}
			else if(spriteNum == 7) {
				spriteNum = 8;
			}
			else if(spriteNum == 8) {
				spriteNum = 9;
			}
			else if(spriteNum == 9) {
				spriteNum = 10;
			}
			else if(spriteNum == 10) {
				spriteNum = 11;
			}
			else if(spriteNum == 11) {
				spriteNum = 12;
			}
			else if(spriteNum == 12) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		for(int i = 1; i <= enemyCounter; i++) {
			if(gp.tileM.counterXkameraPos == true) {
				screenX[i] = worldX[i] - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbLeft * gp.tileSize + gp.tileM.actualXkameraMovement;
			}
			else {
				screenX[i] = worldX[i] - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbLeft * gp.tileSize - gp.tileM.actualXkameraMovement;
			}
			
			if(gp.tileM.counterYkameraPos == true) {
				screenY[i] = worldY[i] - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbUp * gp.tileSize + gp.tileSize + gp.tileM.actualYkameraMovement;
			}
			else {
				screenY[i] = worldY[i] - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbUp * gp.tileSize + gp.tileSize - gp.tileM.actualYkameraMovement;
			}
			
			g2.drawImage(image, screenX[i], screenY[i], gp.tileSize, gp.tileSize, null);
		}
	}
}
