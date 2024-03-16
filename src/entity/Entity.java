package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage player_gray_u1, player_gray_u2, player_gray_o1, player_gray_o2, player_gray_r1, player_gray_r2, player_gray_l1, player_gray_l2;
	public BufferedImage enemy_gray_f1, enemy_gray_f2;
	public BufferedImage butterfly_f1, butterfly_f2, butterfly_f3, butterfly_f4;
	public BufferedImage butterfly_f1_red, butterfly_f2_red, butterfly_f3_red, butterfly_f4_red;
	public String direction;
	public String Xdirection;
	public String Ydirection;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn;
	public boolean eat;
	public boolean item;
	public boolean stone;
	public boolean finish;
}