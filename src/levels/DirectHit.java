package levels;

import collidable.Block;
import geometric.Circle;
import geometric.Line;
import geometric.Point;
import sprite.Background;
import sprite.Sprite;
import sprite.Velocity;
import geometric.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 1;
    private static final double SPEED = 6;
    private static final double UPANGLE = 0;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 100;
    private static final String LEVEL_NAME = "Direct Hit";
    private static final double WIDTH_BLOCK = 36;
    private static final double HEIGHT_BLOCK = 36;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 1;
    private static final int NUMBER_OF_LIVES = 7;


    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(UPANGLE, SPEED);
        velocityList.add(velocity);
        return velocityList;
    }

    @Override
    public List<Color> ballColor() {
        List<Color> colorList = new ArrayList<>();
        Color color = java.awt.Color.white;
        colorList.add(color);
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
        return new Background(Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Point p1 = new Point((WIDTH / 2) - 18, 140);
        Rectangle rec1 = new geometric.Rectangle(p1, WIDTH_BLOCK, HEIGHT_BLOCK);
        rec1.setColor(Color.RED);
        Block b1 = new Block(rec1);
        blockList.add(b1);
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
        Circle c1 = new Circle(400, 158, 103, Color.BLUE);
        Circle c2 = new Circle(400, 158, 73, Color.BLUE);
        Circle c3 = new Circle(400, 158, 43, Color.BLUE);
        // left line
        Point p1Start = new Point(375, 158);
        Point p1End = new Point(260, 158);
        Line l1 = new Line(p1Start, p1End, Color.BLUE);
        // right line
        Point p2Start = new Point(425, 158);
        Point p2End = new Point(540, 158);
        Line l2 = new Line(p2Start, p2End, Color.BLUE);
        // up line
        Point p3Start = new Point(400, 133);
        Point p3End = new Point(400, 28);
        Line l3 = new Line(p3Start, p3End, Color.BLUE);
        // down line
        Point p4Start = new Point(400, 183);
        Point p4End = new Point(400, 298);
        Line l4 = new Line(p4Start, p4End, Color.BLUE);
        spriteList.add(c1);
        spriteList.add(c2);
        spriteList.add(c3);
        spriteList.add(l1);
        spriteList.add(l2);
        spriteList.add(l3);
        spriteList.add(l4);
        return spriteList;
    }

}
