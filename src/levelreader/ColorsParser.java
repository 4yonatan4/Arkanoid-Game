package levelreader;


import java.awt.Color;


import static java.lang.Integer.valueOf;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * parse color definition and return the specified color.
     *
     * @param s the s
     * @return the java . awt . color
     */
    public java.awt.Color colorFromString(String s) {
        s = s.replaceAll("[\\(\\)]", " ");
        String[] strArr = s.split(" ");
        if (strArr[0].equals("color")) {
            if (strArr[1].equals("RGB")) {
                String[] strRgb = strArr[2].split(",");
                return new Color(valueOf(strRgb[0]), valueOf(strRgb[1]),
                        valueOf(strRgb[2]));
            } else {
                return this.getColor(strArr[1]);
            }
        } else {
            return null;
        }
    }

    /**
     * Gets color.
     *
     * @param colorStr the color str
     * @return the color
     */
    public Color getColor(String colorStr) {
        Color color = null;
        switch (colorStr.toLowerCase()) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "green":
                color = Color.GREEN;
                break;
            case "yellow":
                color = Color.YELLOW;
                break;
            case "lightgray":
                color = Color.LIGHT_GRAY;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "white":
                color = Color.WHITE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + colorStr.toLowerCase());
        }
        return color;
    }
}