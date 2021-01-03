package geometric;

/**
 * This class define the geometric.Point class.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new geometric.Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the distance of this point to the other point
     */

    public double distance(Point other) {
        double num1 = (this.getX() - other.getX()) * (this.getX() - other.getX());
        double num2 = (this.getY() - other.getY()) * (this.getY() - other.getY());
        return Math.sqrt(num1 + num2);
    }

    /**
     * check if the point are equal to another point.
     *
     * @param other the other
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}