package sprite;

import biuoop.DrawSurface;
import collidable.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import geometric.Line;
import geometric.Point;

import java.awt.Color;

/**
 * This class define the sprite.Ball class.
 */
public class Ball implements Sprite {

    // class variables
    private Point point;
    private int r;
    private java.awt.Color ballColor;
    private Velocity ballVelocity;
    private GameEnvironment gameEnvironment;
    private GameLevel gameLevel;
    private static final int SPEED = 6;

    /**
     * Instantiates a new sprite.Ball.
     *
     * @param center the geometric.Point of the center
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.r = r;
        this.ballColor = color;
        this.ballVelocity = Velocity.fromAngleAndSpeed(315, SPEED);
    }

    /**
     * Instantiates a new sprite.Ball.
     *
     * @param x     the x of the center point
     * @param y     the y of the center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.r = r;
        this.ballColor = color;
        this.ballVelocity = Velocity.fromAngleAndSpeed(315, SPEED);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Gets size (radius) of the ball.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.ballColor;
    }

    /**
     * Draw the ball on a given DrawSurface.
     *
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.ballColor);
        d.fillCircle(this.getX(), this.getY(), this.getSize());
        d.setColor(Color.black);
        d.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets velocity.
     *
     * @param v the new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.ballVelocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.ballVelocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.ballVelocity;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        Point next = this.getVelocity().applyToPoint(this.point);
        Line trajectory = new Line(this.point, next);
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision == null) {
            this.point = next;
        } else {
            Velocity newVelocity = collision.getCollisionObject().hit(this, collision.getCollisionPoint(),
                    this.getVelocity());
            if (newVelocity.getDy() == this.getVelocity().getDy()
                    && newVelocity.getDx() == this.getVelocity().getDx()) {
                this.point = new Point(this.point.getX(), this.point.getY() - 4);
            }
            this.setVelocity(newVelocity);
        }
    }

    /**
     * Gets gameLevel environment.
     *
     * @return the gameLevel environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets gameLevel environment.
     *
     * @param newGameEnvironment the new gameLevel environment
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }

    /**
     * Start the animation loop of the ball.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add to gameLevel.
     *
     * @param g the current gameLevel.
     */
    public void addToGame(GameLevel g) {
        this.gameLevel = g;
        this.gameLevel.addSprite(this);
        this.gameEnvironment = this.gameLevel.getEnvironment();
        this.gameLevel.getCounterBalls().increase(1);
    }

    /**
     * Remove from gameLevel.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}