package main;

import java.time.Duration;
import java.util.*;

public class SoundHandler {
    private static final Map<String, Sound> sounds = new HashMap<>();

    public static float soundEffectVolume = 0;

    public static void loadSounds(){
        sounds.put("startup", new Sound(SoundHandler.class.getResource("/sound/WindowsXPstartup.wav")));
        sounds.put("nyan-cat", new Sound(SoundHandler.class.getResource("/sound/Nyan-Cat.wav")));
        sounds.put("blue-boy-adventure", new Sound(SoundHandler.class.getResource("/sound/BlueBoyAdventure.wav")));
        sounds.put("win-nt", new Sound(SoundHandler.class.getResource("/sound/WindowsNT5.0.wav")));
        sounds.put("win-nt-startup", new Sound(SoundHandler.class.getResource("/sound/WindowsNTStartup.wav")));
        sounds.put("win-nt-shutdown", new Sound(SoundHandler.class.getResource("/sound/WindowsNTShuttdown.wav")));
        sounds.put("eat", new Sound(SoundHandler.class.getResource("/sound/Eat.wav")));
        sounds.put("stoneFall", new Sound(SoundHandler.class.getResource("/sound/StoneFall.wav")));
        sounds.put("FireAndSword", new Sound(SoundHandler.class.getResource("/sound/FireAndSword.wav")));
        sounds.put("FireAndSwordMain", new Sound(SoundHandler.class.getResource("/sound/FireAndSwordMain.wav")));
        sounds.put("MainOST", new Sound(SoundHandler.class.getResource("/sound/MainOST.wav")));
        sounds.put("Death", new Sound(SoundHandler.class.getResource("/sound/Death.wav")));
    }

    public static Sound getSound(String soundName){
        if(sounds.containsKey(soundName)) {
            return sounds.get(soundName);
        }
        System.out.println("Sound mit dem Namen " + soundName + " konnte nicht gefunden werden.");
        return null;
    }

    public static void updateVolume(){
        for(Sound sound : sounds.values()){
            sound.updateVolume();
        }
    }

    public static void playSoundDelayed(Sound sound, Duration delay){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                sound.play();
            }
        }, delay.toMillis());
    }
}
