package tile;

import main.GamePanel;
import struct.Direction;
import struct.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    BufferedImage rubin_f1, rubin_f2, rubin_f3, rubin_f4, rubin_f5, rubin_f6, rubin_f7, rubin_f8;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {

        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray-v1.png"));
            tiles[0].eat = true;

            tiles[1] = new Tile();
            rubin_f1 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f1.png"));
            rubin_f2 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f2.png"));
            rubin_f3 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f3.png"));
            rubin_f4 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f4.png"));
            rubin_f5 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f5.png"));
            rubin_f6 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f6.png"));
            rubin_f7 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f7.png"));
            rubin_f8 = ImageIO.read(getClass().getResourceAsStream("/tiles/rubin_f8.png"));
            tiles[1].eat = true;
            tiles[1].item = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_gray.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick_gray.png"));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone_dark_gray.png"));
            tiles[4].stone = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt_gray_dark.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String map) {
        try {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

                String line = br.readLine();

                while (col < gamePanel.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void eaten(Vector2D position) {
        int tileCol = position.x / gamePanel.tileSize;
        int tileRow = position.y / gamePanel.tileSize;
        mapTileNum[tileCol][tileRow] = 5;
//		gamePanel.playSE(6);   					//Herr Wedel, Sie können das auch entkommentieren, aber auf eigene Gefahr
    }

    public void stonePush(Vector2D position, Direction direction) {
        int x = position.x + direction.getDirectionVector().x*48;
        int y = position.y + direction.getDirectionVector().y*-48;

        int tileCol = 0;
        int tileRow = 0;

        tileCol = x / gamePanel.tileSize;
        tileRow = y / gamePanel.tileSize;
        mapTileNum[tileCol][tileRow] = 4;

        tileCol = position.x / gamePanel.tileSize;
        tileRow = position.y / gamePanel.tileSize;
        mapTileNum[tileCol][tileRow] = 5;
    }

    public void stoneFall(int col, int row) {
        if (mapTileNum[col][row] == 4) {
            if (mapTileNum[col][row + 1] == 5) {
                mapTileNum[col][row] = 5;
                mapTileNum[col][row + 1] = 4;
                gamePanel.playSE(5);
            }
            if (mapTileNum[col][row] == 4) {                                                            //guckt ob der Spieler im Weg ist
                if (col == gamePanel.player.worldX / gamePanel.tileSize && row == gamePanel.player.worldY / gamePanel.tileSize) {
                    //Spieler wurde getroffen (Sound-Effect und Leben abziehen/Game Over)
                }
            }
        }
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > 6) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 6;
            } else if (spriteNum == 6) {
                spriteNum = 7;
            } else if (spriteNum == 7) {
                spriteNum = 8;
            } else if (spriteNum == 8) {
                spriteNum = 9;
            } else if (spriteNum == 9) {
                spriteNum = 10;
            } else if (spriteNum == 10) {
                spriteNum = 11;
            } else if (spriteNum == 11) {
                spriteNum = 12;
            } else if (spriteNum == 12) {
                spriteNum = 13;
            } else if (spriteNum == 13) {
                spriteNum = 14;
            } else if (spriteNum == 14) {
                spriteNum = 15;
            } else if (spriteNum == 15) {
                spriteNum = 16;
            } else if (spriteNum == 16) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void rubinSprite() {
        if (spriteNum == 1) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 2) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 3) {
            tiles[1].image = rubin_f2;
        }
        if (spriteNum == 4) {
            tiles[1].image = rubin_f3;
        }
        if (spriteNum == 5) {
            tiles[1].image = rubin_f4;
        }
        if (spriteNum == 6) {
            tiles[1].image = rubin_f5;
        }
        if (spriteNum == 7) {
            tiles[1].image = rubin_f6;
        }
        if (spriteNum == 8) {
            tiles[1].image = rubin_f7;
        }
        if (spriteNum == 9) {
            tiles[1].image = rubin_f8;
        }
        if (spriteNum == 10) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 11) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 12) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 13) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 14) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 15) {
            tiles[1].image = rubin_f1;
        }
        if (spriteNum == 16) {
            tiles[1].image = rubin_f1;
        }
    }


    //Fixierte Kamera, wird bearbeitet
    public void draw(Graphics2D g2) {

        rubinSprite();

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            stoneFall(worldCol, worldRow);

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}