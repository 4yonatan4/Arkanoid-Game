package geometric;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {

    private int x;
    private int y;
    private int radius;
    private Color color;

    /**
     * Instantiates a new Circle.
     *
     * @param x      the x
     * @param y      the y
     * @param radius the radius
     * @param color  the color
     */
    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle(x, y, radius);
    }

    @Override
    public void timePassed() {

    }
}
