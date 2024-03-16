package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.Sound;
import main.SoundHandler;
import tile.TileManager;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	public int rubinCounter;
	public int leben = 3;
	public int varX = 0;
	public int varY = 0;
	private int[] worldSpawnX = {8, 8, 3, 18, 1};
	private int[] worldSpawnY = {6, 6, 2, 19, 1};
	
	private BufferedImage image = null;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		
		
		setDefaultValues(gp.welt);
		getPlayerImage();
	}
	
	public void setDefaultValues(int welt) {
		
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		worldX = gp.tileSize * worldSpawnX[welt-1];
		worldY = gp.tileSize * worldSpawnY[welt-1];
		speed = gp.tileSize;
		direction = "down";
		Xdirection = "right";
		Ydirection = "down";
		rubinCounter = 0;
	}
	
	public void getPlayerImage() {
		
		try {
			
			player_gray_u1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_u1.png"));
			player_gray_u2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_u2.png"));
			player_gray_o1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_o1.png"));
			player_gray_o2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_o2.png"));
			player_gray_r1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_r1.png"));
			player_gray_r2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_r2.png"));
			player_gray_l1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_l1.png"));
			player_gray_l2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_l2.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updatePlayerImage() {
		switch(Ydirection) {
		case "up":
			switch(Xdirection) {
			case "right":
				if(spriteNum == 1) {
					image = player_gray_r1;
				}
				if(spriteNum == 2) {
					image = player_gray_r2;
				}
				if(spriteNum == 3) {
					image = player_gray_r1;
				}
				if(spriteNum == 4) {
					image = player_gray_r2;
				}
				if(spriteNum == 5) {
					image = player_gray_r1;
				}
				if(spriteNum == 6) {
					image = player_gray_r2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = player_gray_o1;
				}
				if(spriteNum == 2) {
					image = player_gray_o2;
				}
				if(spriteNum == 3) {
					image = player_gray_o1;
				}
				if(spriteNum == 4) {
					image = player_gray_o2;
				}
				if(spriteNum == 5) {
					image = player_gray_o1;
				}
				if(spriteNum == 6) {
					image = player_gray_o2;
				}
				break;
			}
			break;
		case "down":
			switch(Xdirection) {
			case "right":
				if(spriteNum == 1) {
					image = player_gray_u1;
				}
				if(spriteNum == 2) {
					image = player_gray_u2;
				}
				if(spriteNum == 3) {
					image = player_gray_u1;
				}
				if(spriteNum == 4) {
					image = player_gray_u2;
				}
				if(spriteNum == 5) {
					image = player_gray_u1;
				}
				if(spriteNum == 6) {
					image = player_gray_u2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = player_gray_l1;
				}
				if(spriteNum == 2) {
					image = player_gray_l2;
				}
				if(spriteNum == 3) {
					image = player_gray_l1;
				}
				if(spriteNum == 4) {
					image = player_gray_l2;
				}
				if(spriteNum == 5) {
					image = player_gray_l1;
				}
				if(spriteNum == 6) {
					image = player_gray_l2;
				}
				break;
			}
			break;
		}
	}

	public void chunkUpdate() {
		screenX = worldX - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbLeft * gp.tileSize;
		screenY = worldY - gp.tileM.chunks[gp.tileM.playerChunkY][gp.tileM.playerChunkX].sbUp * gp.tileSize + gp.tileSize;
	}
	
	public void sterben(String grund, int col, int row, String direction) {
		gp.animation.startDeathanimation(grund, col, row, direction);
	}
	
	public void sterben2(String grund) {
		if(grund == "Gegner") {
			System.out.println("Der Gegner hat dich getötet.");
		}
		if(grund == "Gegner mit Loot") {
			System.out.println("Der Gegner hat dich getötet, er hätte etwas wichtiges dabei gehabt!");
		}
		if(grund == "Rubin Ground") {
			System.out.println("Ein Rubin hat dich zerschmettert, dabei sollst du die doch sammeln.");
		}
		if(grund == "Stein Ground") {
			System.out.println("Ein Stein hat dich zerschmettert.");
		}
		if(grund == "Zeit") {
			System.out.println("Die Zeit ist abgelaufen.");
		}
		if(grund == "Retry") {
			System.out.println("Du hast aufgegeben.");
		}
		System.out.println("Verbleibende Leben: " + leben);
		
		if(leben == 0) {
			gp.welt = 3;
			leben = 3;
			System.out.println("Du bist mit deinen neuen Leben im ersten Level neu gespawnt.");
		}
		
		gp.enemyWL.clearEnemyWithLoot();
		switch(gp.welt) {
			case(3):
				gp.tileM.loadMap("/maps/world03.txt");
				break;
			case(4):
				gp.tileM.loadMap("/maps/world04.txt");
				break;
			case(5):
				gp.tileM.loadMap("/maps/world05.txt");
				break;
		}
		setDefaultValues(gp.welt);
		gp.enemyNL.setDefaultValues();
		gp.levelB.resetTime();
	}
	
	public void update() {
		
		updatePlayerImage();
		chunkUpdate();
		
		if(keyH.backspacePressed == true) {
			keyH.backspacePressed = false;
			sterben("Retry", 0, 0, "null");
		}
		
		if(keyH.upPressed == true || 
		   keyH.downPressed == true || 
		   keyH.leftPressed == true || 
		   keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				keyH.upPressed = false;
				direction = "up";
				Ydirection = "up";
			} else if(keyH.downPressed == true) {
				keyH.downPressed = false;
				direction = "down";
				Ydirection = "down";
			} else if(keyH.leftPressed == true) {
				keyH.leftPressed = false;
				direction = "left";
				Xdirection = "left";
			} else if(keyH.rightPressed == true) {
				keyH.rightPressed = false;
				direction = "right";
				Xdirection = "right";
			}
			
			collisionOn = false;
			eat = false;
			item = false;
			stone = false;
			finish = false;
			gp.cChecker.checkTile(this);
			
			if(keyH.shiftPressed == true && direction == "left") {                            //this is for the stuff while shift is hold.
                direction = "";
                int row = worldX / gp.tileSize;
                row = row -1;
                int col = worldY / gp.tileSize;
                if(gp.tileM.mapTileNum[row][col] == 0 || gp.tileM.mapTileNum[row][col] == 1) {
                	gp.tileM.mapTileNum[row][col] = 5;
                }
            }
            if(keyH.shiftPressed == true && direction == "right") {
                direction = "";
                int row = worldX / gp.tileSize;
                row = row +1;
                int col = worldY / gp.tileSize;
                if(gp.tileM.mapTileNum[row][col] == 0 || gp.tileM.mapTileNum[row][col] == 1) {
                    gp.tileM.mapTileNum[row][col] = 5;
                }
            }
            if(keyH.shiftPressed == true && direction == "up") {
                direction = "";
                int row = worldX / gp.tileSize;
                int col = worldY / gp.tileSize;
                col = col -1;
                if(gp.tileM.mapTileNum[row][col] == 0 || gp.tileM.mapTileNum[row][col] == 1) {
                    gp.tileM.mapTileNum[row][col] = 5;
                }
            }
            if(keyH.shiftPressed == true && direction == "down") {
                direction = "";
                int row = worldX / gp.tileSize;
                int col = worldY / gp.tileSize;
                col = col +1;
                if(gp.tileM.mapTileNum[row][col] == 0 || gp.tileM.mapTileNum[row][col] == 1) {
                    gp.tileM.mapTileNum[row][col] = 5;
                }
            }
			
			if(stone == true) {
				switch(direction) {
				case "left":
					varX = -gp.tileSize;
					gp.stoneM.stonePush(worldX, worldY, varX);
					System.out.println("leftEaten");
			        break;
				case "right":
					varX = gp.tileSize;
					gp.stoneM.stonePush(worldX, worldY, varX);
					System.out.println("rightEaten");
			        break;
				}
			}
			
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					keyH.upPressed = false;
					worldY -= speed;
					screenY -= speed;
		        	break;
				case "down":
					keyH.downPressed = false;
					worldY += speed;
					screenY += speed;
			        break;
				case "left":
					keyH.leftPressed = false;
					worldX -= speed;
					screenX -= speed;
			        break;
				case "right":
					keyH.rightPressed = false;
					worldX += speed;
					screenX += speed;
			        break;
				}
			}
			else if(collisionOn == true && stone == true && gp.tileM.stoneCollision == false) {
				switch(direction) {
				case "left":
					keyH.leftPressed = false;
					worldX -= speed;
					screenX -= speed;
					break;
				case "right":
					keyH.rightPressed = false;
					worldX += speed;
					screenX += speed;
					break;
				}
			}
			
			if(eat == true) {
				gp.tileM.eaten(worldX, worldY);
			}
			
			if(item == true) {
				rubinCounter++;
				System.out.println("Rubine gesammelt: " + rubinCounter);
				SoundHandler.getSound("eat").play();
			}
			
			if(finish == true) {
				if(gp.welt != 5) {
					System.out.println("Level geschafft! Let's go ins Nächste.");
					gp.welt += 1;
					switch(gp.welt) {
						case(3):
							gp.tileM.loadMap("/maps/world03.txt");
							break;
						case(4):
							gp.tileM.loadMap("/maps/world04.txt");
							break;
						case(5):
							gp.tileM.loadMap("/maps/world05.txt");
							break;
					}
					setDefaultValues(gp.welt);
					gp.enemyNL.setDefaultValues();
					gp.levelB.resetTime();
				}
				else {
					System.out.println("Level geschafft! Du hast das Spiel durchgespielt.");
					Sound endSound = SoundHandler.getSound("nyan-cat");
					endSound.setVolume(.5f);
					endSound.play();
					gp.stopMusic("blue-boy-adventure");
					gp.animation.startEndanimation();
				}
			}
			
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
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		else
		{
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
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		if(gp.tileM.counterXkameraPos == true) {
			screenX += gp.tileM.actualXkameraMovement;
		}
		else {
			screenX -= gp.tileM.actualXkameraMovement;
		}
		
		if(gp.tileM.counterYkameraPos == true) {
			screenY += gp.tileM.actualYkameraMovement;
		}
		else {
			screenY -= gp.tileM.actualYkameraMovement;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
}