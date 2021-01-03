package levelreader;

import collidable.Block;
import geometric.Rectangle;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Block create.
 */
public class BlockCreate implements BlockCreator {

    private BlockProperties blockProperties;

    /**
     * Instantiates a new Block create.
     *
     * @param blockProperties the block properties
     */
    public BlockCreate(BlockProperties blockProperties) {
        this.blockProperties = blockProperties;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rec = new Rectangle(xpos, ypos,
                blockProperties.getWidth(), blockProperties.getHeight());
        Block block = new Block(rec);
        block.setHitPoints(blockProperties.getHitPoints());
        block.setBorder(blockProperties.getStroke());
        block.setFillMap(blockProperties.getFillMap());
        String newFill = blockProperties.getFill();
        if (newFill != null) {
            if (newFill.contains("color")) {
                block.setFillColor(new ColorsParser().colorFromString(newFill));
            } else if (newFill.contains("image")) {
                try {
                    String fileName = newFill.replaceFirst("image", "");
                    fileName = fileName.replaceAll("\\(", "");
                    fileName = fileName.replaceAll("\\)", "");
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream((fileName));
                    if (is != null) {
                        block.setImg(ImageIO.read(is));
                    }
                } catch (IOException e) {
                    System.out.println(" Not Found file !");
                }
            }
        }
        return block;
    }
}
