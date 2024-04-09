package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.swing.JPanel;

import entity.EnemyNoLoot;
import entity.EnemyWithLoot;
import entity.Player;
import entity.RubinManager;
import entity.StoneManager;
import tile.TileManager;
import tile.Levelbord;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 30;
	public final int maxScreenRow = 17;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 40;
	public final int maxWorldRow = 23;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// FPS
	int FPS = 60;
	
	//Game States
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	
	//Standard World
	public int welt = 3;
	
	public Login login = new Login(this);
	public StoneManager stoneM = new StoneManager(this);
	public RubinManager rubinM = new RubinManager(this);
	public EnemyWithLoot enemyWL = new EnemyWithLoot(this);
	public Levelbord levelB = new Levelbord(this);
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public Thread gameThread;
	public Player player = new Player(this, keyH);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Interface ui = new Interface(this);
	public EnemyNoLoot enemyNL = new EnemyNoLoot(this);
	public Animation animation = new Animation(this);
	public Random random = new Random();            						//random stuff like random int.
	
	public int threadRunTime = 0;
	public int time = 0;
	public boolean animationPause = false;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
		//setDefault GameState
		gameState = titleState;
		
		playSE("startup");
		playMusic("blue-boy-adventure");
	}

	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
		
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				
				if(animationPause == false) {
					threadRunTime++;
				}
				time = Math.round(threadRunTime/FPS);
				
				delta--;
			}
		}
	}
	
	
	public void update() {
		
		switch(gameState) {
			case(titleState):
				login.update();
				break;
			
			case(playState):
				if(!animationPause) {
					setVolume();
					player.update();
					tileM.update();
					stoneM.update();
					rubinM.update();
					levelB.update();
					enemyNL.update();
					enemyWL.update();
				}
				else {
					animation.update();
				}
				break;
			
			case(pauseState):
				
				break;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		switch(gameState) {
			case(titleState):
				login.draw(g2);
				break;
			
			case(playState):
				
				tileM.draw(g2);
				stoneM.draw(g2);
				rubinM.draw(g2);
				
				if(!animationPause) {
					player.draw(g2);
				}
				
				enemyNL.draw(g2);
				enemyWL.draw(g2);
				levelB.draw(g2);
				ui.draw(g2);
				animation.draw(g2);
				break;
				
			case(pauseState):
				
				break;
			
		}
		
		g2.dispose();
	}
	
	public void playMusic(String name) {
		Sound sound = SoundHandler.getSound(name);
		sound.setVolume(0.5f);
		sound.loop();
		SoundHandler.playSoundDelayed(sound, Duration.of(5, ChronoUnit.SECONDS));
	}
	public void stopMusic(String name) {
		Sound sound = SoundHandler.getSound(name);
		sound.stop();
	}
	public void playSE(String name){
		Sound sound = SoundHandler.getSound(name);
		sound.setVolume(0.5f);
		if(name.equals("win-nt-shutdown")) {
			sound.setVolume(0.3f);
		}
		sound.play();
	}
	
	public void setVolume() {
		if(keyH.pageupPressed == true) {
			keyH.pageupPressed = false;
			if(SoundHandler.soundEffectVolume < 1f) {
				SoundHandler.soundEffectVolume += 0.05f;
			}
			else {
				System.out.println("Maximale Lautstärke erreicht!");
			}
		}
		if(keyH.pagedownPressed == true) {
			keyH.pagedownPressed = false;
			if(SoundHandler.soundEffectVolume > -1f) {
				SoundHandler.soundEffectVolume -= 0.05f;
			}
			else {
				System.out.println("Minimale Lautstärke erreicht!");
			}
		}
		SoundHandler.updateVolume();
	}
	
	public int getRandomNum() {
		int randomNum = random.nextInt(50);
		return randomNum;
	}
}