package sprite;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.Collidable;
import game.GameLevel;
import game.GameEnvironment;
import geometric.Rectangle;
import geometric.Point;

import java.awt.Color;

/**
 * The class sprite.Paddle.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private GameEnvironment gameEnvironment;
    private GameLevel gameLevel;
    private Rectangle recPaddle;
    private int rightLim;
    private int leftLim;
    private int paddleSpeed;
    private int paddleWidth;
    private static final int SPEED = 6;

    /**
     * Instantiates a new sprite.Paddle.
     *
     * @param width       the width of the screen
     * @param height      the height of the screen
     * @param paddleSpeed the speed of the paddle
     * @param paddleWidth the width of the paddle
     */
    public Paddle(int width, int height, int paddleSpeed, int paddleWidth) {
        this.rightLim = width - 25;
        this.leftLim = 25;
        this.paddleSpeed = paddleSpeed;
        Point p = new Point((width / 2) - (paddleWidth / 2), height - 20);
        this.recPaddle = new Rectangle(p, paddleWidth, 15);
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.recPaddle.getPoint().getX() > this.leftLim) {
            double dx = this.recPaddle.getPoint().getX() - paddleSpeed;
            if (dx < this.leftLim) {
                dx = this.leftLim;
            }
            Point newPoint = new Point(dx, this.recPaddle.getPoint().getY());
            this.recPaddle.setPoint(newPoint);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (((this.recPaddle.getPoint().getX()) + this.getWidthPaddle()) < this.rightLim) {
            double dx = this.recPaddle.getPoint().getX() + paddleSpeed;
            if (dx + this.getWidthPaddle() > this.rightLim) {
                dx = this.rightLim - this.getWidthPaddle();
            }
            Point newPoint = new Point(dx, this.recPaddle.getPoint().getY());
            this.recPaddle.setPoint(newPoint);
        }
    }

    /**
     * Start to animation loop.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle.
     *
     * @param d - the surface of the gameLevel.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.getCollisionRectangle().getPoint().getX(),
                (int) this.getCollisionRectangle().getPoint().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getCollisionRectangle().getPoint().getX(),
                (int) this.getCollisionRectangle().getPoint().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Get the collision rectangle.
     *
     * @return rectangle collision
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.recPaddle;
    }

    /**
     * calculate the  new velocity of the sprite that hit the paddle.
     *
     * @param collisionPoint  the point of the collision
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the block
     * @return new velocity of the ball
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = new Velocity(currentVelocity.getDx(), -(currentVelocity.getDy()));
        if ((getCollisionRectangle().getRightLine().isOnLine(collisionPoint))) {
            return new Velocity(-(currentVelocity.getDx()), currentVelocity.getDy());
        }
        if (getCollisionRectangle().getLeftLine().isOnLine(collisionPoint)) {
            return new Velocity(-(currentVelocity.getDx()), currentVelocity.getDy());
        }
        if ((this.getCollisionRectangle().getUpLine().isOnLine(collisionPoint))) {
            if (currentVelocity.getDy() < 0) {
                return currentVelocity;
            }
            int hitPlace = checkHitPlace(collisionPoint);
            switch (hitPlace) {
                case 1:
                    newV = Velocity.fromAngleAndSpeed(300, SPEED);
                    break;
                case 2:
                    newV = Velocity.fromAngleAndSpeed(330, SPEED);
                    break;
                case 3:
                    // do nothing
                    break;
                case 4:
                    newV = Velocity.fromAngleAndSpeed(30, SPEED);
                    break;
                case 5:
                    newV = Velocity.fromAngleAndSpeed(60, SPEED);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + hitPlace);
            }
        }
        return newV;
    }

    /**
     * Check hit place.
     *
     * @param collisionPoint the collision point
     * @return the area of the hit
     */
    public int checkHitPlace(Point collisionPoint) {
        if ((collisionPoint.getX() >= this.recPaddle.getPoint().getX())
                && (collisionPoint.getX() < (this.recPaddle.getPoint().getX() + (this.recPaddle.getWidth() / 5)))) {
            return 1;
        }
        if ((collisionPoint.getX() >= (this.recPaddle.getPoint().getX() + (this.recPaddle.getWidth() / 5)))
                && (collisionPoint.getX() < (this.recPaddle.getPoint().getX() + 2 * (this.recPaddle.getWidth() / 5)))) {
            return 2;
        }
        if ((collisionPoint.getX() >= (this.recPaddle.getPoint().getX() + 3 * (this.recPaddle.getWidth() / 5)))
                && (collisionPoint.getX() < (this.recPaddle.getPoint().getX() + 4 * (this.recPaddle.getWidth() / 5)))) {
            return 4;
        }
        if ((collisionPoint.getX() >= (this.recPaddle.getPoint().getX() + 4 * (this.recPaddle.getWidth() / 5)))
                && (collisionPoint.getX()
                <= (this.recPaddle.getPoint().getX() + 5 * (this.recPaddle.getWidth() / 5)))) {
            return 5;
        }
        return 3;
    }

    /**
     * Add this paddle to the gameLevel.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        this.gameLevel = g;
        g.addSprite(this);
        g.addCollidable(this);
        setKeyboard(gameLevel.getKeyboard());
        setGameEnvironment(gameLevel.getEnvironment());
    }

    /**
     * Sets keyboard.
     *
     * @param newKeyboard the new keyboard
     */
    public void setKeyboard(KeyboardSensor newKeyboard) {
        this.keyboard = newKeyboard;
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
     * Gets width paddle.
     *
     * @return the width paddle
     */
    public double getWidthPaddle() {
        return this.recPaddle.getWidth();
    }

    /**
     * Remove from gameLevel.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}