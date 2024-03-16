package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class RubinManager {
	
	private GamePanel gp;
	
	private ArrayList<Rubin> rubinList = new ArrayList();
	private int indexCounter = 999;
	
	public RubinManager(GamePanel gp) {
		this.gp = gp;
		setDefault();
	}
	
	public void setDefault() {
		rubinList.clear();
	}
	
	public void addRubinToRubinList(int worldX, int worldY) {
		Rubin x = new Rubin(gp, worldX, worldY);
		rubinList.add(x);
	}
	
	public void removeMe(int worldX, int worldY) {
		indexCounter = 0;
		for(Rubin i : rubinList) {
			if(i.worldX == worldX && i.worldY == worldY) {
				break;
			}
			indexCounter++;
		}
		
	}
	
	public void update() {
		for(Rubin i : rubinList) {
			i.update();
		}
		if(indexCounter != 999) {
			rubinList.remove(indexCounter);
		}
		indexCounter = 999;
	}
	
	public void draw(Graphics2D g2) {
		for(Rubin i : rubinList) {
			if(i.getScreenX() >= 0 && i.getScreenX() <= gp.maxScreenCol * gp.tileSize && i.getScreenY() >= 0 && i.getScreenY() <= gp.maxScreenRow * gp.tileSize) {
				g2.drawImage(i.getImage(), i.getScreenX(), i.getScreenY(), gp.tileSize, gp.tileSize, null);
			}
		}
	}
}
