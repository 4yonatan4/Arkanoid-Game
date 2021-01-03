package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import geometric.Line;
import geometric.Point;
import geometric.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The class game.GameLevel environment.
 */
public class GameEnvironment {

    private java.util.List<Collidable> collidables;

    /**
     * Instantiates a new game.GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove collidable to the environment.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory of the ball
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<CollisionInfo> allCollidables = new ArrayList<>();
        CollisionInfo first = null;
        List<Collidable> collidables2 = new ArrayList<Collidable>(this.collidables);
        for (Collidable c : collidables2) {
            Rectangle r = c.getCollisionRectangle();
            Point check = trajectory.closestIntersectionToStartOfLine(r);
            if (check != null) {
                CollisionInfo collisionInfo = new CollisionInfo(check, c);
                allCollidables.add(collisionInfo);
            }
        }
        // if the list is not empty, at least one collidable
        if (!(allCollidables.isEmpty())) {
            first = allCollidables.get(0);
            double minDistance = trajectory.start().distance(allCollidables.get(0).getCollisionPoint());
            for (int i = 1; i < allCollidables.size(); i++) {
                double curDistance = trajectory.start().distance(allCollidables.get(i).getCollisionPoint());
                if (curDistance < minDistance) {
                    minDistance = curDistance;
                    first = allCollidables.get(i);
                }
            }
        }
        return first;
    }
}