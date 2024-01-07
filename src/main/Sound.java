package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

	private Clip clip;
	private URL soundURL;

	private boolean looping = false;

	private float volume = 1;

	public Sound(URL resource) {
		soundURL = resource;
	}

	public void play() {
		load();
		setVolume();
		if (looping) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			return;
		}
		clip.start();
	}

	private void setVolume() {
		if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float max = gainControl.getMaximum();
			float min = gainControl.getMinimum();
			float baseVolume = map(this.volume, 0f, 1f, min, max);
			float volume = map(SoundHandler.soundEffectVolume, -1, 1, min, baseVolume);
			volume = clamp(volume, min, max);
			gainControl.setValue(volume);
		} else {
			System.out.println("Lautstärkeänderung wird nicht unterstützt.");
		}
	}

	private float clamp(float value, float min, float max) {
		if (value > max) return max;
		if (value < min) return min;
		return value;
	}

	private float map(float x, float in_min, float in_max, float out_min, float out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	private void load() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			System.out.println("Audiofile konnte nicht geladen werden!");
		}
	}

	public void loop() {
		looping = true;
	}

	public void stop() {
		if (clip != null) clip.stop();
	}

	public void setVolume(float x) {
		volume += x;
	}

	public void updateVolume() {
		if (clip != null) setVolume();
	}
}