package entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

	private Point2D spawnPoint;

	GamePanel gp;
	KeyHandler keyH;

	public int screenX;
	public int screenY;
	public int rubinCounter;
	public int leben = 3;
	public int varX = 0;
	public int varY = 0;
	
	private BufferedImage image = null;
	
	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		spawnPoint = gp.currentMap.getCurrentLevel().getLevelSpawn();

		worldX = spawnPoint.getX() * gp.tileSize;
		worldY = spawnPoint.getY() * gp.tileSize;
		varX = spawnPoint.getX() * gp.tileSize;
		varY = spawnPoint.getY() * gp.tileSize;
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
	
	public void sterben(String grund) {
		//SoundHandler.getSound("win-nt").play();
		leben -= 1;
		if(grund == "Gegner") {
			System.out.println("Der Gegner hat dich getötet.");
		}
		if(grund == "Stein Air") {
			System.out.println("Ein Stein hat dich getötet.");
		}
		if(grund == "Stein Ground") {
			System.out.println("Ein Stein hat dich zerschmettert.");
		}
		if(grund == "Zeit") {
			System.out.println("Die Zeit ist abgelaufen.");
		}
		if (grund == "Retry") {
			System.out.println("Du hast aufgegeben.");
		}
		System.out.println("Verbleibende Leben: " + leben);

		if (leben == 0) {
			gp.resetMap();
			leben = 3;
			System.out.println("Du bist mit deinen neuen Leben im ersten Level neu gespawnt.");
		}


		setDefaultValues();
		gp.currentMap.getCurrentLevel().reset();
		gp.levelB.resetTime();
	}
	
	public void update() {
		
		updatePlayerImage();
		
		if(keyH.backspacePressed == true) {
			keyH.backspacePressed = false;
			sterben("Retry");
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
			
			if(stone == true) {
				switch(direction) {
				case "left":
					varX = -gp.tileSize;
					varY = 0;
					gp.tileM.stonePush(worldX, worldY, varX, varY);
					System.out.println("leftEaten");
			        break;
				case "right":
					varX = gp.tileSize;
					varY = 0;
					gp.tileM.stonePush(worldX, worldY, varX, varY);
					System.out.println("rightEaten");
			        break;
				}
			}
			
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
			else if(collisionOn == true && stone == true && gp.tileM.stoneCollision == false) {
				switch(direction) {
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
				SoundHandler.getSound("eat").play();
			}
			
			if(finish == true) {
				System.out.println("Level geschafft! Let's go ins Nächste.");
				if (!gp.currentMap.nextLevel()) {
					System.out.println("Level geschafft! Du hast das Spiel durchgespielt.");
					Sound endSound = SoundHandler.getSound("nyan-cat");
					endSound.setVolume(.5f);
					endSound.play();
					gp.stopMusic("blue-boy-adventure");
					gp.animation.startEndanimation();
				}

				gp.scheduleForNextFrame(() -> {
					gp.camera.centerPlayer();
				});
				setDefaultValues();
				gp.levelB.resetTime();
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
				} else if (spriteNum == 5) {
					spriteNum = 6;
				} else if (spriteNum == 6) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	private void toSpawn() {
		varX = spawnPoint.getX() * gp.tileSize;
		varY = spawnPoint.getY() * gp.tileSize;
	}

	public void draw(Graphics2D g2) {
		screenX = worldX - gp.tileSize + gp.camera.worldX;
		screenY = worldY + gp.camera.worldY;

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}