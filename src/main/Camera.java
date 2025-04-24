package main;

import entity.Player;

public class Camera {
    private int x, y; // Position der Kamera
    private final int worldWidth, worldHeight;
    private final int screenWidth, screenHeight;

    public Camera(int worldWidth, int worldHeight, int screenWidth, int screenHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = 0;
        this.y = 0;
    }

    // Update der Kamera-Position, damit sie dem Spieler folgt
    public void update(Player player) {
        int playerX = player.getX() - screenWidth / 2;
        int playerY = player.getY() - screenHeight / 2;

        // Verhindere, dass die Kamera über die Welt hinausgeht
        x = Math.max(0, Math.min(playerX, worldWidth - screenWidth));
        y = Math.max(0, Math.min(playerY, worldHeight - screenHeight));
    }

    // Getter für die Kameraposition
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
