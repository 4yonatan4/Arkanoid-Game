package listener;

import collidable.Block;
import sprite.Ball;
import sprite.Counter;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprite.Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(15);
    }
}