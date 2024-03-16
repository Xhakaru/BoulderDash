package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private GamePanel gp;
	
	//Allowed Chars for Login
	private static final String allowed_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, backspacePressed, escPressed = false, pageupPressed, pagedownPressed, shiftPressed;
	public boolean stopAnimation = false;
	public boolean lever = true;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void keyTyped(KeyEvent e) {
		if(gp.gameState == gp.titleState) {
			if(gp.login.getTitleScreenState() == gp.login.getLoginState()) {
				char typedChar = e.getKeyChar();
				if (allowed_chars.indexOf(typedChar) == -1) {
                    e.consume();
                }
				else {
					if(gp.login.getFieldSelection() == gp.login.getUsernameField()) {
						gp.login.addCharToUser(e.getKeyChar());
					}
					if(gp.login.getFieldSelection() == gp.login.getPasswordField()) {
						gp.login.addCharToPassword(e.getKeyChar());
					}
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(gp.gameState == gp.titleState) {
			if(gp.login.getTitleScreenState() == gp.login.getTitleState()) {
				if(code == KeyEvent.VK_ENTER) {
					gp.login.setTitleScreenState(gp.login.getLoginState());
				}
			}
			else if(gp.login.getTitleScreenState() == gp.login.getLoginState()) {
				if(code == 130) {
					gp.login.tab();
				}
				if(code == KeyEvent.VK_BACK_SPACE) {
					gp.login.deleteLastChar();
				}
				if(gp.login.getLoginAllowed() == true && gp.login.getFieldSelection() == gp.login.getLoginButton()) {
					if(code == KeyEvent.VK_ENTER) {
						if(gp.login.compareUserDB()) {
							gp.gameState = gp.playState;
						}
					}
				}
			}
		}
		else if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_SHIFT) {
				shiftPressed = true;
			}
			if(code == KeyEvent.VK_BACK_SPACE) {
				backspacePressed = true;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				if(escPressed == true) {
					escPressed = false;
					stopAnimation = false;
					lever = false;
					
				}
				else {
					if(code == KeyEvent.VK_ESCAPE) {
						escPressed = true;
						stopAnimation = true;
						
					}
				}
			}
			if(code == KeyEvent.VK_PAGE_UP) {
				pageupPressed = true;
			}
			if(code == KeyEvent.VK_PAGE_DOWN) {
				pagedownPressed = true;
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_SHIFT) {
			shiftPressed = false;
		}
		if(code == KeyEvent.VK_BACK_SPACE) {
			backspacePressed = false;
		}
		if(code == KeyEvent.VK_ESCAPE) {
		}
		if(code == KeyEvent.VK_PAGE_UP) {
			pageupPressed = false;
		}
		if(code == KeyEvent.VK_PAGE_DOWN) {
			pagedownPressed = false;
		}
		
	}
}