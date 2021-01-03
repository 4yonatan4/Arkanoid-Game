package levelreader;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Block properties.
 */
public class BlockProperties {

    private String symbol;
    private int height;
    private int width;
    private int hitPoints;
    private String fill;
    private Color stroke;
    private Map<Integer, String> fillMap;

    /**
     * Instantiates a new Block properties.
     */
    public BlockProperties() {
        fillMap = new HashMap<>();
    }

    /**
     * Add color k.
     *
     * @param num          the num
     * @param colorOrImage the color or image
     */
    public void addColorK(Integer num, String colorOrImage) {
        fillMap.put(num, colorOrImage);
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets symbol.
     *
     * @param newSymbol the new symbol
     */
    public void setSymbol(String newSymbol) {
        this.symbol = newSymbol;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param newHeight the new height
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param newWidth the new width
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
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
     * Gets fill.
     *
     * @return the fill
     */
    public String getFill() {
        return fill;
    }

    /**
     * Sets fill.
     *
     * @param newFill the new fill
     */
    public void setFill(String newFill) {
        this.fill = newFill;
    }

    /**
     * Gets stroke.
     *
     * @return the stroke
     */
    public Color getStroke() {
        return stroke;
    }

    /**
     * Sets stroke.
     *
     * @param color the new stroke color
     */
    public void setStroke(Color color) {
        this.stroke = color;
    }

    /**
     * Gets fill map.
     *
     * @return the fill map
     */
    public Map<Integer, String> getFillMap() {
        return this.fillMap;
    }

    /**
     * Sets fill map.
     *
     * @param newMap the new map
     */
    public void setFillMap(Map<Integer, String> newMap) {
        this.fillMap = newMap;
    }
}
