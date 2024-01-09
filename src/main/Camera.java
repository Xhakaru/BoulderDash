package main;

import java.awt.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Camera {
    private static final Transition.Function CAMERA_SMOOTHING_FUNCTION = Transition.SINUS;
    private static final Duration CAMERA_SMOOTHING_DURATION = Duration.of(500, ChronoUnit.MILLIS);

    public int worldX = 0;
    public int worldY = 0;

    public int lastWorldX = 0;
    public int lastWorldY = 0;

    public int targetX = 0;
    public int targetY = 0;

    public int oldTargetX = 0;
    public int oldTargetY = 0;

    Transition transitionX;
    Transition transitionY;

    public int worldRow = 0;
    public int worldColumn = 0;

    public int tilesPerRow;
    public int tilesPerColumn;

    public int worldRows;
    public int worldColumns;

    public final int tileMarginX = 10;
    public final int tileMarginY = 5;

    private GamePanel gp;

    public Camera(GamePanel gp) {
        this.gp = gp;

        tilesPerRow = gp.screenWidth / gp.tileSize;
        tilesPerColumn = gp.screenHeight / gp.tileSize - 1;
    }

    public void centerPlayer(){
        int playerColumn = gp.player.worldX / gp.tileSize;
        int playerRow = gp.player.worldY / -gp.tileSize * -1;

        worldColumn = Math.min(gp.currentMap.getCurrentLevel().getLevelSize().getWidth() - tilesPerRow, Math.max(playerColumn - tilesPerRow/2, 0));
        worldRow = Math.min(gp.currentMap.getCurrentLevel().getLevelSize().getHeight() - tilesPerColumn, Math.max(playerRow - tilesPerColumn/2, 0));

        transitionXTo(-worldColumn * gp.tileSize + gp.tileSize);
        transitionYTo(-worldRow * gp.tileSize + gp.tileSize);
    }

    public void update() {
        worldRows = gp.currentMap.getCurrentLevel().getLevelSize().getHeight();
        worldColumns = gp.currentMap.getCurrentLevel().getLevelSize().getWidth();

        int playerColumn = gp.player.worldX / gp.tileSize;
        int playerRow = gp.player.worldY / -gp.tileSize * -1;

        if (playerColumn < tileMarginX + worldColumn) {
            if (worldColumn > 0) {
                worldColumn--;
            }
            transitionXTo(-worldColumn * gp.tileSize + gp.tileSize);
        } else if (playerColumn > worldColumn + tilesPerRow - tileMarginX -1) {
            if (worldColumn == worldColumns - tilesPerRow) {
                worldColumn = worldColumns - tilesPerRow;
            } else {
                worldColumn++;
            }
            transitionXTo(-worldColumn * gp.tileSize + gp.tileSize);
        }

        if (playerRow < tileMarginY + worldRow) {
            if (worldRow > 0) {
                worldRow--;
            }
            transitionYTo(-worldRow * gp.tileSize + gp.tileSize);
        } else if (playerRow > worldRow + tilesPerColumn - tileMarginY - 1) {
            if (worldRow >= worldRows - tilesPerColumn) {
                worldRow = worldRows - tilesPerColumn;
            } else {
                worldRow++;
            }
            transitionYTo(-worldRow * gp.tileSize + gp.tileSize);
        }
    }

    private void transitionXTo(int newX) {
        targetX = newX;
        if(targetX == oldTargetX) return;
        if (transitionX != null){
            transitionX.kill();
        }
        oldTargetX = targetX;
        lastWorldX = worldX;
        transitionX = new Transition(CAMERA_SMOOTHING_DURATION, CAMERA_SMOOTHING_FUNCTION, t -> worldX = (int) Sound.map(t, 0f, 1f, lastWorldX, newX));
    }

    private void transitionYTo(int newY) {
        targetY = newY;
        if(targetY == oldTargetY) return;
        if (transitionY != null){
            transitionY.kill();
        }
        oldTargetY = targetY;
        lastWorldY = worldY;
        transitionY = new Transition(CAMERA_SMOOTHING_DURATION, CAMERA_SMOOTHING_FUNCTION, t -> worldY = (int) Sound.map(t, 0f, 1f, lastWorldY, newY));
    }

    public void drawCameraBox(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.drawRect(tileMarginX * gp.tileSize, (tileMarginY + 1) * gp.tileSize, (tilesPerRow - tileMarginX * 2) * gp.tileSize, (tilesPerColumn - tileMarginY * 2) * gp.tileSize);
    }
}
