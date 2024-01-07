package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.SoundHandler;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	public int playerChunkX, playerChunkY, playerChunkXlast, playerChunkYlast;
	
	public boolean stoneCollision = false;
	public boolean fallingCol[];
	public int stoneFallCounter = 0;
	public int rubinFallCounter = 0;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	private int[] finishSpawnX = {9999, 9999, 38, 18};
	private int[] finishSpawnY = {9999, 9999, 16, 20};
	private int[] rubineFinishSpawn = {9999, 9999, 16, 14};
	
	BufferedImage rubin_f1, rubin_f2, rubin_f3, rubin_f4, rubin_f5, rubin_f6, rubin_f7, rubin_f8;
	BufferedImage finish_grey_f1, finish_grey_f2, finish_grey_f3;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		setFallingColFalse();
		
		getTileImage();
		switch(gp.welt) {
			case(3):
				loadMap("/maps/world03.txt");
				break;
			case(4):
				loadMap("/maps/world04.txt");
				break;
		}
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
			rubin_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f1.png"));
			rubin_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f2.png"));
			rubin_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f3.png"));
			rubin_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f4.png"));
			rubin_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f5.png"));
			rubin_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f6.png"));
			rubin_f7 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f7.png"));
			rubin_f8 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f8.png"));
			tile[1].eat = true;
			tile[1].item = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_gray.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick_gray.png"));
			tile[3].collision = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone_dark_gray.png"));
			tile[4].stone = true;
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));
			
			tile[6] = new Tile();
			finish_grey_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f1.png"));
			finish_grey_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f2.png"));
			finish_grey_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/finish_grey_f3.png"));
			tile[6].finish = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String map) {
		try {
			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(Exception e){
			
		}
	}
	
	public void eaten(int worldX, int worldY) {
		
		int tileCol = worldX / gp.tileSize;
		int tileRow = worldY / gp.tileSize;
		mapTileNum[tileCol][tileRow] = 5;
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
			if(mapTileNum[tileCol + 2][tileRow] == 5) {
				stoneCollision = false;
				mapTileNum[tileCol + 1][tileRow] = 5;
				
				tileCol = x / gp.tileSize;
				tileRow = y / gp.tileSize;
				mapTileNum[tileCol + 1][tileRow] = 4;
			}
			else {
				stoneCollision = true;
			}
		}
		else if(varX < 0) {
			tileCol = c / gp.tileSize;
			tileRow = v / gp.tileSize;
			if(mapTileNum[tileCol - 2][tileRow] == 5) {
				stoneCollision = false;
				mapTileNum[tileCol - 1][tileRow] = 5;
				
				tileCol = x / gp.tileSize;
				tileRow = y / gp.tileSize;
				mapTileNum[tileCol - 1][tileRow] = 4;
			}
			else {
				stoneCollision = true;
			}
		}
	}
	
	public void stoneFall(int col, int row) {
		if(mapTileNum[col][row] == 4) {
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
			if(gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col - 1) {
				playerInWayXnegMinusOne = true;
			}
			boolean playerInWayXposMinusOne = false;
			if(gp.player.worldY / gp.tileSize == row + 1 && gp.player.worldX / gp.tileSize == col + 1) {
				playerInWayXposMinusOne = true;
			}
			if(mapTileNum[col][row + 1] == 5 && !skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col][row + 1] = 4;
//				gp.playSE("win-nt-startup");
				fallingCol[col] = true;
				if(mapTileNum[col][row + 2] != 5) {
					fallingCol[col] = false;
				}
			}
			else if(mapTileNum[col][row + 1] == 4 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
			else if(mapTileNum[col][row + 1] == 4 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
			else if(mapTileNum[col][row + 1] == 1 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
			else if(mapTileNum[col][row + 1] == 1 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 4;
//				gp.playSE("win-nt-startup");
			}
		}
	}
	
	public void rubinFall(int col, int row) {
		if(mapTileNum[col][row] == 1) {
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
			if(mapTileNum[col][row + 1] == 5 && !skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col][row + 1] = 1;
//				gp.playSE("win-nt-startup");
				fallingCol[col] = true;
				if(mapTileNum[col][row + 2] != 5) {
					fallingCol[col] = false;
				}
			}
			else if(mapTileNum[col][row + 1] == 1 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			else if(mapTileNum[col][row + 1] == 1 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			else if(mapTileNum[col][row + 1] == 4 && 
					mapTileNum[col - 1][row] == 5 && 
					mapTileNum[col - 1][row + 1] == 5 && 
					!playerInWayXneg && 
					!playerInWayXnegMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col - 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			else if(mapTileNum[col][row + 1] == 4 && 
					mapTileNum[col + 1][row] == 5 && 
					mapTileNum[col + 1][row + 1] == 5 && 
					!playerInWayXpos && 
					!playerInWayXposMinusOne && 
					!skip) {
				mapTileNum[col][row] = 5;
				mapTileNum[col + 1][row] = 1;
//				gp.playSE("win-nt-startup");
			}
			if(mapTileNum[col][row] == 1) {															//guckt ob der Spieler im Weg ist
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
			mapTileNum[finishSpawnX[gp.welt - 1]][finishSpawnY[gp.welt - 1]] = 6;
		}
	}
	
	public void update() {
		
		spawnFinish();
		
		rubinSprite();
		finishSprite();
		
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
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}