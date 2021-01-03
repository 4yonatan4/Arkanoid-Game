package sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Lives Indicator.
 */
public class LivesIndicator implements Sprite {

    private GameLevel gameLevel;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param g the g
     */
    public LivesIndicator(GameLevel g) {
        this.gameLevel = g;
        gameLevel.addSprite(this);
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(70, 19,
                "Lives: " + gameLevel.getNumOfLives().getValue(), 15);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
