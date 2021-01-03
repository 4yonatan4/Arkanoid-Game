package collidable;

import geometric.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Gets collision point.
     *
     * @return the collision point
     */
// the point at which the collision occurs.
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Gets collision object.
     *
     * @return the collision object
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }
}