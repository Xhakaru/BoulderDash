package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {
    private GamePanel gp;
    private String mapPath;
    private String name;
    private List<Level> levels = new ArrayList<>();
    private Level currentLevel;
    private boolean preloaded = false;

    public Map(GamePanel gp, String mapPath) {
        this.gp = gp;
        this.mapPath = mapPath;
    }

    public void preload(){
        if(preloaded) return;
        loadFromFile();
        preloaded = true;
    }

    public void load(){
        preload();
    }

    private void loadFromFile() {
        String[] levelPaths = new String[0];

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(mapPath))))){
            for(String line : br.lines().toList()){
                String[] lineSplitted = line.split("=");
                String varName = lineSplitted[0];
                String varValue = lineSplitted[1];

                switch (varName){
                    case "name":
                        name = varValue;
                        break;
                    case "levels":
                        levelPaths = varValue.split(";");
                        break;
                }
            }
        }
        catch(Exception e){

        }

        preloadLevels(levelPaths);
    }

    private void preloadLevels(String[] levelPaths) {
        for(String levelPath : levelPaths){
            levels.add(new Level("/maps/levels/" + levelPath + ".lvl"));
        }
        currentLevel = levels.get(0);
    }

    public boolean nextLevel(){
        int currentIndex = levels.indexOf(currentLevel);
        if(currentIndex == levels.size()-1) return false;
        currentLevel = levels.get(currentIndex + 1);
        return true;
    }

    public void resetMap(){
        for(Level level : levels){
            level.reset();
        }
        currentLevel = levels.get(0);
        gp.camera.centerPlayer();
    }

    public String getName() {
        return name;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
