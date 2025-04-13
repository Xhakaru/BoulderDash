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
	public boolean rubineGesammelt = false;
	
	//Gesamte Zeit pro Level + Zeit die Rot aufblinkt + ab wann die Ausrufezeichen erscheinen
	private static int defaultWorldTime = 150;
	private static int redTime = 50;
	private static int speedZeit = 10;
	
	private BufferedImage hourglass_f1, hourglass_f2, hourglass_f3, hourglass_f4, hourglass_f5, hourglass_f6; 
	private BufferedImage hourglass_f1_red, hourglass_f2_red, hourglass_f3_red, hourglass_f4_red, hourglass_f5_red, hourglass_f6_red;
	private BufferedImage hourglass_f1_red_white, hourglass_f2_red_white, hourglass_f3_red_white, hourglass_f4_red_white, hourglass_f5_red_white, hourglass_f6_red_white;
	private BufferedImage hourglass_f1_black, hourglass_f2_black, hourglass_f3_black, hourglass_f4_black, hourglass_f5_black, hourglass_f6_black;
	private BufferedImage herz_f1, herz_f2, herz_f3;
	private BufferedImage exclamation_mark_r_f1, exclamation_mark_r_f2, exclamation_mark_l_f1, exclamation_mark_l_f2;
	private BufferedImage exclamation_mark_r_f1_white, exclamation_mark_r_f2_white, exclamation_mark_l_f1_white, exclamation_mark_l_f2_white;
	
	public Levelbord(GamePanel gp) {
		this.gp = gp;
		
		tileBord = new Tile[60];
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
			exclamation_mark_r_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_r_f1.png"));
			exclamation_mark_r_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_r_f2.png"));
			
			tileBord[14] = new Tile();
			exclamation_mark_l_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_l_f1.png"));
			exclamation_mark_l_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_l_f2.png"));
			
			tileBord[15] = new Tile();
			hourglass_f1_red = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f1_red.png"));
			hourglass_f2_red = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f2_red.png"));
			hourglass_f3_red = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f3_red.png"));
			hourglass_f4_red = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f4_red.png"));
			hourglass_f5_red = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f5_red.png"));
			hourglass_f6_red = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f6_red.png"));
			
			tileBord[16] = new Tile();
			hourglass_f1_black = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f1_black.png"));
			hourglass_f2_black = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f2_black.png"));
			hourglass_f3_black = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f3_black.png"));
			hourglass_f4_black = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f4_black.png"));
			hourglass_f5_black = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f5_black.png"));
			hourglass_f6_black = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f6_black.png"));
			
			//ein weißes Teil
			tileBord[17] = new Tile();
			tileBord[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/white.png"));
			
			tileBord[18] = new Tile();
			hourglass_f1_red_white = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f1_red_white.png"));
			hourglass_f2_red_white = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f2_red_white.png"));
			hourglass_f3_red_white = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f3_red_white.png"));
			hourglass_f4_red_white = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f4_red_white.png"));
			hourglass_f5_red_white = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f5_red_white.png"));
			hourglass_f6_red_white = ImageIO.read(getClass().getResourceAsStream("/tiles/hourglass/hourglass_f6_red_white.png"));
			
			tileBord[20] = new Tile();
			tileBord[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/0_red.png"));
			
			tileBord[21] = new Tile();
			tileBord[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/1_red.png"));
			
			tileBord[22] = new Tile();
			tileBord[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/2_red.png"));
			
			tileBord[23] = new Tile();
			tileBord[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/3_red.png"));
			
			tileBord[24] = new Tile();
			tileBord[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/4_red.png"));
			
			tileBord[25] = new Tile();
			tileBord[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/5_red.png"));
			
			tileBord[26] = new Tile();
			tileBord[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/6_red.png"));
			
			tileBord[27] = new Tile();
			tileBord[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/7_red.png"));
			
			tileBord[28] = new Tile();
			tileBord[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/8_red.png"));
			
			tileBord[29] = new Tile();
			tileBord[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/9_red.png"));
			
			tileBord[30] = new Tile();
			tileBord[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/0_black.png"));
			
			tileBord[31] = new Tile();
			tileBord[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/1_black.png"));
			
			tileBord[32] = new Tile();
			tileBord[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/2_black.png"));
			
			tileBord[33] = new Tile();
			tileBord[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/3_black.png"));
			
			tileBord[34] = new Tile();
			tileBord[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/4_black.png"));
			
			tileBord[35] = new Tile();
			tileBord[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/5_black.png"));
			
			tileBord[36] = new Tile();
			tileBord[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/6_black.png"));
			
			tileBord[37] = new Tile();
			tileBord[37].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/7_black.png"));
			
			tileBord[38] = new Tile();
			tileBord[38].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/8_black.png"));
			
			tileBord[39] = new Tile();
			tileBord[39].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/black/9_black.png"));
			
			tileBord[40] = new Tile();
			tileBord[40].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/0_red_white.png"));
			
			tileBord[41] = new Tile();
			tileBord[41].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/1_red_white.png"));
			
			tileBord[42] = new Tile();
			tileBord[42].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/2_red_white.png"));
			
			tileBord[43] = new Tile();
			tileBord[43].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/3_red_white.png"));
			
			tileBord[44] = new Tile();
			tileBord[44].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/4_red_white.png"));
			
			tileBord[45] = new Tile();
			tileBord[45].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/5_red_white.png"));
			
			tileBord[46] = new Tile();
			tileBord[46].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/6_red_white.png"));
			
			tileBord[47] = new Tile();
			tileBord[47].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/7_red_white.png"));
			
			tileBord[48] = new Tile();
			tileBord[48].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/8_red_white.png"));
			
			tileBord[49] = new Tile();
			tileBord[49].image = ImageIO.read(getClass().getResourceAsStream("/tiles/numbers/red/9_red_white.png"));
			
			tileBord[50] = new Tile();
			exclamation_mark_r_f1_white = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_r_f1_white.png"));
			exclamation_mark_r_f2_white = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_r_f2_white.png"));
			
			tileBord[51] = new Tile();
			exclamation_mark_l_f1_white = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_l_f1_white.png"));
			exclamation_mark_l_f2_white = ImageIO.read(getClass().getResourceAsStream("/tiles/exclamation_mark/exclamation_mark_l_f2_white.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaultTileBord() {
		//all black
		for(int i = 0; i < gp.maxScreenCol; i++) {
			if(rubineGesammelt == false) {
				tileScreened[i] = tileBord[10];
			}
			else {
				tileScreened[i] = tileBord[17];
			}
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
		if(gp.welt == 5) {
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
	    	if(rubineGesammelt == false) {
	    		//tileScreened[gp.maxScreenCol + i] = tileBord[k];
	    		tileScreened[40 + i] = tileBord[k];
	    	}
	    	else {
	    		//tileScreened[gp.maxScreenCol + i] = tileBord[k + 30];
	    		tileScreened[40 + i] = tileBord[k + 30];
	    	}
	    	i++;
	    }
	}
	
	public void updateTileBordTime() {
		int halfPanel = Math.round( gp.maxScreenCol / 2 );
		if(rubineGesammelt == false) {
			tileScreened[halfPanel + 1] = tileBord[11];
		}
		else {
			tileScreened[halfPanel + 1] = tileBord[16];
		}
		
		for(int i = 0; i < 3; i++) {
			if(rubineGesammelt == false) {
				tileScreened[halfPanel - 2 + i] = tileBord[0];
			}
			else {
				tileScreened[halfPanel - 2 + i] = tileBord[30];
			}
		}
		
		//Zeit in zahlen auf half -2 bis 0
		int anzahlZeit = defaultWorldTime - gp.time + worldTimeSubtractor;
		
		if(anzahlZeit > redTime){
			String str = Integer.toString(anzahlZeit);
	    	
	    	int[] zeitArray = new int[str.length()];
	    	
	    	int j = 0;
	    	for (char c : str.toCharArray()) {
	    		zeitArray[j++] = Character.getNumericValue(c);
	    	}
	    	
	    	int i = -str.length() - halfPanel + 1;
	    	for (int k : zeitArray) {
	    		if(rubineGesammelt == false) {
	    			tileScreened[gp.maxScreenCol + i] = tileBord[k];
	    		}
	    		else {
	    			tileScreened[gp.maxScreenCol + i] = tileBord[k + 30];
	    		}
	    		i++;
	    	}
		}
		else if(anzahlZeit <= redTime && anzahlZeit >= 0){
			int colorChange = 0;
			if(anzahlZeit % 2 == 1) {
				colorChange = 0;
				if(rubineGesammelt == false) {
					tileScreened[gp.maxScreenCol - halfPanel + 1] = tileBord[11];
					tileScreened[gp.maxScreenCol - halfPanel - 2] = tileBord[0];
					tileScreened[gp.maxScreenCol - halfPanel - 1] = tileBord[0];
				}
				else {
					tileScreened[gp.maxScreenCol - halfPanel + 1] = tileBord[16];
					tileScreened[gp.maxScreenCol - halfPanel - 2] = tileBord[30];
					tileScreened[gp.maxScreenCol - halfPanel - 1] = tileBord[30];
				}
			}
			else {
				colorChange = 20;
				if(rubineGesammelt == false) {
					tileScreened[gp.maxScreenCol - halfPanel + 1] = tileBord[15];
					tileScreened[gp.maxScreenCol - halfPanel - 2] = tileBord[20];
					tileScreened[gp.maxScreenCol - halfPanel - 1] = tileBord[20];
				}
				else {
					tileScreened[gp.maxScreenCol - halfPanel + 1] = tileBord[18];
					tileScreened[gp.maxScreenCol - halfPanel - 2] = tileBord[40];
					tileScreened[gp.maxScreenCol - halfPanel - 1] = tileBord[40];
				}
			}
			
			String str = Integer.toString(anzahlZeit);
	    	
	    	int[] zeitArray = new int[str.length()];
	    	
	    	int j = 0;
	    	for (char c : str.toCharArray()) {
	    		zeitArray[j++] = Character.getNumericValue(c);
	    	}
	    	
	    	int i = -str.length() - halfPanel + 1;
	    	for (int k : zeitArray) {
	    		if(rubineGesammelt == false) {
	    			tileScreened[gp.maxScreenCol + i] = tileBord[k + colorChange];
	    		}
	    		else {
	    			tileScreened[gp.maxScreenCol + i] = tileBord[k + 30 + colorChange / 2];
	    		}
	    		i++;
	    	}
		}
		else {
			gp.player.sterben("Zeit", 0, 0, "null");
		}
		
		if(anzahlZeit <= speedZeit) {
			if(rubineGesammelt == false) {
				tileScreened[gp.maxScreenCol - halfPanel - 3] = tileBord[13];
				tileScreened[gp.maxScreenCol - halfPanel + 2] = tileBord[14];
			}
			else {
				tileScreened[gp.maxScreenCol - halfPanel - 3] = tileBord[50];
				tileScreened[gp.maxScreenCol - halfPanel + 2] = tileBord[51];
			}
		}
	}
	
	public void updateTileBordLeben() {
		if(rubineGesammelt == false) {
			tileScreened[1] = tileBord[gp.player.leben];
			tileScreened[2] = tileBord[12];
		}
		else {
			tileScreened[1] = tileBord[gp.player.leben + 30];
			tileScreened[2] = tileBord[12];
		}
	}
	
	public void hourglassSprite() {
		if(spriteNum == 1) {
			tileBord[11].image = hourglass_f1;
			tileBord[15].image = hourglass_f1_red;
			tileBord[16].image = hourglass_f1_black;
			tileBord[18].image = hourglass_f1_red_white;
		}
		if(spriteNum == 2) {
			tileBord[11].image = hourglass_f1;
			tileBord[15].image = hourglass_f1_red;
			tileBord[16].image = hourglass_f1_black;
			tileBord[18].image = hourglass_f1_red_white;
		}
		if(spriteNum == 3) {
			tileBord[11].image = hourglass_f1;
			tileBord[15].image = hourglass_f1_red;
			tileBord[16].image = hourglass_f1_black;
			tileBord[18].image = hourglass_f1_red_white;
		}
		if(spriteNum == 4) {
			tileBord[11].image = hourglass_f2;
			tileBord[15].image = hourglass_f2_red;
			tileBord[16].image = hourglass_f2_black;
			tileBord[18].image = hourglass_f2_red_white;
		}
		if(spriteNum == 5) {
			tileBord[11].image = hourglass_f2;
			tileBord[15].image = hourglass_f2_red;
			tileBord[16].image = hourglass_f2_black;
			tileBord[18].image = hourglass_f2_red_white;
		}
		if(spriteNum == 6) {
			tileBord[11].image = hourglass_f3;
			tileBord[15].image = hourglass_f3_red;
			tileBord[16].image = hourglass_f3_black;
			tileBord[18].image = hourglass_f3_red_white;
		}
		if(spriteNum == 7) {
			tileBord[11].image = hourglass_f3;
			tileBord[15].image = hourglass_f3_red;
			tileBord[16].image = hourglass_f3_black;
			tileBord[18].image = hourglass_f3_red_white;
		}
		if(spriteNum == 8) {
			tileBord[11].image = hourglass_f4;
			tileBord[15].image = hourglass_f4_red;
			tileBord[16].image = hourglass_f4_black;
			tileBord[18].image = hourglass_f4_red_white;
		}
		if(spriteNum == 9) {
			tileBord[11].image = hourglass_f4;
			tileBord[15].image = hourglass_f4_red;
			tileBord[16].image = hourglass_f4_black;
			tileBord[18].image = hourglass_f4_red_white;
		}
		if(spriteNum == 10) {
			tileBord[11].image = hourglass_f4;
			tileBord[15].image = hourglass_f4_red;
			tileBord[16].image = hourglass_f4_black;
			tileBord[18].image = hourglass_f4_red_white;
		}
		if(spriteNum == 11) {
			tileBord[11].image = hourglass_f5;
			tileBord[15].image = hourglass_f5_red;
			tileBord[16].image = hourglass_f5_black;
			tileBord[18].image = hourglass_f5_red_white;
		}
		if(spriteNum == 12) {
			tileBord[11].image = hourglass_f5;
			tileBord[15].image = hourglass_f5_red;
			tileBord[16].image = hourglass_f5_black;
			tileBord[18].image = hourglass_f5_red_white;
		}
		if(spriteNum == 13) {
			tileBord[11].image = hourglass_f6;
			tileBord[15].image = hourglass_f6_red;
			tileBord[16].image = hourglass_f6_black;
			tileBord[18].image = hourglass_f6_red_white;
		}
		if(spriteNum == 14) {
			tileBord[11].image = hourglass_f6;
			tileBord[15].image = hourglass_f6_red;
			tileBord[16].image = hourglass_f6_black;
			tileBord[18].image = hourglass_f6_red_white;
		}
	}
	
	public void herzSprite() {
		if(spriteNum == 1) {
			tileBord[12].image = herz_f1;
		}
		if(spriteNum == 2) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 3) {
			tileBord[12].image = herz_f3;
		}
		if(spriteNum == 4) {
			tileBord[12].image = herz_f1;
		}
		if(spriteNum == 5) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 6) {
			tileBord[12].image = herz_f3;
		}
		if(spriteNum == 7) {
			tileBord[12].image = herz_f1;
		}
		if(spriteNum == 8) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 9) {
			tileBord[12].image = herz_f3;
		}
		if(spriteNum == 10) {
			tileBord[12].image = herz_f1;
		}
		if(spriteNum == 11) {
			tileBord[12].image = herz_f2;
		}
		if(spriteNum == 12) {
			tileBord[12].image = herz_f3;
		}
		if(spriteNum == 13) {
			tileBord[12].image = herz_f1;
		}
		if(spriteNum == 14) {
			tileBord[12].image = herz_f2;
		}
	}
	
	public void exclamationMarkSprite() {
		switch(spriteNum) {
			case(1):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(2):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
			case(3):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(4):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
			case(5):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(6):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
			case(7):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(8):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
			case(9):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(10):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
			case(11):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(12):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
			case(13):
				tileBord[13].image = exclamation_mark_r_f1;
				tileBord[14].image = exclamation_mark_l_f1;
				tileBord[50].image = exclamation_mark_r_f1_white;
				tileBord[51].image = exclamation_mark_l_f1_white;
				break;
			case(14):
				tileBord[13].image = exclamation_mark_r_f2;
				tileBord[14].image = exclamation_mark_l_f2;
				tileBord[50].image = exclamation_mark_r_f2_white;
				tileBord[51].image = exclamation_mark_l_f2_white;
				break;
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
		
		exclamationMarkSprite();
		hourglassSprite();
		herzSprite();
	}
	
	public void draw(Graphics2D g2) {
		
		int bordCol = 0;
		
		while(bordCol < gp.maxScreenCol) {
			
			if(rubineGesammelt == false) {
				g2.drawImage(tileBord[10].image, bordCol * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
			}
			else {
				g2.drawImage(tileBord[17].image, bordCol * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
			}
			g2.drawImage(tileScreened[bordCol].image, bordCol * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
			
			
			bordCol++;
		}
		//g2.drawImage(gp.tileM.tile[1].image, (gp.maxScreenCol-2) * gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
		
	}
}
