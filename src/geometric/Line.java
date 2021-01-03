package geometric;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * This class define the geometric.Line class.
 */
public class Line implements Sprite {
    private Point p1;
    private Point p2;
    private Color color;

    /**
     * The method is the first constructor of the geometric.Line.
     *
     * @param start - the point of the start of the line.
     * @param end   - the point of the end of the line.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     * @param color the color
     */
    public Line(Point start, Point end, Color color) {
        this.p1 = start;
        this.p2 = end;
        this.color = color;
    }

    /**
     * The method is the second constructor of the geometric.Line.
     *
     * @param x1 - x of point1.
     * @param y1 - y of point1.
     * @param x2 - x of point2.
     * @param y2 - y of point2.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * The method is the second constructor of the geometric.Line.
     *
     * @return the length of the line.
     */
    public double length() {
        double num1 = Math.pow((p1.getX() - p2.getX()), 2);
        double num2 = Math.pow((p1.getY() - p2.getY()), 2);
        return Math.sqrt(num1 + num2);
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        Point middlePoint;
        double middleX = ((this.p1.getX() + this.p2.getX()) / 2);
        double middleY = ((this.p1.getY() + this.p2.getY()) / 2);
        middlePoint = new Point(middleX, middleY);
        return middlePoint;
    }

    /**
     * start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.p1;
    }

    /**
     * end point.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.p2;
    }

    /**
     * check if the lines intersect.
     *
     * @param other line
     * @return true if the lines intersect.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * calculate the intersection point.
     *
     * @param other line
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.vertical() || other.vertical()) {
            if (this.vertical() && other.vertical()) {
                return (this.onePointEqual(other));
            }
            double m, b, y;
            // this line is vertical, check if the other line intersect this line
            if (this.vertical()) {
                if ((this.p1.getX() <= other.p1.getX()
                        && this.p1.getX() >= other.p2.getX())
                        || (this.p1.getX() <= other.p2.getX()
                        && this.p1.getX() >= other.p1.getX())) {
                    m = other.slope();
                    b = other.p1.getY() - m * other.p1.getX();
                    y = m * this.p1.getX() + b;
                    if ((y <= this.p1.getY() && y >= this.p2.getY())
                            || (y <= this.p2.getY() && y >= this.p1.getY())) {
                        Point inter = new Point(this.p1.getX(), y);
                        return inter;
                    }
                }
                return null;
            }
            // other line is vertical, check if this line intersect other line
            if (other.vertical()) {
                if ((other.p1.getX() <= this.p1.getX()
                        && other.p1.getX() >= this.p2.getX())
                        || (other.p1.getX() <= this.p2.getX()
                        && other.p1.getX() >= this.p1.getX())) {
                    m = this.slope();
                    b = this.p1.getY() - m * this.p1.getX();
                    y = m * other.p1.getX() + b;
                    if ((y <= other.p1.getY() && y >= other.p2.getY())
                            || (y <= other.p2.getY() && y >= other.p1.getY())) {
                        Point inter = new Point(other.p1.getX(), y);
                        return inter;
                    }
                    return null;
                }
            }
        }
        if (this.slope() == other.slope()) {
            return this.onePointEqual(other);
        }
        Point intersection = findIntersection(other);
        if (intersection.getX() <= this.p1.getX()
                && intersection.getX() >= this.p2.getX()) {
            if ((intersection.getX() <= other.p1.getX()
                    && intersection.getX() >= other.p2.getX())
                    || (intersection.getX() >= other.p1.getX()
                    && intersection.getX() <= other.p2.getX())) {
                return intersection;
            }
        }
        if (intersection.getX() >= this.p1.getX()
                && intersection.getX() <= this.p2.getX()) {
            if ((intersection.getX() <= other.p1.getX()
                    && intersection.getX() >= other.p2.getX())
                    || (intersection.getX() >= other.p1.getX()
                    && intersection.getX() <= other.p2.getX())) {
                return intersection;
            }
        }
        return null;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.p1.getX() == other.p1.getX()
                && this.p1.getY() == other.p1.getY()
                && this.p2.getX() == other.p2.getX()
                && this.p2.getY() == other.p2.getY());
    }

    /**
     * Find intersection point.
     *
     * @param other the other
     * @return the point
     */
    public Point findIntersection(Line other) {
        Point inter;
        double m1, m2, b1, b2, x, y;
        m1 = this.slope();
        m2 = other.slope();
        b1 = this.p1.getY() - m1 * (this.p1.getX());
        b2 = other.p1.getY() - m2 * (other.p1.getX());
        x = (b2 - b1) / (m1 - m2);
        y = (m1 * x) + b1;
        return new Point(x, y);
    }

    /**
     * check if there is ONLY one point equal between 2 lines.
     *
     * @param other the other
     * @return if there is ONLY one point equal - return the point, null otherwise.
     */
    public Point onePointEqual(Line other) {
        // check if there is just one equal point
        if (this.start().equals(other.start())
                && (!this.end().equals(other.end()))) {
            return this.start();
        }
        if (this.end().equals(other.end())
                && (!this.start().equals(other.start()))) {
            return this.end();
        }
        if (this.start().equals(other.end())
                && (!this.end().equals(other.start()))) {
            return this.start();
        }
        if (this.end().equals(other.start())
                && (!this.start().equals(other.end()))) {
            return this.end();
        }
        return null;
    }

    /**
     * calculate the slope of the line.
     *
     * @return the slope of the line
     */
    public double slope() {
        return (this.p1.getY() - this.p2.getY())
                / (this.p1.getX() - this.p2.getX());
    }

    /**
     * check if the line is vertical.
     *
     * @return true if the line is vertical, false otherwise.
     */
    public boolean vertical() {
        return (this.p1.getX() == this.p2.getX());
    }

    /**
     * Found the closest intersection to start of line point.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rect
     * @return the closest intersection to start of line point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> interPoints = rect.intersectionPoints(this);
        // if the list is empty
        if (interPoints.isEmpty()) {
            return null;
        }
        // found the closest intersection point to the start of the line.
        if (interPoints.size() > 1) {
            if (interPoints.get(0).distance(this.start())
                    < interPoints.get(1).distance(this.start())) {
                return interPoints.get(0);
            } else {
                return interPoints.get(1);
            }
        } else {
            return interPoints.get(0);
        }

    }

    /**
     * Is on line boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isOnLine(Point other) {
        double l1 = this.start().distance(other);
        double l2 = this.end().distance(other);
        double x = Math.abs(this.length() - (l1 + l2));
        if (Math.floor(x) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.p1.getX(), (int) this.p1.getY(),
                (int) this.p2.getX(), (int) this.p2.getY());
    }

    @Override
    public void timePassed() {

    }
}