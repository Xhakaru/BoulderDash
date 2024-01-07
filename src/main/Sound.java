package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public float volume = 0;
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/WindowsXPstartup.wav");
		soundURL[1] = getClass().getResource("/sound/Nyan-Cat.wav");
		soundURL[2] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[3] = getClass().getResource("/sound/WindowsNT5.0.wav");
		soundURL[4] = getClass().getResource("/sound/WindowsNTStartup.wav");
		soundURL[5] = getClass().getResource("/sound/WindowsNTShuttdown.wav");
		soundURL[6] = getClass().getResource("/sound/Eat.wav");
	}
	
	public void setFile(int i){
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			System.out.println("Audiofile konnte nicht geladen werden!");
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void setValue(float x) {
	    if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(x + volume);
	    } else {
	        System.out.println("Lautstärkeänderung wird nicht unterstützt.");
	    }
	}
}