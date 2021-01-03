package geometric;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The class geometric.Rectangle.
 */
public class Rectangle implements Sprite {

    private Point point;
    private double width;
    private double height;
    private java.awt.Color recColor;

    /**
     * Instantiates a new geometric.Rectangle with location and width/height.
     *
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.point = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new geometric.Rectangle.
     *
     * @param x      the x of the upperleft point.
     * @param y      the y of the upperleft point.
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.point = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.point = new Point(x, y);
        this.width = width;
        this.height = height;
        this.recColor = color;
    }

    /**
     * Found the intersection point between the rectangle and a line.
     *
     * @param line the line
     * @return the List (possibly empty) of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> interPoints = new ArrayList<>();
        if (this.getUpLine().intersectionWith(line) != null) {
            interPoints.add(this.getUpLine().intersectionWith(line));
        }
        if (this.getRightLine().intersectionWith(line) != null) {
            interPoints.add(this.getRightLine().intersectionWith(line));
        }
        if (this.getDownLine().intersectionWith(line) != null) {
            interPoints.add(this.getDownLine().intersectionWith(line));
        }
        if (this.getLeftLine().intersectionWith(line) != null) {
            interPoints.add(this.getLeftLine().intersectionWith(line));
        }
        return interPoints;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left point.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.point;
    }

    /**
     * Gets upper right point.
     *
     * @return the upper right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY());
    }

    /**
     * Gets down left point.
     *
     * @return the down left point of the rectangle
     */
    public Point getDownLeft() {
        return new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * Gets down right point.
     *
     * @return the down right point of the rectangle
     */
    public Point getDownRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * Gets up line.
     *
     * @return the up line of the rectangle
     */
    public Line getUpLine() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * Gets down line.
     *
     * @return the down line of the rectangle
     */
    public Line getDownLine() {
        return new Line(this.getDownLeft(), this.getDownRight());
    }

    /**
     * Gets right line.
     *
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return new Line(this.getDownRight(), this.getUpperRight());
    }

    /**
     * Gets left line.
     *
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return new Line(this.getDownLeft(), this.getUpperLeft());
    }

    /**
     * Sets color.
     *
     * @param color the color of the rectangle.
     */
    public void setColor(java.awt.Color color) {
        this.recColor = color;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.recColor;
    }

    /**
     * Gets point.
     *
     * @return the upperLeft point of the rectangle
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * Sets point.
     *
     * @param newPoint the new upperLeft point of the rectangle
     */
    public void setPoint(Point newPoint) {
        this.point = newPoint;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.recColor);
        d.drawRectangle((int) point.getX(), (int) point.getY(), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void timePassed() {

    }
}