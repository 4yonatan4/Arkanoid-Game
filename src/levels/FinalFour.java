package levels;

import collidable.Block;
import geometric.FillCircle;
import geometric.Line;
import geometric.Point;
import geometric.Rectangle;
import sprite.Background;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 3;
    private static final double SPEED = 6;
    private static final double UPANGLE = 0;
    private static final int PADDLE_SPEED = 9;
    private static final int PADDLE_WIDTH = 120;
    private static final String LEVEL_NAME = "Final Four";
    private static final double WIDTH_BLOCK = 50;
    private static final double HEIGHT_BLOCK = 25;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 105;
    private static final int NUMBER_OF_LIVES = 7;


    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        double x = -30;
        for (int i = 0; i < 3; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(UPANGLE + x, SPEED);
            velocityList.add(velocity);
            x += 30;
        }
        return velocityList;
    }

    @Override
    public List<Color> ballColor() {
        List<Color> colorList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Color color = java.awt.Color.white;
            colorList.add(color);
        }
        return colorList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(0x29AEDA));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();
        colorList.add(Color.GRAY);
        colorList.add(Color.RED);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        colorList.add(Color.WHITE);
        colorList.add(Color.PINK);
        colorList.add(Color.CYAN);
        int x = 25, y = 100;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                Point p1 = new Point(x, y);
                Rectangle rec1 = new geometric.Rectangle(p1, WIDTH_BLOCK, HEIGHT_BLOCK);
                rec1.setColor(colorList.get(j));
                Block b1 = new Block(rec1);
                blockList.add(b1);
                x = x + 50;
            }
            x = 25;
            y += (int) HEIGHT_BLOCK;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }

    @Override
    public int numOfLives() {
        return NUMBER_OF_LIVES;
    }

    @Override
    public List<Sprite> drawings() {
        List<Sprite> spriteList = new ArrayList<>();
        // draw Clouds
        int x = 0, y = 0, a = 90, b = 380;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 15; j++) {
                Line l1 = new Line(new Point(a, b), new Point(a - 25, HEIGHT), Color.white);
                spriteList.add(l1);
                a += 7;
            }
            FillCircle c1 = new FillCircle(95 + x, 380 + y, 20, new Color(0xE5E3E1));
            spriteList.add(c1);
            FillCircle c3 = new FillCircle(120 + x, 408 + y, 25, new Color(0xE5E3E1));
            spriteList.add(c3);
            FillCircle c2 = new FillCircle(130 + x, 370 + y, 25, Color.lightGray);
            spriteList.add(c2);
            FillCircle c4 = new FillCircle(150 + x, 405 + y, 20, Color.gray.brighter());
            spriteList.add(c4);
            FillCircle c5 = new FillCircle(170 + x, 380 + y, 30, Color.gray.brighter());
            spriteList.add(c5);
            x += 480;
            y += 50;
            a = 570;
            b += 50;
        }
        return spriteList;
    }
}
