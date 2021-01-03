package levels;

import collidable.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * The Color of the balls.
     *
     * @return the list
     */
    List<Color> ballColor();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen..
     *
     * @return the string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    int numberOfBlocksToRemove();

    /**
     * Num of lives int.
     *
     * @return the int
     */
    int numOfLives();

    /**
     * Drawings.
     *
     * @return the List<Sprite>
     */
    List<Sprite> drawings();
}