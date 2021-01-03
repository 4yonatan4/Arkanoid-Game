package collidable;

import geometric.Point;
import geometric.Rectangle;
import sprite.Ball;
import sprite.Velocity;

/**
 * The interface collidable.Collidable.
 */
public interface Collidable {
    /**
     * Gets "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with  given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          the ball that hit the collidable.Collidable
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}