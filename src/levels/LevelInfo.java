package levels;

import collidable.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level info.
 */
public class LevelInfo implements LevelInformation {

    private String levelName;
    private List<Velocity> ballVelocities;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private double blocksStartX;
    private double blocksStartY;
    private double rowHeight;
    private int numOfBlocks;
    private InputStream blocksFile;
    private List<Block> blockList;
    private static final int NUM_OF_LIVES = 3;

    /**
     * Gets blocks file.
     *
     * @return the blocks file
     */
    public InputStream getBlocksFile() {
        return blocksFile;
    }

    /**
     * Sets blocks file.
     *
     * @param newBlocksFile the blocks file
     */
    public void setBlocksFile(InputStream newBlocksFile) {
        this.blocksFile = newBlocksFile;
    }

    /**
     * Gets level name.
     *
     * @return the level name
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Sets level name.
     *
     * @param newLevelName the new level name
     */
    public void setLevelName(String newLevelName) {
        this.levelName = newLevelName;
    }

    /**
     * Gets ball velocities.
     *
     * @return the ball velocities
     */
    public List<Velocity> getBallVelocities() {
        return ballVelocities;
    }

    /**
     * Sets ball velocities.
     *
     * @param newBallVelocities the new ball velocities
     */
    public void setBallVelocities(List<Velocity> newBallVelocities) {
        this.ballVelocities = newBallVelocities;
    }

    /**
     * Sets background.
     *
     * @param newBackground the new background
     */
    public void setBackground(Sprite newBackground) {
        this.background = newBackground;
    }

    /**
     * Gets paddle speed.
     *
     * @return the paddle speed
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Sets paddle speed.
     *
     * @param newPaddleSpeed the new paddle speed
     */
    public void setPaddleSpeed(int newPaddleSpeed) {
        this.paddleSpeed = newPaddleSpeed;
    }

    /**
     * Gets paddle width.
     *
     * @return the paddle width
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * Sets paddle width.
     *
     * @param newPaddleWidth the new paddle width
     */
    public void setPaddleWidth(int newPaddleWidth) {
        this.paddleWidth = newPaddleWidth;
    }

    /**
     * Gets blocks start x.
     *
     * @return the blocks start x
     */
    public double getBlocksStartX() {
        return blocksStartX;
    }

    /**
     * Sets blocks start x.
     *
     * @param newBlocksStartX the new blocks start x
     */
    public void setBlocksStartX(double newBlocksStartX) {
        this.blocksStartX = newBlocksStartX;
    }

    /**
     * Gets blocks start y.
     *
     * @return the blocks start y
     */
    public double getBlocksStartY() {
        return blocksStartY;
    }

    /**
     * Sets blocks start y.
     *
     * @param newBlocksStartY the new blocks start y
     */
    public void setBlocksStartY(double newBlocksStartY) {
        this.blocksStartY = newBlocksStartY;
    }

    /**
     * Gets row height.
     *
     * @return the row height
     */
    public double getRowHeight() {
        return rowHeight;
    }

    /**
     * Sets row height.
     *
     * @param newRowHeight the new row height
     */
    public void setRowHeight(double newRowHeight) {
        this.rowHeight = newRowHeight;
    }

    /**
     * Gets num of blocks.
     *
     * @return the num of blocks
     */
    public int getNumOfBlocks() {
        return numOfBlocks;
    }

    /**
     * Sets num of blocks.
     *
     * @param newNumOfBlocks the new num of blocks
     */
    public void setNumOfBlocks(int newNumOfBlocks) {
        this.numOfBlocks = newNumOfBlocks;
    }

    @Override
    public int numberOfBalls() {
        return ballVelocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public List<Color> ballColor() {
        List<Color> colorList = new ArrayList<>();
        Color color = java.awt.Color.white;
        for (int i = 0; i < ballVelocities.size(); i++) {
            colorList.add(color);
        }
        return colorList;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

    @Override
    public int numOfLives() {
        return NUM_OF_LIVES;
    }

    @Override
    public List<Sprite> drawings() {
        return null;
    }

    /**
     * Sets block list.
     *
     * @param newBlockList the new block list
     */
    public void setBlockList(List<Block> newBlockList) {
        this.blockList = newBlockList;
    }
}
