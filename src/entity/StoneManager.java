package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class StoneManager {
	
	private GamePanel gp;
	
	private ArrayList<Stone> stoneList = new ArrayList();
	
	public StoneManager(GamePanel gp) {
		this.gp = gp;
		setDefault();
	}
	
	public void setDefault() {
		stoneList.clear();
	}
	
	public void addStoneToStoneList(int worldX, int worldY) {
		Stone x = new Stone(gp, worldX, worldY);
		stoneList.add(x);
	}
	
	public void stonePush(int worldX, int worldY, int Xdirection) {
		for(Stone i : stoneList) {
			if(i.worldX == worldX + Xdirection && i.worldY == worldY) {
				i.stonePush(Xdirection);
			}
		}
	}
	
	public void update() {
		for(Stone i : stoneList) {
			i.update();
		}
	}
	
	public void draw(Graphics2D g2) {
		for(Stone i : stoneList) {
			if(i.getScreenX() >= 0 && i.getScreenX() <= gp.maxScreenCol * gp.tileSize && i.getScreenY() >= 0 && i.getScreenY() <= gp.maxScreenRow * gp.tileSize) {
				g2.drawImage(i.getImage(), i.getScreenX(), i.getScreenY(), gp.tileSize, gp.tileSize, null);
			}
		}
	}
}
