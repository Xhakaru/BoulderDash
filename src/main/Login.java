package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Login {

	private GamePanel gp;
	
	private Database db = new Database();
	
	//TitleScreen State
	private int titleScreenState;
	private final int titleState = 0;
	private final int loginState = 1;
	
	//Title Screen Variables
	private BufferedImage logo;
	
	//Login Screen Variables
	private int fieldSelection;
	private final int usernameField = 0;
	private final int passwordField = 1;
	private final int loginButton = 2;
	private boolean loginAllowed;
	
	//Login
	private String user;
	private String password;
	
	public Login(GamePanel gp) {
		this.gp = gp;
		setDefaultTitleScreen();
		getTileImage();
	}
	
	public void setDefaultTitleScreen() {
		titleScreenState = titleState;
		fieldSelection = usernameField;
		loginAllowed = false;
	}
	
	public void getTileImage() {
		try {
			
			logo = ImageIO.read(getClass().getResourceAsStream("/logo/BoulderDashLogo.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean compareUserDB() {
		return true;
		//Vincent
	}
	
	public void tab() {
		fieldSelection++;
		if(fieldSelection > 1 && loginAllowed == false) {
			fieldSelection = 0;
		}
		if(fieldSelection > 2) {
			fieldSelection = 0;
		}
	}
	
	public void addCharToUser(char x) {
		user = user + x;
	}
	
	public void addCharToPassword(char x) {
		password = password + x;
	}
	
	public void deleteLastChar() {
		switch(fieldSelection) {
			case(usernameField):
				if(user.length() != 0) {
					user = user.substring(0, user.length()-1);
				}
				break;
			
			case(passwordField):
				if(password.length() != 0) {
					password = password.substring(0, password.length()-1);
				}
				break;
		}
	}
	
	public void update() {
		switch(titleScreenState) {
			case(titleState):
				
				break;
			
			case(loginState):
				if(user != null && password != null) {
					loginAllowed = true;
				}
				else {
					loginAllowed = false;
				}
				break;
		}
	}
	
	public void draw(Graphics2D g2) {
		switch(titleScreenState) {
			case(titleState):
				g2.drawImage(logo, (gp.maxScreenCol/2)*gp.tileSize - 3*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize - 3*gp.tileSize, 6*gp.tileSize, 6*gp.tileSize, null);
				break;
			
			case(loginState):
				
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
				g2.setColor(Color.white);
				g2.fillRect((gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize - 3*gp.tileSize, 10*gp.tileSize, gp.tileSize);
				g2.fillRect((gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize - gp.tileSize, 10*gp.tileSize, gp.tileSize);
				if(loginAllowed == true) {
					g2.fillRect((gp.maxScreenCol/2)*gp.tileSize - 2*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize + gp.tileSize, 4*gp.tileSize, gp.tileSize);
				}
				g2.setColor(Color.red);
				switch(fieldSelection) {
					case(usernameField):
						g2.drawRect((gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize - 3*gp.tileSize, 10*gp.tileSize, gp.tileSize);
						g2.drawRect((gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize+1, (gp.maxScreenRow/2)*gp.tileSize - 3*gp.tileSize+1, 10*gp.tileSize-2, gp.tileSize-2);
						break;
					
					case(passwordField):
						g2.drawRect((gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize - gp.tileSize, 10*gp.tileSize, gp.tileSize);
						g2.drawRect((gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize+1, (gp.maxScreenRow/2)*gp.tileSize - gp.tileSize+1, 10*gp.tileSize-2, gp.tileSize-2);
						break;
					
					case(loginButton):
						g2.drawRect((gp.maxScreenCol/2)*gp.tileSize - 2*gp.tileSize, (gp.maxScreenRow/2)*gp.tileSize + gp.tileSize, 4*gp.tileSize, gp.tileSize);
						g2.drawRect((gp.maxScreenCol/2)*gp.tileSize - 2*gp.tileSize+1, (gp.maxScreenRow/2)*gp.tileSize + gp.tileSize+1, 4*gp.tileSize-2, gp.tileSize-2);
						break;
				}
				g2.setColor(Color.black);
				String text = user;
				g2.drawString(text, (gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize + gp.tileSize/16, (gp.maxScreenRow/2)*gp.tileSize - 2*gp.tileSize - gp.tileSize/12);
				text = password;
				g2.drawString(text, (gp.maxScreenCol/2)*gp.tileSize - 5*gp.tileSize + gp.tileSize/16, (gp.maxScreenRow/2)*gp.tileSize - gp.tileSize/12);
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
				text = "Login";
				g2.drawString(text, (gp.maxScreenCol/2)*gp.tileSize - gp.tileSize + gp.tileSize/16, (gp.maxScreenRow/2)*gp.tileSize + 2*gp.tileSize - gp.tileSize/4);
				break;
		}
	}
	
	//Get & Set - methods
	public int getTitleScreenState() {
		return titleScreenState;
	}
	
	public int getTitleState() {
		return titleState;
	}
	
	public int getLoginState() {
		return loginState;
	}
	
	public boolean getLoginAllowed() {
		return loginAllowed;
	}
	
	public int getFieldSelection() {
		return fieldSelection;
	}
	
	public int getUsernameField() {
		return usernameField;
	}
	
	public int getPasswordField() {
		return passwordField;
	}
	
	public int getLoginButton(){
		return loginButton;
	}
	
	public void setTitleScreenState(int x) {
		titleScreenState = x;
	}
}
