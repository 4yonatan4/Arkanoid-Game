package listener;

import collidable.Block;
import game.GameLevel;
import sprite.Ball;
import sprite.Counter;

/**
 * The type sprite.Ball remover.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainBalls;

    /**
     * Instantiates a new sprite.Ball remover.
     *
     * @param gameLevel1       the gameLevel 1
     * @param remainBalls the remain balls
     */
    public BallRemover(GameLevel gameLevel1, Counter remainBalls) {
        this.gameLevel = gameLevel1;
        this.remainBalls = remainBalls;
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
        hitter.removeFromGame(gameLevel);
        gameLevel.getCounterBalls().decrease(1);
    }
}
