package entity;

import main.GamePanel;
import main.KeyHandler;
import struct.Direction;
import struct.Vector2D;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
	protected final GamePanel gamePanel;
	protected final KeyHandler keyHandler;
	
	protected Vector2D position;
	protected int speed;
	
	protected BufferedImage player_gray_u1, player_gray_u2, player_gray_o1, player_gray_o2, player_gray_r1, player_gray_r2, player_gray_l1, player_gray_l2;
	protected Direction direction;
	protected Direction xDirection;
	protected Direction yDirection;
	
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	
	protected Rectangle solidArea;
	protected boolean collisionOn;
	protected boolean eat;
	protected boolean item;
	protected boolean stone;

	protected Entity(GamePanel gamePanel, KeyHandler keyHandler){
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
	}

	public void update(){
		resetGameloopStats();
		gp.cChecker.checkTile(this);
	}

	private void resetGameloopStats() {
		collisionOn = false;
		eat = false;
		item = false;
		stone = false;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getPlayer_gray_u1() {
		return player_gray_u1;
	}

	public void setPlayer_gray_u1(BufferedImage player_gray_u1) {
		this.player_gray_u1 = player_gray_u1;
	}

	public BufferedImage getPlayer_gray_u2() {
		return player_gray_u2;
	}

	public void setPlayer_gray_u2(BufferedImage player_gray_u2) {
		this.player_gray_u2 = player_gray_u2;
	}

	public BufferedImage getPlayer_gray_o1() {
		return player_gray_o1;
	}

	public void setPlayer_gray_o1(BufferedImage player_gray_o1) {
		this.player_gray_o1 = player_gray_o1;
	}

	public BufferedImage getPlayer_gray_o2() {
		return player_gray_o2;
	}

	public void setPlayer_gray_o2(BufferedImage player_gray_o2) {
		this.player_gray_o2 = player_gray_o2;
	}

	public BufferedImage getPlayer_gray_r1() {
		return player_gray_r1;
	}

	public void setPlayer_gray_r1(BufferedImage player_gray_r1) {
		this.player_gray_r1 = player_gray_r1;
	}

	public BufferedImage getPlayer_gray_r2() {
		return player_gray_r2;
	}

	public void setPlayer_gray_r2(BufferedImage player_gray_r2) {
		this.player_gray_r2 = player_gray_r2;
	}

	public BufferedImage getPlayer_gray_l1() {
		return player_gray_l1;
	}

	public void setPlayer_gray_l1(BufferedImage player_gray_l1) {
		this.player_gray_l1 = player_gray_l1;
	}

	public BufferedImage getPlayer_gray_l2() {
		return player_gray_l2;
	}

	public void setPlayer_gray_l2(BufferedImage player_gray_l2) {
		this.player_gray_l2 = player_gray_l2;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getxDirection() {
		return xDirection;
	}

	public void setxDirection(Direction xDirection) {
		this.xDirection = xDirection;
	}

	public Direction getyDirection() {
		return yDirection;
	}

	public void setyDirection(Direction yDirection) {
		this.yDirection = yDirection;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}

	public int getSpriteNum() {
		return spriteNum;
	}

	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}

	public boolean isCollisionOn() {
		return collisionOn;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}

	public boolean isEat() {
		return eat;
	}

	public void setEat(boolean eat) {
		this.eat = eat;
	}

	public boolean isItem() {
		return item;
	}

	public void setItem(boolean item) {
		this.item = item;
	}

	public boolean isStone() {
		return stone;
	}

	public void setStone(boolean stone) {
		this.stone = stone;
	}
}