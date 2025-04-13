package tile;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public Chunk chunks[][];
	public int playerInChunk;
	public int screenInWorldCol, screenInWorldRow;
	public int anzahlScreens;
	public int playerChunkX, playerChunkY, playerChunkXlast, playerChunkYlast;
	
	public boolean stoneCollision = false;
	private boolean fallingCol[];
	private int stoneFallCounter = 0;
	private int rubinFallCounter = 0;
	private int spriteCounter = 0;
	private int spriteNum = 1;
	private int spriteCounterExplosion = 0;
	private int spriteNumExplosion = 0;
	private int[] rubineFinishSpawn = {9999, 9999, 16, 14, 26};
	private boolean startExplosion = false;
	
	//TILE IDs
	public static int dirtID = 0;
	public static int rubinID = 1;
	public static int borderID = 2;
	public static int brickID = 3;
	public static int stoneID = 4;
	public static int backBlackID = 5;
	
	//Kamerafahrt
	public boolean counterXkameraPos, counterYkameraPos;
	private boolean kameraFahrtX = false;
	private boolean kameraFahrtY = false;
	public int actualXkameraMovement, actualYkameraMovement;
	public int counterXkamera = 0;
	public int counterYkamera = 0;
	
	public BufferedImage rubin_f1, rubin_f2, rubin_f3, rubin_f4, rubin_f5, rubin_f6, rubin_f7, rubin_f8;
	public BufferedImage finish_grey_f1, finish_grey_f2, finish_grey_f3;
	public BufferedImage explosion_dark_f1, explosion_dark_f2, explosion_dark_f3, explosion_dark_f4, explosion_dark_f5, explosion_dark_f6;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[11];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		setFallingColFalse();
		
		getTileImage();
		switch(gp.welt) {
			case(99):
				loadMap("/maps/world03.txt");
				break;
			case(4):
				loadMap("/maps/world04.txt");
				break;
			case(5):
				loadMap("/maps/world05.txt");
				break;
			case(3):
				loadMap("/maps/TestMap.txt");
		}
		
		chunkBorders();
	}
	
	public void setFallingColFalse() {
		fallingCol = new boolean[gp.maxWorldCol];
		for(int i = 0; i < gp.maxWorldCol; i++) {
			fallingCol[i] = false;
		}
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray-v1.png"));
			tile[0].eat = true;
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));
			tile[1].eat = true;
			tile[1].item = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_gray.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick_gray.png"));
			tile[3].collision = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));
			tile[4].stone = true;
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));
			
			tile[6] = new Tile();
			finish_grey_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f1.png"));
			finish_grey_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f2.png"));
			finish_grey_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f3.png"));
			tile[6].finish = true;
			
			tile[7] = new Tile();
			explosion_dark_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f1.png"));
			explosion_dark_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f2.png"));
			explosion_dark_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f3.png"));
			explosion_dark_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f4.png"));
			explosion_dark_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f5.png"));
			explosion_dark_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/explosion/explosion_dark_f6.png"));
			tile[7].collision = true;
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_closed.png"));
			tile[8].collision = true;
			
			//Spawnpoint für Gegner mit Loot
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String map) {
		gp.levelB.rubineGesammelt = false;
		gp.levelB.setDefaultTileBord();
		gp.stoneM.setDefault();
		gp.rubinM.setDefault();
		
		try {
		    InputStream is = getClass().getResourceAsStream(map);
		    BufferedReader br = new BufferedReader(new InputStreamReader(is));

		    for (int row = 0; row < gp.maxWorldRow; row++) {
		        String line = br.readLine();
		        if (line == null) break;

		        String[] numbers = line.split(" ");

		        for (int col = 0; col < gp.maxWorldCol; col++) {
		            int num;

		            if (col < numbers.length) {
		                num = Integer.parseInt(numbers[col]);
		            } else {
		                // Füllt mit 10 auf, wenn zu wenige Spalten vorhanden sind
		                num = 10;
		            }

		            if (num == stoneID) {
		                gp.stoneM.addStoneToStoneList(col * gp.tileSize, row * gp.tileSize);
		            }

		            if (num == rubinID) {
		                gp.rubinM.addRubinToRubinList(col * gp.tileSize, row * gp.tileSize);
		            }

		            if (num == 9) {
		                gp.enemyWL.spawnEnemyWithLoot(col, row);
		                num = 5;
		            }

		            mapTileNum[col][row] = num;
		        }
		    }

		    br.close();
		} catch (Exception e) {
		    e.printStackTrace(); // Zeigt dir den Fehler
		}


	}
	
	public void eaten(int worldX, int worldY) {
		
		int tileCol = worldX / gp.tileSize;
		int tileRow = worldY / gp.tileSize;
		mapTileNum[tileCol][tileRow] = 5;
//		gp.playSE(6);   					//Herr Wedel, Sie können das auch entkommentieren, aber auf eigene Gefahr
	}
	
	public void chunkBorders() {
		
		//Wie oft passt der Screen in der X-Achse rein (um alles zu bedecken)
		screenInWorldCol = 0;
		int displayedTilesX = gp.maxScreenCol;
		int worldColVariable = gp.maxWorldCol;
		while(worldColVariable - displayedTilesX > 0) {
			screenInWorldCol++;
			worldColVariable = worldColVariable - displayedTilesX;
		}
		screenInWorldCol++;
		//Wie oft passt der Screen in der Y-Achse rein (um alles zu bedecken)
		screenInWorldRow = 0;
		int displayedTilesY = gp.maxScreenRow;
		int worldRowVariable = gp.maxWorldRow;
		while(worldRowVariable - displayedTilesY > 0) {
			screenInWorldRow++;
			worldRowVariable = worldRowVariable - displayedTilesY;
		}
		screenInWorldRow++;
		anzahlScreens = screenInWorldCol * screenInWorldRow;
		
		int widthPerChunk = Math.round(gp.maxWorldCol / screenInWorldCol);
		int hightPerChunk = Math.round(gp.maxWorldRow / screenInWorldRow);
		
		chunks = new Chunk[screenInWorldRow][screenInWorldCol];
		int TPchunky = 0;
		for(int i = 0; i < screenInWorldRow; i++) {
			int TPchunkx = 0;
			for(int j = 0; j < screenInWorldCol; j++) {
				chunks[i][j] = new Chunk(gp, TPchunkx, TPchunkx + widthPerChunk, TPchunky, TPchunky + hightPerChunk);
				TPchunkx = TPchunkx + widthPerChunk;
			}
			TPchunky = TPchunky + hightPerChunk;
		}
		
	}
	
	public void inChunk() {
		playerChunkXlast = playerChunkX;
		playerChunkYlast = playerChunkY;
		//Player in Chunk?
		playerInChunk = 0;
		playerChunkX = 0;
		for(int j = 0; j < screenInWorldCol; j++) {
			Chunk tp = chunks[0][j];
			if(tp.cbLeft * gp.tileSize < gp.player.worldX && tp.cbRight * gp.tileSize >= gp.player.worldX) {
				playerChunkX = j;
				j = screenInWorldCol;
			}
		}
		
		playerChunkY = 0;
		for(int i = 0; i < screenInWorldRow; i++) {
			Chunk tp = chunks[i][playerChunkX];
			if(tp.cbUp * gp.tileSize < gp.player.worldY && tp.cbDown * gp.tileSize >= gp.player.worldY) {
				playerChunkY = i;
				i = screenInWorldRow;
			}
		}
		
		playerInChunk = playerChunkY * screenInWorldCol + playerChunkX + 1;
		
		if(playerChunkXlast != playerChunkX) {
			if(playerChunkXlast - playerChunkX == -1) {
				counterXkameraPos = true;
				kameraFahrtX = true;
			}
			else {
				counterXkameraPos = false;
				kameraFahrtX = true;
			}
		}
		if(playerChunkYlast != playerChunkY) {
			if(playerChunkYlast - playerChunkY == -1) {
				counterYkameraPos = true;
				kameraFahrtY = true;
			}
			else {
				counterYkameraPos = false;
				kameraFahrtY = true;
			}
		}
	}
	
	public void rubinFall(int col, int row) {
		int Xcol = gp.player.worldX / gp.tileSize;
		int Yrow = gp.player.worldY / gp.tileSize;
		
		if(mapTileNum[col][row] == 1) {
			if(col == gp.player.worldX / gp.tileSize && row == gp.player.worldY / gp.tileSize) {
				gp.player.sterben("Rubin Ground", 0, 0, "null");
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
			if(gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col - 1) {
				playerInWayXnegMinusOne = true;
			}
			boolean playerInWayXposMinusOne = false;
			if(gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col + 1) {
				playerInWayXposMinusOne = true;
			}
			if(col == Xcol && row == Yrow -1 && skip == false) {
				gp.player.sterben("Stein Ground", 0, 0, "null");
			}
			if(mapTileNum[col][row + 1] == 5 && !skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col][row + 1] = 1;
//				gp.playSE(5);
				fallingCol[col] = true;
				if(mapTileNum[col][row + 2] != 5) {
					fallingCol[col] = false;
				}
			}
			else if(mapTileNum[col][row + 1] == 4 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 1;
//				gp.playSE(5);
			}
			else if(mapTileNum[col][row + 1] == 4 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 1;
//				gp.playSE(5);
			}
			else if(mapTileNum[col][row + 1] == 1 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 1;
//				gp.playSE(5);
			}
			else if(mapTileNum[col][row + 1] == 1 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 1;
//				gp.playSE(5);
			}
			else if(mapTileNum[col][row + 1] == 3 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 1;
//				gp.playSE(5);
			}
			else if(mapTileNum[col][row + 1] == 3 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 1;
//				gp.playSE(5);
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
		if(spriteNum == 4) {
			tile[1].image = rubin_f3;
		}
		if(spriteNum == 5) {
			tile[1].image = rubin_f4;
		}
		if(spriteNum == 6) {
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
		if(spriteNum == 4) {
			tile[6].image = finish_grey_f2;
		}
		if(spriteNum == 5) {
			tile[6].image = finish_grey_f3;
		}
		if(spriteNum == 6) {
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
		if(gp.player.rubinCounter >= rubineFinishSpawn[gp.welt - 1]) {
			gp.levelB.rubineGesammelt = true;
			gp.levelB.setDefaultTileBord();
			
			int counterX = 0;
			int counterY = 0;
			while(counterX < gp.maxWorldCol && counterY < gp.maxWorldRow) {
				
				while(counterX < gp.maxWorldCol) {
					
					if(mapTileNum[counterX][counterY] == 8) {
						mapTileNum[counterX][counterY] = 6;
					}
					counterX++;
				}
				if(counterX == gp.maxWorldCol) {
					counterX = 0;
					counterY++;
				}
			}
		}
	}
	
	public void explosionSprite() {
		if(startExplosion == true) {
			spriteCounterExplosion++;
			if(spriteCounterExplosion >= 4) {
				spriteNumExplosion++;
				switch(spriteNumExplosion){
					case(1):
						tile[7].image = tile[5].image;
						break;
					case(2):
						tile[7].image = explosion_dark_f1;
						break;
					case(3):
						tile[7].image = explosion_dark_f1;
						break;
					case(4):
						tile[7].image = explosion_dark_f2;
						break;
					case(5):
						tile[7].image = explosion_dark_f2;
						break;
					case(6):
						tile[7].image = explosion_dark_f3;
						break;
					case(7):
						tile[7].image = explosion_dark_f3;
						break;
					case(8):
						tile[7].image = explosion_dark_f4;
						break;
					case(9):
						tile[7].image = explosion_dark_f4;
						break;
					case(10):
						tile[7].image = explosion_dark_f5;
						break;
					case(11):
						tile[7].image = explosion_dark_f5;
						break;
					case(12):
						tile[7].image = explosion_dark_f5;
						break;
					case(13):
						tile[7].image = explosion_dark_f6;
						break;
					case(14):
						tile[7].image = explosion_dark_f6;
						break;
					case(15):
						tile[7].image = explosion_dark_f6;
						break;
					case(16):
						tile[7].image = explosion_dark_f6;
						break;
					case(17):
						tile[7].image = tile[5].image;
						break;
					case(18):
						tile[7].image = tile[5].image;
						break;
					case(19):
						tile[7].image = tile[5].image;
						break;
					case(20):
						tile[7].image = tile[5].image;
						break;
					case(21):
						tile[7].image = tile[5].image;
						break;
					case(22):
						int counterX = 0;
						int counterY = 0;
						while(counterX < gp.maxWorldCol && counterY < gp.maxWorldRow) {
							
							while(counterX < gp.maxWorldCol) {
								
								if(mapTileNum[counterX][counterY] == 7) {
									mapTileNum[counterX][counterY] = 5;
								}
								counterX++;
							}
							if(counterX == gp.maxWorldCol) {
								counterX = 0;
								counterY++;
							}
						}
						break;
				}
				spriteCounterExplosion = 0;
				startExplosion = true;
			}
		}
	}
	
	public void update() {
		
		spawnFinish();
		explosionSprite();
		
		inChunk();
		
//		rubinSprite();
		finishSprite();
		
		//Kamerafahrt
		if(kameraFahrtX == true) {
			actualXkameraMovement = chunks[0][1].sbLeft * gp.tileSize - chunks[0][0].sbLeft * gp.tileSize - counterXkamera;
			counterXkamera += 16;
			if(actualXkameraMovement == 0) {
				kameraFahrtX = false;
				counterXkamera = 0;
			}
		}
		if(kameraFahrtY == true) {
			actualYkameraMovement = chunks[1][0].sbUp * gp.tileSize - chunks[0][0].sbUp * gp.tileSize - counterYkamera;
			counterYkamera += 8;
			if(actualYkameraMovement == 0) {
				kameraFahrtY = false;
				counterYkamera = 0;
			}
		}
		
		//Sprite Wechsel
		spriteCounter++;
		if(spriteCounter > 6) {
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
				spriteNum = 13;
			}
			else if(spriteNum == 13) {
				spriteNum = 14;
			}
			else if(spriteNum == 14) {
				spriteNum = 15;
			}
			else if(spriteNum == 15) {
				spriteNum = 16;
			}
			else if(spriteNum == 16) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}

	}
	
	
	//Chunk-Kamera
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			if(tileNum == 7) {
				startExplosion = true;
			}
//			rubinFallCounter++;
//			if(rubinFallCounter > 18 && gp.animationPause == false) {
//				rubinFall(worldCol, worldRow);
//				rubinFallCounter = 0;
//			}
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = 0;
			int screenY = 0;
			
			if(counterXkameraPos == true) {
				screenX = worldX - chunks[playerChunkY][playerChunkX].sbLeft * gp.tileSize + actualXkameraMovement;
			}
			else {
				screenX = worldX - chunks[playerChunkY][playerChunkX].sbLeft * gp.tileSize - actualXkameraMovement;
			}
			
			if(counterYkameraPos == true) {
				screenY = worldY - chunks[playerChunkY][playerChunkX].sbUp * gp.tileSize + actualYkameraMovement;
			}
			else {
				screenY = worldY - chunks[playerChunkY][playerChunkX].sbUp * gp.tileSize - actualYkameraMovement;
			}
			
			g2.drawImage(tile[tileNum].image, screenX, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
			
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}