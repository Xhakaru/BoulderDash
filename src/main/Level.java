package main;

import tile.TileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {
    private String levelPath;
    private Point2D levelSpawn;
    private Dimension2D levelSize = new Dimension2D();
    private int[][] tileIds;
    private Point2D finishPoint = new Point2D();
    private List<Point2D> enemySpawnPoints = new ArrayList<>();
    private int finishRubies = 0;
    private int checkPointRubies = 0;

    public Level(String levelPath) {
        this.levelPath = levelPath;
        load();
    }

    private void load() {
        boolean levelDataStarted = false;
        int row = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(levelPath))))) {
            for (String line : br.lines().toList()) {
                if (line.equals(";")) {
                    levelDataStarted = true;
                    tileIds = new int[levelSize.getWidth()][levelSize.getHeight()];
                    continue;
                }
                if (!levelDataStarted) {
                    String[] lineSplitted = line.split("=");
                    String varName = lineSplitted[0];
                    String varValue = lineSplitted[1];

                    switch (varName) {
                        case "rubies_for_finish":
                            finishRubies = Integer.parseInt(varValue);
                            break;
                        case "rows":
                            levelSize.setHeight(Integer.parseInt(varValue));
                            break;
                        case "columns":
                            levelSize.setWidth(Integer.parseInt(varValue));
                            break;
                    }
                } else {
                    int col = 0;
                    for(String idStr : line.split(" ")){
                        int id = Integer.parseInt(idStr);
                        switch (id){
                            case TileManager.FINISH_SPAWNER_ID:
                                finishPoint = new Point2D(col, row);
                                id = 0;
                                break;
                            case TileManager.PLAYER_SPAWNER_ID:
                                levelSpawn = new Point2D(col, row);
                                break;
                            case TileManager.ENEMY_SPAWNER_ID:
                                enemySpawnPoints.add(new Point2D(col, row));
                                break;
                        }
                        tileIds[col][row] = id;
                        col++;
                    }
                    row++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Point2D getLevelSpawn() {
        return levelSpawn;
    }

    public Dimension2D getLevelSize() {
        return levelSize;
    }

    public int[][] getTileIds() {
        return tileIds;
    }

    public Point2D getFinishPoint() {
        return finishPoint;
    }

    public List<Point2D> getEnemySpawnPoints() {
        return enemySpawnPoints;
    }

    public int getFinishRubies() {
        return finishRubies;
    }

    public int getCheckPointRubies() {
        return checkPointRubies;
    }

    public void reset() {
        load();
    }
}
