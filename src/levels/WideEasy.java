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
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 10;
    private static final double SPEED = 6;
    private static final double UPANGLE = 0;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 600;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final double WIDTH_BLOCK = 50;
    private static final double HEIGHT_BLOCK = 25;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 15;
    private static final int NUMBER_OF_LIVES = 7;


    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        int x = 10;
        for (int i = 0; i < 5; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(UPANGLE + x, SPEED);
            velocityList.add(velocity);
            x = x + 10;
        }
        x = -10;
        for (int i = 0; i < 5; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(UPANGLE + x, SPEED);
            velocityList.add(velocity);
            x = x - 10;
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
        return new Background(Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();
        colorList.add(Color.RED);
        colorList.add(Color.RED);
        colorList.add(Color.ORANGE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        colorList.add(Color.GREEN);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.BLUE);
        colorList.add(Color.PINK);
        colorList.add(Color.PINK);
        colorList.add(Color.cyan);
        colorList.add(Color.cyan);
        int x = 25;
        for (int i = 0; i < 15; i++) {
            Point p1 = new Point(x, 260);
            Rectangle rec1 = new geometric.Rectangle(p1, WIDTH_BLOCK, HEIGHT_BLOCK);
            rec1.setColor(colorList.get(i));
            Block b1 = new Block(rec1);
            blockList.add(b1);
            x = x + 50;
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
        int y = 260;
        for (int i = 0; i < 5; i++) {
            Line l1 = new Line(new Point(0, y), new Point(150, 150), new Color(246, 238, 179));
            spriteList.add(l1);
            y += 5;
        }
        int x = 0;
        for (int i = 0; i < 140; i++) {
            Line l1 = new Line(new Point(x, 259), new Point(150, 150), new Color(246, 238, 179));
            spriteList.add(l1);
            x += 5;
        }
        FillCircle c3 = new FillCircle(150, 150, 60, new Color(246, 238, 179));
        spriteList.add(c3);
        FillCircle c2 = new FillCircle(150, 150, 50, new Color(238, 221, 18));
        spriteList.add(c2);
        FillCircle c1 = new FillCircle(150, 150, 40, new Color(255, 238, 18));
        spriteList.add(c1);


        return spriteList;
    }

}
