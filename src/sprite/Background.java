package sprite;

import biuoop.DrawSurface;
import geometric.Point;
import geometric.Rectangle;
import levelreader.ColorsParser;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Background.
 */
public class Background implements Sprite {

    private Color backgroundColor;
    private Image img;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Instantiates a new Background.
     *
     * @param backgroundColor the background color
     */
    public Background(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Instantiates a new Background.
     *
     * @param s the s
     * @throws IOException the io exception
     */
    public Background(String s) throws IOException {
        s = s.replaceAll("[\\(\\)]", " ");
        String[] strArr = s.split(" ");
        switch (strArr[0]) {
            case "image":
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream((strArr[1]));
                if (is != null) {
                    this.img = ImageIO.read(is);
                }
                break;
            case "color":
                this.backgroundColor = new ColorsParser().colorFromString(s);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strArr[0]);
        }
    }


    @Override
    public void drawOn(DrawSurface d) {
        if (this.img == null) {
            Point p1 = new Point(0, 0);
            Rectangle rec = new Rectangle(p1, WIDTH, HEIGHT);
            d.setColor(this.backgroundColor);
            d.fillRectangle((int) p1.getX(), (int) p1.getY(), WIDTH, HEIGHT);
        } else {
            d.drawImage(25, 50, this.img);
        }
    }

    @Override
    public void timePassed() {

    }
}
