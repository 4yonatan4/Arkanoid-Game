package sprite;

import geometric.Point;

/**
 * The type sprite.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    // variable declaration
    private double dx;
    private double dy;

    /**
     * Instantiates a new sprite.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double fixAngle = angle - 90;
        double dx = speed * Math.cos(Math.toRadians(fixAngle));
        double dy = speed * Math.sin(Math.toRadians(fixAngle));
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }
}