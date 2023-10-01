package entity;

import main.GamePanel;
import main.KeyHandler;
import struct.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    private int rubinCounter = 0;
    int varX = 0;
    int varY = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel, keyHandler);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;

        // todo: replace with camera
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        setDefaultValues();
        loadPlayerImages();
    }

    public void setDefaultValues() {
        position.x = gamePanel.tileSize * 8;
        position.y = gamePanel.tileSize * 6;
        speed = gamePanel.tileSize;
        direction = Direction.DOWN;
        xDirection = Direction.RIGHT;
        yDirection = Direction.DOWN;
    }

    public void loadPlayerImages() {

        try {

            player_gray_u1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_u1.png"));
            player_gray_u2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_u2.png"));
            player_gray_o1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_o1.png"));
            player_gray_o2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_o2.png"));
            player_gray_r1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_r1.png"));
            player_gray_r2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_r2.png"));
            player_gray_l1 = ImageIO.read(getClass().getResource("/player_gray/player_gray_l1.png"));
            player_gray_l2 = ImageIO.read(getClass().getResource("/player_gray/player_gray_l2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        super.update();

        if (keyHandler.upPressed ||
                keyHandler.downPressed ||
                keyHandler.leftPressed ||
                keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                keyHandler.upPressed = false;
                direction = Direction.UP;
                yDirection = Direction.UP;
            }
            if (keyHandler.downPressed) {
                keyHandler.downPressed = false;
                direction = Direction.DOWN;
                yDirection = Direction.DOWN;
            }
            if (keyHandler.leftPressed) {
                keyHandler.leftPressed = false;
                direction = Direction.LEFT;
                xDirection = Direction.LEFT;
            }
            if (keyHandler.rightPressed) {
                keyHandler.rightPressed = false;
                direction = Direction.RIGHT;
                xDirection = Direction.RIGHT;
            }

            if (!collisionOn) {
                switch (direction) {
                    case UP:
                        position.y -= speed;
                        break;
                    case DOWN:
                        position.y += speed;
                        break;
                    case LEFT:
                        position.x -= speed;
                        break;
                    case RIGHT:
                        position.x += speed;
                        break;
                }
            }

            if (eat) {
                gamePanel.tileManager.eaten(position);
            }

            if (item) {
                rubinCounter++;
                System.out.println("Rubine gesammelt: " + rubinCounter);
            }

            if (stone) {
                switch (direction) {
                    case UP:
                        varX = 0;
                        varY = -48;
                        gamePanel.tileManager.stonePush(worldX, worldY, varX, varY);
                        System.out.println("upEaten");
                        break;
                    case DOWN:
                        varX = 0;
                        varY = 48;
                        gamePanel.tileManager.stonePush(worldX, worldY, varX, varY);
                        System.out.println("downEaten");
                        break;
                    case LEFT:
                        varX = -48;
                        varY = 0;
                        gamePanel.tileManager.stonePush(worldX, worldY, varX, varY);
                        System.out.println("leftEaten");
                        break;
                    case RIGHT:
                        varX = 48;
                        varY = 0;
                        gamePanel.tileManager.stonePush(worldX, worldY, varX, varY);
                        System.out.println("rightEaten");
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 16) {
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
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteCounter++;
            if (spriteCounter > 16) {
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
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (Ydirection) {
            case "up":
                switch (Xdirection) {
                    case "right":
                        if (spriteNum == 1) {
                            image = player_gray_r1;
                        }
                        if (spriteNum == 2) {
                            image = player_gray_r2;
                        }
                        if (spriteNum == 3) {
                            image = player_gray_r1;
                        }
                        if (spriteNum == 4) {
                            image = player_gray_r2;
                        }
                        if (spriteNum == 5) {
                            image = player_gray_r1;
                        }
                        if (spriteNum == 6) {
                            image = player_gray_r2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = player_gray_o1;
                        }
                        if (spriteNum == 2) {
                            image = player_gray_o2;
                        }
                        if (spriteNum == 3) {
                            image = player_gray_o1;
                        }
                        if (spriteNum == 4) {
                            image = player_gray_o2;
                        }
                        if (spriteNum == 5) {
                            image = player_gray_o1;
                        }
                        if (spriteNum == 6) {
                            image = player_gray_o2;
                        }
                        break;
                }
                break;
            case "down":
                switch (Xdirection) {
                    case "right":
                        if (spriteNum == 1) {
                            image = player_gray_u1;
                        }
                        if (spriteNum == 2) {
                            image = player_gray_u2;
                        }
                        if (spriteNum == 3) {
                            image = player_gray_u1;
                        }
                        if (spriteNum == 4) {
                            image = player_gray_u2;
                        }
                        if (spriteNum == 5) {
                            image = player_gray_u1;
                        }
                        if (spriteNum == 6) {
                            image = player_gray_u2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = player_gray_l1;
                        }
                        if (spriteNum == 2) {
                            image = player_gray_l2;
                        }
                        if (spriteNum == 3) {
                            image = player_gray_l1;
                        }
                        if (spriteNum == 4) {
                            image = player_gray_l2;
                        }
                        if (spriteNum == 5) {
                            image = player_gray_l1;
                        }
                        if (spriteNum == 6) {
                            image = player_gray_l2;
                        }
                        break;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}