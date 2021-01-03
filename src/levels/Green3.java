//package levels;
//
//import collidable.Block;
//import geometric.FillCircle;
//import geometric.FillRectangle;
//import geometric.Point;
//import geometric.Rectangle;
//import sprite.Background;
//import sprite.Sprite;
//import sprite.Velocity;
//
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * The type Green 3.
// */
//public class Green3 implements LevelInformation {
//
//    private static final int NUMBER_OF_BALLS = 2;
//    private static final double SPEED = 6;
//    private static final double UPANGLE = 0;
//    private static final int PADDLE_SPEED = 7;
//    private static final int PADDLE_WIDTH = 100;
//    private static final String LEVEL_NAME = "Green 3";
//    private static final double WIDTH_BLOCK = 50;
//    private static final double HEIGHT_BLOCK = 25;
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 600;
//    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 40;
//    private static final int NUMBER_OF_LIVES = 7;
//
//
//    @Override
//    public int numberOfBalls() {
//        return NUMBER_OF_BALLS;
//    }
//
//    @Override
//    public List<Velocity> initialBallVelocities() {
//        List<Velocity> velocityList = new ArrayList<>();
//        int x = 330;
//        for (int i = 0; i < 2; i++) {
//            Velocity velocity = Velocity.fromAngleAndSpeed(UPANGLE + x, SPEED);
//            velocityList.add(velocity);
//            x = 30;
//        }
//        return velocityList;
//    }
//
//    @Override
//    public List<Color> ballColor() {
//        List<Color> colorList = new ArrayList<>();
//        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
//            Color color = java.awt.Color.white;
//            colorList.add(color);
//        }
//        return colorList;
//    }
//
//    @Override
//    public int paddleSpeed() {
//        return PADDLE_SPEED;
//    }
//
//    @Override
//    public int paddleWidth() {
//        return PADDLE_WIDTH;
//    }
//
//    @Override
//    public String levelName() {
//        return LEVEL_NAME;
//    }
//
//    @Override
//    public Sprite getBackground() {
//        return new Background(new Color(0x2EAF52));
//    }
//
//    @Override
//    public List<Block> blocks() {
//        List<Block> blockList = new ArrayList<>();
//        List<Color> colorList = new ArrayList<>();
//        colorList.add(Color.GRAY);
//        colorList.add(Color.RED);
//        colorList.add(Color.YELLOW);
//        colorList.add(Color.BLUE);
//        colorList.add(Color.WHITE);
//        int x = WIDTH - (int) WIDTH_BLOCK - 25;
//        int y = 150, blocks = 10, color = 0;
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < blocks; j++) {
//                Point p1 = new Point(x, y);
//                Rectangle rec1 = new geometric.Rectangle(p1, WIDTH_BLOCK, HEIGHT_BLOCK);
//                rec1.setColor(colorList.get(color));
//                Block b1 = new Block(rec1);
//                blockList.add(b1);
//                x -= (int) WIDTH_BLOCK;
//            }
//            y += (int) HEIGHT_BLOCK;
//            x = WIDTH - (int) WIDTH_BLOCK - 25;
//            blocks--;
//            color++;
//        }
//        return blockList;
//    }
//
//    @Override
//    public int numberOfBlocksToRemove() {
//        return NUMBER_OF_BLOCKS_TO_REMOVE;
//    }
//
//    @Override
//    public int numOfLives() {
//        return NUMBER_OF_LIVES;
//    }
//
//    @Override
//    public List<Sprite> drawings() {
//        List<Sprite> spriteList = new ArrayList<>();
//        FillRectangle rec1 = new FillRectangle(60, 420, 140, 180, new Color(0x1F1D18));
//        spriteList.add(rec1);
//        FillRectangle rec2 = new FillRectangle(110, 350, 40, 70, Color.gray.darker());
//        spriteList.add(rec2);
//        FillRectangle rec3 = new FillRectangle(122, 180, 16, 170, Color.gray.darker());
//        spriteList.add(rec3);
//        double x = 72, y = 430;
//        for (int j = 0; j < 4; j++) {
//            for (int i = 0; i < 4; i++) {
//                FillRectangle window1 = new FillRectangle(x, y, 20, 40, Color.WHITE);
//                spriteList.add(window1);
//                x += 32;
//            }
//            x = 72;
//            y += 52;
//        }
//        FillCircle c1 = new FillCircle(130, 175, 16, Color.pink);
//        spriteList.add(c1);
//        FillCircle c2 = new FillCircle(130, 175, 13, Color.red);
//        spriteList.add(c2);
//        FillCircle c3 = new FillCircle(130, 175, 4, Color.white);
//        spriteList.add(c3);
//        return spriteList;
//    }
//
//}
