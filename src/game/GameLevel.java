package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.Block;
import collidable.Collidable;
import geometric.Point;
import geometric.Rectangle;
import levels.LevelInformation;
import listener.BallRemover;
import listener.BlockRemover;
import listener.ScoreTrackingListener;
import sprite.Ball;
import sprite.Counter;
import sprite.LevelName;
import sprite.LivesIndicator;
import sprite.Paddle;
import sprite.ScoreIndicator;
import sprite.Sprite;
import sprite.SpriteCollection;

import java.awt.Color;
import java.util.List;


/**
 * The class game.GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private Counter numOfLives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    private AnimationRunner runner;
    private LevelInformation levelInformation;
    private boolean running;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int WIDTH_BLOCK = 45;
    private static final int HEIGHT_BLOCK = 18;
    private static final int SPEED = 6;
    private static final int COUNT_FROM = 3;
    private static final int BALL_SIZE = 8;
    private static final int NUM_OF_SECONDS = 2000;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     * @param ks               KeyboardSensor
     * @param ar               AnimationRunner
     * @param numOfLives       numOfLives
     * @param score            score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks,
                     AnimationRunner ar, Counter numOfLives, Counter score) {
        this.levelInformation = levelInformation;
        this.keyboard = ks;
        this.runner = ar;
        this.numOfLives = numOfLives;
        this.counterScore = score;
    }

    /**
     * Add collidable.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * Build the limits of the game.
     */
    private void buildLimits() {
        Point p1 = new Point(0, 25);
        Point p2 = new Point(0, 50);
        Point p3 = new Point(WIDTH - 25, 50);
        Point p4 = new Point(0, HEIGHT + 5);
        Rectangle rec1 = new Rectangle(p1, WIDTH, 25);
        Rectangle rec2 = new Rectangle(p2, 25, HEIGHT - 50);
        Rectangle rec3 = new Rectangle(p3, 25, HEIGHT - 50);
        Rectangle rec4 = new Rectangle(p4, WIDTH, 30);
        rec1.setColor(Color.GRAY);
        rec2.setColor(Color.GRAY);
        rec3.setColor(Color.GRAY);
        rec4.setColor(Color.GRAY);
        Block upLimit = new Block(rec1);
        Block leftLimit = new Block(rec2);
        Block rightLimit = new Block(rec3);
        Block deathRegion = new Block(rec4);
        deathRegion.addHitListener(ballRemover);
        upLimit.addToGame(this);
        leftLimit.addToGame(this);
        rightLimit.addToGame(this);
        deathRegion.addToGame(this);
    }

    /**
     * Build the blocks of the game.
     */
    private void buildBlocks() {
        List<Block> blockList = this.levelInformation.blocks();
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Initialize a new game.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.counterBlocks = new Counter();
        this.counterBalls = new Counter();
        this.blockRemover = new BlockRemover(this, counterBlocks);
        this.ballRemover = new BallRemover(this, counterBalls);
        addSprite(levelInformation.getBackground());
        this.scoreIndicator = new ScoreIndicator(this);
        this.scoreTrackingListener = new ScoreTrackingListener(counterScore);
        this.livesIndicator = new LivesIndicator(this);
        LevelName levelName = new LevelName(this, levelInformation.levelName());
        this.buildBlocks();
//        for (Sprite s : levelInformation.drawings()) {
//            addSprite(s);
//        }
        // build the limits of the game
        this.buildLimits();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    this.keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
        if (counterBlocks.getValue() >= levelInformation.numberOfBlocksToRemove()
                || counterBalls.getValue() <= 0) {
            if (counterBalls.getValue() <= 0) {
                this.getNumOfLives().decrease(1);
            }
            this.running = false;
        }

    }

    /**
     * Run the game - start the animation loop.
     */
    public void playOneTurn() {
        Paddle paddle = new Paddle(WIDTH, HEIGHT,
                levelInformation.paddleSpeed(), levelInformation.paddleWidth());
        paddle.addToGame(this);
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        paddle.removeFromGame(this);
    }

    /**
     * create new balls on the game.
     */
    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(WIDTH / 2, HEIGHT - 30, BALL_SIZE,
                    levelInformation.ballColor().get(i));
            ball.addToGame(this);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounterBlocks() {
        return this.counterBlocks;
    }

    /**
     * Gets counter balls.
     *
     * @return the counter balls
     */
    public Counter getCounterBalls() {
        return this.counterBalls;
    }

    /**
     * Gets counter score.
     *
     * @return the counter score
     */
    public Counter getCounterScore() {
        return this.counterScore;
    }

    /**
     * Gets num of lives.
     *
     * @return the num of lives
     */
    public Counter getNumOfLives() {
        return this.numOfLives;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return HEIGHT;
    }
}