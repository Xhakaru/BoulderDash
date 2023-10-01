package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	private int rubinCounter = 0;
	int varX = 0;
	int varY = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 6;
		speed = gp.tileSize;
		direction = "down";
		Xdirection = "right";
		Ydirection = "down";
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
	
	public void update() {
		
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
			gp.cChecker.checkTile(this);
			
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					keyH.upPressed = false;
					worldY -= speed;
		        	break;
				case "down":
					keyH.downPressed = false;
					worldY += speed;
			        break;
				case "left":
					keyH.leftPressed = false;
					worldX -= speed;
			        break;
				case "right":
					keyH.rightPressed = false;
					worldX += speed;
			        break;
				}
			}
			
			if(eat == true) {
				gp.tileM.eaten(worldX, worldY);
			}
			
			if(item == true) {
				rubinCounter++;
				System.out.println("Rubine gesammelt: " + rubinCounter);
			}
			
			if(stone == true) {
				switch(direction) {
				case "up":
					varX = 0;
					varY = -48;
					gp.tileM.stonePush(worldX, worldY, varX, varY);
					System.out.println("upEaten");
		        	break;
				case "down":
					varX = 0;
					varY = 48;
					gp.tileM.stonePush(worldX, worldY, varX, varY);
					System.out.println("downEaten");
			        break;
				case "left":
					varX = -48;
					varY = 0;
					gp.tileM.stonePush(worldX, worldY, varX, varY);
					System.out.println("leftEaten");
			        break;
				case "right":
					varX = 48;
					varY = 0;
					gp.tileM.stonePush(worldX, worldY, varX, varY);
					System.out.println("rightEaten");
			        break;
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
		
		BufferedImage image = null;
		
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
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
}