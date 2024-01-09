package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileManager {

	public static final int DIRT_ID = 0;
	public static final int RUBIE_ID = 1;
	public static final int WALL_GRAY_ID = 2;
	public static final int BRICK_GRAY_ID = 3;
	public static final int STONE_DARK_GRAY_ID = 4;
	public static final int DIRT_DARK_GRAY_ID = 5;
	public static final int FINISH_ID = 6;
	public static final int FINISH_SPAWNER_ID = 7;
	public static final int PLAYER_SPAWNER_ID = 8;
	public static final int ENEMY_SPAWNER_ID = 9;
	GamePanel gp;
	public Tile[] tile;

	public boolean stoneCollision = false;
	public boolean[] fallingCol;
	public int stoneFallCounter = 0;
	public int rubinFallCounter = 0;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	BufferedImage rubin_f1, rubin_f2, rubin_f3, rubin_f4, rubin_f5, rubin_f6, rubin_f7, rubin_f8;
	BufferedImage finish_grey_f1, finish_grey_f2, finish_grey_f3;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		
		setFallingColFalse();

		loadTileImages();
	}
	
	public void setFallingColFalse() {
		fallingCol = new boolean[gp.currentMap.getCurrentLevel().getLevelSize().getWidth()];
		for(int i = 0; i < gp.currentMap.getCurrentLevel().getLevelSize().getHeight(); i++) {
			fallingCol[i] = false;
		}
	}

	public void loadTileImages() {
		try {

			// 0 = dirt
			tile[DIRT_ID] = new Tile();
			tile[DIRT_ID].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray-v1.png"));
			tile[DIRT_ID].eat = true;

			// 1 = rubin
			tile[RUBIE_ID] = new Tile();
			rubin_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f1.png"));
			rubin_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f2.png"));
			rubin_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f3.png"));
			rubin_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f4.png"));
			rubin_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f5.png"));
			rubin_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f6.png"));
			rubin_f7 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f7.png"));
			rubin_f8 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f8.png"));
			tile[RUBIE_ID].eat = true;
			tile[RUBIE_ID].item = true;

			// 2 = wall gray
			tile[WALL_GRAY_ID] = new Tile();
			tile[WALL_GRAY_ID].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_gray.png"));
			tile[WALL_GRAY_ID].collision = true;

			// 3 = brick gray
			tile[BRICK_GRAY_ID] = new Tile();
			tile[BRICK_GRAY_ID].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick_gray.png"));
			tile[BRICK_GRAY_ID].collision = true;

			// 4 = stone dark gray
			tile[STONE_DARK_GRAY_ID] = new Tile();
			tile[STONE_DARK_GRAY_ID].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone_dark_gray.png"));
			tile[STONE_DARK_GRAY_ID].stone = true;
			tile[STONE_DARK_GRAY_ID].collision = true;

			// 5 = dirt dark gray
			tile[DIRT_DARK_GRAY_ID] = new Tile();
			tile[DIRT_DARK_GRAY_ID].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));

			// 6 = finish
			tile[FINISH_ID] = new Tile();
			finish_grey_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f1.png"));
			finish_grey_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f2.png"));
			finish_grey_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f3.png"));
			tile[FINISH_ID].finish = true;

			// 7 = finish spawn point
			tile[FINISH_SPAWNER_ID] = new Tile();
			tile[FINISH_SPAWNER_ID].image = tile[0].image;
			tile[FINISH_SPAWNER_ID].finishSpawnPoint = true;

			// 8 = player spawn point
			tile[PLAYER_SPAWNER_ID] = new Tile();
			tile[PLAYER_SPAWNER_ID].image = tile[5].image;
			tile[PLAYER_SPAWNER_ID].playerSpawnPoint = true;
			tile[PLAYER_SPAWNER_ID].eat = true;

			// 9 = enemy spawn point
			tile[ENEMY_SPAWNER_ID] = new Tile();
			tile[ENEMY_SPAWNER_ID].image = tile[5].image;
			tile[ENEMY_SPAWNER_ID].playerSpawnPoint = true;

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void eaten(int worldX, int worldY) {
		
		int tileCol = worldX / gp.tileSize;
		int tileRow = worldY / gp.tileSize;
		gp.currentMap.getCurrentLevel().getTileIds()[tileCol][tileRow] = DIRT_DARK_GRAY_ID;
//		gp.playSE(6);   					//Herr Wedel, Sie können das auch entkommentieren, aber auf eigene Gefahr
	}
	
	public void stonePush(int worldX, int worldY, int varX, int varY) {
		int x = worldX + varX;
		int y = worldY + varY;
		int c = worldX;
		int v = worldY;
		int tileCol = 0;
		int tileRow = 0;
		
		if(varX > 0) {
			tileCol = c / gp.tileSize;
			tileRow = v / gp.tileSize;
			if (gp.currentMap.getCurrentLevel().getTileIds()[tileCol + 2][tileRow] == DIRT_DARK_GRAY_ID) {
				stoneCollision = false;
				gp.currentMap.getCurrentLevel().getTileIds()[tileCol + 1][tileRow] = DIRT_DARK_GRAY_ID;

				tileCol = x / gp.tileSize;
				tileRow = y / gp.tileSize;
				gp.currentMap.getCurrentLevel().getTileIds()[tileCol + 1][tileRow] = 4;
			} else {
				stoneCollision = true;
			}
		}
		else if(varX < 0) {
			tileCol = c / gp.tileSize;
			tileRow = v / gp.tileSize;
			if (gp.currentMap.getCurrentLevel().getTileIds()[tileCol - 2][tileRow] == DIRT_DARK_GRAY_ID) {
				stoneCollision = false;
				gp.currentMap.getCurrentLevel().getTileIds()[tileCol - 1][tileRow] = DIRT_DARK_GRAY_ID;

				tileCol = x / gp.tileSize;
				tileRow = y / gp.tileSize;
				gp.currentMap.getCurrentLevel().getTileIds()[tileCol - 1][tileRow] = 4;
			} else {
				stoneCollision = true;
			}
		}
	}
	
	public void stoneFall(int col, int row) {
		if(gp.currentMap.getCurrentLevel().getTileIds()[col][row] == 4) {
			if(col == gp.player.worldX / gp.tileSize && row == gp.player.worldY / gp.tileSize) {
				gp.player.sterben("Stein Ground");
			}
			boolean skip = false;
			if(!fallingCol[col] && col == gp.player.worldX / gp.tileSize && row == gp.player.worldY / gp.tileSize - 1) {
				skip = true; 
			}
			boolean playerInWayXneg = false;
			if(gp.player.worldY / gp.tileSize == row && gp.player.worldX / gp.tileSize == col - 1) {
				playerInWayXneg = true;
			}
			boolean playerInWayXpos = false;
			if(gp.player.worldY / gp.tileSize == row && gp.player.worldX / gp.tileSize == col + 1) {
				playerInWayXpos = true;
			}
			boolean playerInWayXnegMinusOne = false;
			if (gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col - 1) {
				playerInWayXnegMinusOne = true;
			}
			boolean playerInWayXposMinusOne = false;
			if (gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col + 1) {
				playerInWayXposMinusOne = true;
			}
			if (gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == DIRT_DARK_GRAY_ID && !skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] = 4;
//				gp.playSE("win-nt-startup");
				fallingCol[col] = true;
				if (gp.currentMap.getCurrentLevel().getTileIds()[col][row + 2] != DIRT_DARK_GRAY_ID) {
					fallingCol[col] = false;
				}
			} else if (gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 4 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXneg &&
					!playerInWayXnegMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
			else if(gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 4 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXpos &&
					!playerInWayXposMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
			else if(gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 1 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXneg &&
					!playerInWayXnegMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
			else if(gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 1 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXpos &&
					!playerInWayXposMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
		}
	}
	
	public void rubinFall(int col, int row) {
		if(gp.currentMap.getCurrentLevel().getTileIds()[col][row] == 1) {
			boolean skip = false;
			if(!fallingCol[col] && col == gp.player.worldX / gp.tileSize && row == gp.player.worldY / gp.tileSize - 1) { 
				skip = true; 
			}
			boolean playerInWayXneg = false;
			if(gp.player.worldY / gp.tileSize == row && gp.player.worldX / gp.tileSize == col - 1) {
				playerInWayXneg = true;
			}
			boolean playerInWayXpos = false;
			if(gp.player.worldY / gp.tileSize == row && gp.player.worldX / gp.tileSize == col + 1) {
				playerInWayXpos = true;
			}
			boolean playerInWayXnegMinusOne = false;
			if (gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col - 1) {
				playerInWayXnegMinusOne = true;
			}
			boolean playerInWayXposMinusOne = false;
			if (gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col + 1) {
				playerInWayXposMinusOne = true;
			}
			if (gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == DIRT_DARK_GRAY_ID && !skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] = 1;
//				gp.playSE("win-nt-startup");
				fallingCol[col] = true;
				if (gp.currentMap.getCurrentLevel().getTileIds()[col][row + 2] != DIRT_DARK_GRAY_ID) {
					fallingCol[col] = false;
				}
			} else if (gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 1 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXneg &&
					!playerInWayXnegMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			else if(gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 1 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXpos &&
					!playerInWayXposMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			else if(gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 4 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXneg &&
					!playerInWayXnegMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col - 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			else if(gp.currentMap.getCurrentLevel().getTileIds()[col][row + 1] == 4 &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] == DIRT_DARK_GRAY_ID &&
					gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row + 1] == DIRT_DARK_GRAY_ID &&
					!playerInWayXpos &&
					!playerInWayXposMinusOne &&
					!skip) {
				gp.currentMap.getCurrentLevel().getTileIds()[col][row] = DIRT_DARK_GRAY_ID;
				gp.currentMap.getCurrentLevel().getTileIds()[col + 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			if(gp.currentMap.getCurrentLevel().getTileIds()[col][row] == 1) {															//guckt ob der Spieler im Weg ist
				if(col == gp.player.worldX / gp.tileSize && row == gp.player.worldY / gp.tileSize) {
					//Spieler wurde getroffen (Sound-Effect und Leben abziehen/Game Over)
					gp.player.leben--;
					System.out.println("Der Spieler hat nur noch " + gp.player.leben + " Leben.");
				}
			}
		}
	}
	
	public void rubinSprite() {
		if(spriteNum == 1) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 2) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 3) {
			tile[1].image = rubin_f2;
		}
		if (spriteNum == 4) {
			tile[1].image = rubin_f3;
		}
		if (spriteNum == DIRT_DARK_GRAY_ID) {
			tile[1].image = rubin_f4;
		}
		if (spriteNum == 6) {
			tile[1].image = rubin_f5;
		}
		if(spriteNum == 7) {
			tile[1].image = rubin_f6;
		}
		if(spriteNum == 8) {
			tile[1].image = rubin_f7;
		}
		if(spriteNum == 9) {
			tile[1].image = rubin_f8;
		}
		if(spriteNum == 10) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 11) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 12) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 13) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 14) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 15) {
			tile[1].image = rubin_f1;
		}
		if(spriteNum == 16) {
			tile[1].image = rubin_f1;
		}
	}
	
	public void finishSprite() {
		if(spriteNum == 1) {
			tile[6].image = finish_grey_f1;
		}
		if(spriteNum == 2) {
			tile[6].image = finish_grey_f1;
		}
		if(spriteNum == 3) {
			tile[6].image = finish_grey_f2;
		}
		if (spriteNum == 4) {
			tile[6].image = finish_grey_f2;
		}
		if (spriteNum == 5) {
			tile[6].image = finish_grey_f3;
		}
		if (spriteNum == 6) {
			tile[6].image = finish_grey_f3;
		}
		if(spriteNum == 7) {
			tile[6].image = finish_grey_f2;
		}
		if(spriteNum == 8) {
			tile[6].image = finish_grey_f2;
		}
		if(spriteNum == 9) {
			tile[6].image = finish_grey_f1;
		}
		if(spriteNum == 10) {
			tile[6].image = finish_grey_f1;
		}
		if(spriteNum == 11) {
			tile[6].image = finish_grey_f2;
		}
		if(spriteNum == 12) {
			tile[6].image = finish_grey_f2;
		}
		if(spriteNum == 13) {
			tile[6].image = finish_grey_f3;
		}
		if(spriteNum == 14) {
			tile[6].image = finish_grey_f3;
		}
		if(spriteNum == 15) {
			tile[6].image = finish_grey_f2;
		}
		if(spriteNum == 16) {
			tile[6].image = finish_grey_f2;
		}
	}
	
	public void spawnFinish() {
		if(gp.player.rubinCounter >= gp.currentMap.getCurrentLevel().getFinishRubies()) {
			gp.currentMap.getCurrentLevel().getTileIds()[gp.currentMap.getCurrentLevel().getFinishPoint().getX()][gp.currentMap.getCurrentLevel().getFinishPoint().getY()] = 6;
		}
	}
	
	public void update() {
		
		spawnFinish();
		
		rubinSprite();
		finishSprite();
		
		spriteCounter++;
		if(spriteCounter > 6) {
			spriteNum = spriteNum - 1;
			if(spriteNum < 0) spriteNum = 16;
			spriteCounter = 0;
		}

	}
	
	
	//Chunk-Kamera
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.currentMap.getCurrentLevel().getLevelSize().getWidth() && worldRow < gp.currentMap.getCurrentLevel().getLevelSize().getHeight()) {
			
			int tileNum = gp.currentMap.getCurrentLevel().getTileIds()[worldCol][worldRow];
			
			stoneFallCounter++;
			if(stoneFallCounter > 18) {
				stoneFall(worldCol, worldRow);
				stoneFallCounter = 0;
			}
			
			rubinFallCounter++;
			if(rubinFallCounter > 18) {
				rubinFall(worldCol, worldRow);
				rubinFallCounter = 0;
			}
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.tileSize + gp.camera.worldX;
			int screenY = worldY - gp.tileSize + gp.camera.worldY;
			
			g2.drawImage(tile[tileNum].image, screenX, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
			
			
			worldCol++;
			
			if(worldCol == gp.currentMap.getCurrentLevel().getLevelSize().getWidth()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}