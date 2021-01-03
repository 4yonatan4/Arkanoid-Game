package sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {

    private GameLevel gameLevel;
    private String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param g         the g
     * @param levelName the level name
     */
    public LevelName(GameLevel g, String levelName) {
        this.gameLevel = g;
        this.levelName = levelName;
        this.gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(550, 19, "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {

    }
}
