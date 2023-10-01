package tile;

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
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	BufferedImage rubin_f1, rubin_f2, rubin_f3, rubin_f4, rubin_f5, rubin_f6, rubin_f7, rubin_f8;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
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
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));
			
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
		
			tileCol = x / gp.tileSize;
			tileRow = y / gp.tileSize;
			mapTileNum[tileCol][tileRow] = 4;
			
			tileCol = c / gp.tileSize;
			tileRow = v / gp.tileSize;
			mapTileNum[tileCol][tileRow] = 5;
	}
	
	public void stoneFall(int col, int row) {
		if(mapTileNum[col][row] == 4) {
			if(mapTileNum[col][row + 1] == 5) {
				mapTileNum[col][row] = 5;
				mapTileNum[col][row + 1] = 4;
				gp.playSE(5);
			}
			if(mapTileNum[col][row] == 4) {															//guckt ob der Spieler im Weg ist
				if(col == gp.player.worldX/ gp.tileSize && row == gp.player.worldY / gp.tileSize) {
					//Spieler wurde getroffen (Sound-Effect und Leben abziehen/Game Over)
				}
			}
		}
	}
	
	public void update() {
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
	
	
	//Fixierte Kamera, wird bearbeitet
	public void draw(Graphics2D g2) {
		
		rubinSprite();
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			stoneFall(worldCol, worldRow);
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}