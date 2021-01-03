package listener;

import collidable.Block;
import sprite.Ball;

/**
 * The interface Hit listener.
 */
public interface HitListener {
   /**
    * This method is called whenever the beingHit object is hit.
    * The hitter parameter is the sprite.Ball that's doing the hitting.
    *
    * @param beingHit the being hit
    * @param hitter   the hitter
    */
   void hitEvent(Block beingHit, Ball hitter);
}