package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geometric.Point;
import geometric.Rectangle;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {

    private geometric.Rectangle rec;
    private GameLevel gameLevel;

    /**
     * Instantiates a new Score indicator.
     *
     * @param g the g
     */
    public ScoreIndicator(GameLevel g) {
        this.gameLevel = g;
        gameLevel.addSprite(this);
        Point p = new geometric.Point(0, 0);
        this.rec = new Rectangle(p, gameLevel.getWidth(), 25);
        this.rec.setColor(Color.LIGHT_GRAY);
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(rec.getColor());
        d.drawRectangle(0, 0, (int) rec.getWidth(), (int) rec.getHeight());
        d.fillRectangle(0, 0, (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(((int) rec.getWidth() / 2) - 27, ((int) rec.getHeight() / 2) + 7,
                "Score: " + gameLevel.getCounterScore().getValue(), 15);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
