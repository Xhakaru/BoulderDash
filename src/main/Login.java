package main;

import java.awt.Graphics2D;

public class Login {

	private GamePanel gp;
	
	private Database db = new Database();
	
	//TitleScreen State
	private int titleScreenState;
	private final int titleState = 0;
	private final int loginState = 1;
	
	//Login
	private String user;
	private String passwort;
	
	
	public Login(GamePanel gp) {
		this.gp = gp;
		setDefaultTitleScreen();
	}
	
	public void setDefaultTitleScreen() {
		titleScreenState = titleState;
	}
	
	public boolean compareUserDB() {
		return true;
		//Vincent
	}
	
	public void update() {
		switch(titleScreenState) {
			case(titleState):
				
				break;
			
			case(loginState):
				
				break;
		}
	}
	
	public void draw(Graphics2D g2) {
		switch(titleScreenState) {
			case(titleState):
				
				break;
			
			case(loginState):
				
				break;
		}
	}
	
	public int getTitleScreenState() {
		return titleScreenState;
	}
	
	public int getTitleState() {
		return titleState;
	}
	
	public int getLoginState() {
		return loginState;
	}
	
	public void setTitleScreenState(int x) {
		titleScreenState = x;
	}
}
