package listener;

import collidable.Block;
import game.GameLevel;
import sprite.Ball;
import sprite.Counter;

/**
 * The type collidable.Block remover.
 * a listener.BlockRemover is in charge of removing blocks from the gameLevel,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new collidable.Block remover.
     *
     * @param gameLevel     the gameLevel
     * @param counterBlocks the counter of the blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter counterBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = counterBlocks;
    }


    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the gameLevel.
     *
     * @param beingHit the block that the ball hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() <= 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(gameLevel);
            gameLevel.getCounterBlocks().increase(1);
        }
    }
}