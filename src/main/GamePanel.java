package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
import tile.Levelbord;

public class GamePanel extends JPanel implements Runnable{
	private Stack<Runnable> nextFrameSchedule = new Stack<>();
	public static final boolean cameraDebug = false;

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 30;
	public final int maxScreenRow = 17;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public List<Map> maps = new ArrayList<>();
	public Map currentMap;

	private static final int STARTING_MAP_ID = 0;
	
	// FPS
	int FPS = 60;
	
	public TileManager tileM;
	public Camera camera;
	public KeyHandler keyH = new KeyHandler();
	public Thread gameThread;
	public Player player;
	public CollisionChecker cChecker;
	public Interface ui;
	public Levelbord levelB;
	public Animation animation;
	
	public int threadRunTime = 0;
	public int time = 0;
	public boolean animationPause = false;
	
	public GamePanel() {
		loadMaps();

		init();
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	private void loadMaps() {
		maps.add(new Map(this, "/maps/map01.map"));
		maps.add(new Map(this, "/maps/map02.map"));
		maps.add(new Map(this, "/maps/map03.map"));
		maps.add(new Map(this, "/maps/map04.map"));

		for(Map map : maps){
			map.preload();
		}

		currentMap = maps.get(STARTING_MAP_ID);
	}

	private void init() {
		tileM = new TileManager(this);
		camera = new Camera(this);
		player = new Player(this, keyH);
		cChecker = new CollisionChecker(this);
		ui = new Interface();
		levelB = new Levelbord(this);
		animation = new Animation(this);

		camera.centerPlayer();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
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
				threadRunTime++;
				time = Math.round(threadRunTime/FPS);
				delta--;
			}
		}
	}
	
	
	public void update() {
		
		if(!animationPause) {
			player.update();
			tileM.update();
			levelB.update();
		}
		else {
			animation.update();
		}
		setVolume();
		camera.update();
	}
	
	public void paintComponent(Graphics g) {
		if(!nextFrameSchedule.empty()) nextFrameSchedule.pop().run();

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);

		if(!animationPause) {
			player.draw(g2);
		}

		levelB.draw(g2);

		ui.draw(g2);
		
		if(animationPause) {
			animation.draw(g2);
		}

		if(cameraDebug){
			camera.drawCameraBox(g2);
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

	public void resetMap() {
		currentMap.resetMap();
	}

	public void scheduleForNextFrame(Runnable runnable) {
		nextFrameSchedule.add(runnable);
	}
}