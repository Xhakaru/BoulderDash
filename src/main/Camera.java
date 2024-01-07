package main;

import java.awt.*;

public class Camera {
    public int worldX = 0;
    public int worldY = 0;

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

    public void update() {
        worldRows = gp.maxWorldRow;
        worldColumns = gp.maxWorldCol;

        int playerColumn = gp.player.worldX / gp.tileSize;
        int playerRow = gp.player.worldY / -gp.tileSize * -1;

        if (playerColumn < tileMarginX + worldColumn) {
            if (worldColumn == 0) {
                worldX = gp.tileSize;
            } else {
                worldX += gp.tileSize;
                worldColumn--;
            }
        } else if (playerColumn > worldColumn + tilesPerRow - tileMarginX -1) {
            if (worldColumn == worldColumns - tilesPerRow) {
                worldColumn = worldColumns - tilesPerRow;
            } else {
                worldX -= gp.tileSize;
                worldColumn++;
            }
        }

        if (playerRow < tileMarginY + worldRow) {
            if (worldRow == 0) {
                worldY = gp.tileSize;
            } else {
                worldY += gp.tileSize;
                worldRow--;
            }
        } else if (playerRow > worldRow + tilesPerColumn - tileMarginY -1) {
            if (worldRow >= worldRows - tilesPerColumn) {
                worldRow = worldRows - tilesPerColumn;
            } else {
                worldY -= gp.tileSize;
                worldRow++;
            }
        }
    }

    public void drawCameraBox(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        g2d.drawRect(tileMarginX*gp.tileSize, (tileMarginY+1)*gp.tileSize, (tilesPerRow-tileMarginX*2)*gp.tileSize, (tilesPerColumn-tileMarginY*2)*gp.tileSize);
    }
}
