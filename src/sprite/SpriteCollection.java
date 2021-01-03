package sprite;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The class sprite.Sprite collection.
 */
public class SpriteCollection {

    private java.util.List<Sprite> spriteList;

    /**
     * Instantiates a new sprite.Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listeners = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s : listeners) {
            s.timePassed();
        }
    }

    /**
     * Draw all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> listeners = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s : listeners) {
            s.drawOn(d);
        }
    }
}