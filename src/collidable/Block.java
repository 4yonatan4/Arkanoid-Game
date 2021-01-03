package collidable;

import biuoop.DrawSurface;
import game.GameLevel;
import geometric.Point;
import geometric.Rectangle;
import levelreader.ColorsParser;
import listener.HitListener;
import listener.HitNotifier;
import sprite.Ball;
import sprite.Sprite;
import sprite.Velocity;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class collidable.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rec;
    private GameLevel gameLevel;
    private List<HitListener> hitListeners = new ArrayList<>();
    private int hitPoints;
    private Color border;
    private Color fillColor;
    private Map<Integer, String> fillMap;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;
    private Image img;


    /**
     * Instantiates a new collidable.Block.
     *
     * @param rec the rec of the block
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        this.fillColor = rec.getColor();
        this.colorMap = new HashMap<>();
        this.imageMap = new HashMap<>();
    }

    /**
     * Color or image.
     *
     * @throws IOException the io exception
     */
    public void colorOrImage() throws IOException {
        if (!this.fillMap.isEmpty()) {
            for (Map.Entry<Integer, String> i : this.fillMap.entrySet()) {
                if (i.getValue().contains("color")) {
                    colorMap.put(i.getKey(), new ColorsParser().colorFromString(i.getValue()));
                } else if (i.getValue().contains("image")) {
                    String str = i.getValue();
                    str = str.replaceFirst("image", "");
                    str = str.replaceAll("\\(", "");
                    str = str.replaceAll("\\)", "");
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
                    if (is != null) {
                        imageMap.put(i.getKey(), ImageIO.read(is));
                    }
                }
            }
        }
    }

    /**
     * Get the collision rectangle.
     *
     * @return collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * calculate the  new velocity of the sprite that hit the paddle.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the point of the collision
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the block
     * @return new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        boolean hit = false;
        if ((this.getCollisionRectangle().getUpLine().isOnLine(collisionPoint))
                || (this.getCollisionRectangle().getDownLine().isOnLine(collisionPoint))) {
            dy = -dy;
            hit = true;
        }
        if ((getCollisionRectangle().getRightLine().isOnLine(collisionPoint))
                || (getCollisionRectangle().getLeftLine().isOnLine(collisionPoint))) {
            dx = -dx;
            hit = true;
        }
        if (hit) {
            this.notifyHit(hitter);
            this.hitPoints--;
        }
        return new Velocity(dx, dy);
    }

    /**
     * Draw the ball on a given DrawSurface.
     *
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (img != null) {
            d.drawImage((int) this.rec.getPoint().getX(), (int) this.rec.getPoint().getY(), img);
        }
        if (this.fillColor != null) {
            d.setColor(this.fillColor);
            d.fillRectangle((int) this.getCollisionRectangle().getPoint().getX(),
                    (int) this.getCollisionRectangle().getPoint().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
        if ((colorMap != null && colorMap.containsKey(this.getHitPoints()))
                || (imageMap != null && imageMap.containsKey(this.getHitPoints()))) {
            if (colorMap != null && colorMap.containsKey(this.getHitPoints())) {
                d.setColor(colorMap.get(this.getHitPoints()));
                d.fillRectangle((int) this.getCollisionRectangle().getPoint().getX(),
                        (int) this.getCollisionRectangle().getPoint().getY(),
                        (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
            }
            if (imageMap != null && imageMap.containsKey(this.getHitPoints())) {
                d.drawImage((int) this.rec.getPoint().getX(), (int) this.rec.getPoint().getY(),
                        imageMap.get(this.getHitPoints()));
            }
        }
        if (this.border != null) {
            d.setColor(this.border);
            d.drawRectangle((int) this.getCollisionRectangle().getPoint().getX(),
                    (int) this.getCollisionRectangle().getPoint().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
    }


//    /**
//     * Draw the ball on a given DrawSurface.
//     *
//     * @param d the surface
//     */
//    @Override
//    public void drawOn(DrawSurface d) {
//        if (img != null) {
//            d.drawImage((int) this.rec.getPoint().getX(), (int) this.rec.getPoint().getY(), img);
//        }
//        if (this.fillColor != null) {
//            d.setColor(this.fillColor);
//            d.fillRectangle((int) this.getCollisionRectangle().getPoint().getX(),
//                    (int) this.getCollisionRectangle().getPoint().getY(),
//                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
//        }
//        if (fillMap != null && fillMap.containsKey(this.getHitPoints())) {
//            String imgOrColor = fillMap.get(this.getHitPoints());
//            imgOrColor = imgOrColor.replaceAll("[\\(\\)]", " ");
//            String[] strArr = imgOrColor.split(" ");
//            if (strArr[0].contains("image")) {
//                try {
//                    String fix = strArr[1].replaceAll(" ", "");
//                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream((strArr[1]));
//                    if (is != null) {
//                        d.drawImage((int) this.rec.getPoint().getX(), (int) this.rec.getPoint().getY(),
//                                ImageIO.read(is));
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else if (strArr[0].contains("color")) {
//                d.setColor(new ColorsParser().colorFromString(imgOrColor));
//                d.fillRectangle((int) this.getCollisionRectangle().getPoint().getX(),
//                        (int) this.getCollisionRectangle().getPoint().getY(),
//                        (int) this.getCollisionRectangle().getWidth(),
//                        (int) this.getCollisionRectangle().getHeight());
//            }
//        }
//        if (this.border != null) {
//            d.setColor(this.border);
//            d.drawRectangle((int) this.getCollisionRectangle().getPoint().getX(),
//                    (int) this.getCollisionRectangle().getPoint().getY(),
//                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
//        }
//    }

    @Override
    public void timePassed() {
        // nothing
    }

    /**
     * Add to gameLevel.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        this.gameLevel = g;
        this.gameLevel.addSprite(this);
        this.gameLevel.addCollidable(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param g the gameLevel
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * notifyHit all of the listener.HitListener about the Hit.
     *
     * @param hitter the ball that hit the collidable
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Sets hit points.
     *
     * @param newHitPoints the new hit points
     */
    public void setHitPoints(int newHitPoints) {
        this.hitPoints = newHitPoints;
    }

    /**
     * Gets border.
     *
     * @return the border
     */
    public Color getBorder() {
        return border;
    }

    /**
     * Sets border.
     *
     * @param newBorder the border
     */
    public void setBorder(Color newBorder) {
        this.border = newBorder;
    }

    /**
     * Gets fill color.
     *
     * @return the fill color
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets fill color.
     *
     * @param newFillColor the fill color
     */
    public void setFillColor(Color newFillColor) {
        this.fillColor = newFillColor;
    }

    /**
     * Sets fill map.
     *
     * @param newFillMap the fill map
     */
    public void setFillMap(Map<Integer, String> newFillMap) {
        this.fillMap = newFillMap;
    }

    /**
     * Sets img.
     *
     * @param newImg the new img
     */
    public void setImg(Image newImg) {
        this.img = newImg;
    }
}
