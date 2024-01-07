package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Levelbord {
	
	private GamePanel gp;
	public Tile[] tileBord;
	public Tile[] tileScreened;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int timeCounter = 0;
	public int worldTimeSubtractor = 0;
	private int defaultWorldTime = 300;
	BufferedImage hourglass_f1, hourglass_f2, hourglass_f3, hourglass_f4, hourglass_f5, hourglass_f6;
	BufferedImage herz_f1, herz_f2, herz_f3;
	
	public Levelbord(GamePanel gp) {
		this.gp = gp;
		
		tileBord = new Tile[20];
		tileScreened = new Tile[gp.screenWidth];
		getTileImage();
		setDefaultTileBord();
	}
	
	public void getTileImage() {
		try {
			
			//0-9 sind die Ziffern
			tileBord[0] = new Tile();
			tileBord[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/0.png"));
			
			tileBord[1] = new Tile();
			tileBord[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/1.png"));
			
			tileBord[2] = new Tile();
			tileBord[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/2.png"));
			
			tileBord[3] = new Tile();
			tileBord[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/3.png"));
			
			tileBord[4] = new Tile();
			tileBord[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/4.png"));
			
			tileBord[5] = new Tile();
			tileBord[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/5.png"));
			
			tileBord[6] = new Tile();
			tileBord[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/6.png"));
			
			tileBord[7] = new Tile();
			tileBord[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/7.png"));
			
			tileBord[8] = new Tile();
			tileBord[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/8.png"));
			
			tileBord[9] = new Tile();
			tileBord[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/9.png"));
			
			//10 ist der Null-Wert also schwarz
			tileBord[10] = new Tile();
			tileBord[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/black.png"));
			
			tileBord[11] = new Tile();
			hourglass_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f1.png"));
			hourglass_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f2.png"));
			hourglass_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f3.png"));
			hourglass_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f4.png"));
			hourglass_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f5.png"));
			hourglass_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f6.png"));
			
			tileBord[12] = new Tile();
			herz_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/herz/herz_f1.png"));
			herz_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/herz/herz_f2.png"));
			herz_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/herz/herz_f3.png"));
			
			tileBord[13] = new Tile();
			tileBord[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray-v1.png"));
			
			tileBord[14] = new Tile();
			tileBord[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray-v1.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaultTileBord() {
		//all black
		for(int i = 0; i < gp.maxScreenCol; i++) {
			tileScreened[i] = tileBord[10];
		}
	}
	
	public void resetTime() {
		setDefaultTileBord();
		if(gp.welt == 3) {
			worldTimeSubtractor = gp.time;
		}
		if(gp.welt == 4) {
			worldTimeSubtractor = gp.time;
		}
	}
	
	public void updateTileBordRubinCounter() {
		
		int anzahlRubine = gp.player.rubinCounter;
	    String str = Integer.toString(anzahlRubine);
	    
	    int[] rubineArray = new int[str.length()];
	    
	    int j = 0;
	    for (char c : str.toCharArray()) {
	    	rubineArray[j++] = Character.getNumericValue(c);
	    }
	    
	    int i = -str.length() - 2;
	    for (int k : rubineArray) {
	    	tileScreened[gp.maxScreenCol + i] = tileBord[k];
	    	i++;
	    }
	}
	
	public void updateTileBordTime() {
		int halfPanel = Math.round( gp.maxScreenCol / 2 );
		tileScreened[halfPanel + 1] = tileBord[11];
		
		for(int i = 0; i < 3; i++) {
			tileScreened[halfPanel - 2 + i] = tileBord[0];
		}
		
		//Zeit in zahlen auf half -2 bis 0
		int anzahlZeit = defaultWorldTime - gp.time + worldTimeSubtractor;
		
		if(anzahlZeit >= 0){
			String str = Integer.toString(anzahlZeit);
	    	
	    	int[] zeitArray = new int[str.length()];
	    	
	    	int j = 0;
	    	for (char c : str.toCharArray()) {
	    		zeitArray[j++] = Character.getNumericValue(c);
	    	}
	    	
	    	int i = -str.length() - halfPanel + 1;
	    	for (int k : zeitArray) {
	    		tileScreened[gp.maxScreenCol + i] = tileBord[k];
	    		i++;
	    	}
		}
		
		if(anzahlZeit <= 0) {
			gp.player.sterben("Zeit");
		}
	}
	
	public void updateTileBordLeben() {
		tileScreened[1] = tileBord[gp.player.leben];
		tileScreened[2] = tileBord[12];
	}
	
	public void hourglassSprite() {
		if(spriteNum == 1) {
			tileBord[11].image = hourglass_f1;
		}
		if(spriteNum == 2) {
			tileBord[11].image = hourglass_f1;
		}
		if(spriteNum == 3) {
			tileBord[11].image = hourglass_f1;
		}
		if(spriteNum == 4) {
			tileBord[11].image = hourglass_f2;
		}
		if(spriteNum == 5) {
			tileBord[11].image = hourglass_f2;
		}
		if(spriteNum == 6) {
			tileBord[11].image = hourglass_f3;
		}
		if(spriteNum == 7) {
			tileBord[11].image = hourglass_f3;
		}
		if(spriteNum == 8) {
			tileBord[11].image = hourglass_f4;
		}
		if(spriteNum == 9) {
			tileBord[11].image = hourglass_f4;
		}
		if(spriteNum == 10) {
			tileBord[11].image = hourglass_f4;
		}
		if(spriteNum == 11) {
			tileBord[11].image = hourglass_f5;
		}
		if(spriteNum == 12) {
			tileBord[11].image = hourglass_f5;
		}
		if(spriteNum == 13) {
			tileBord[11].image = hourglass_f6;
		}
		if(spriteNum == 14) {
			tileBord[11].image = hourglass_f6;
		}
	}
	
	public void herzSprite() {
		if(spriteNum == 1) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 2) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 3) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 4) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 5) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 6) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 7) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 8) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 9) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 10) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 11) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 12) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 13) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 14) {
			tileBord[12].image = herz_f2;
		}
	}
	
	public void update() {
		
		spriteCounter++;
		if(spriteCounter > 16) {
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
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		updateTileBordTime();
		updateTileBordLeben();
		updateTileBordRubinCounter();
		
		hourglassSprite();
		herzSprite();
	}
	
	public void draw(Graphics2D g2) {
		
		int bordCol = 0;
		
		while(bordCol < gp.maxScreenCol) {
			
			g2.drawImage(tileBord[10].image, bordCol * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
			g2.drawImage(tileScreened[bordCol].image, bordCol * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
			
			
			bordCol++;
		}
		g2.drawImage(gp.tileM.tile[1].image, (gp.maxScreenCol-2) * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
		
	}
}
