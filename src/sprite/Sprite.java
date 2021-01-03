package sprite;

import biuoop.DrawSurface;

/**
 * The interface sprite.Sprite.
 */
public interface Sprite {
   /**
    * Draw the sprite to the screen.
    *
    * @param d the d
    */
   void drawOn(DrawSurface d);

   /**
    * Notify the sprite that time has passed.
    */
   void timePassed();
}