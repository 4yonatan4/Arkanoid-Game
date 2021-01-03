package levelreader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static java.lang.Integer.valueOf;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {

    private BlockProperties defaultBlock;

    /**
     * Read one block.
     *
     * @param blockProperties the block properties
     * @param arrOfStr        the arr of str
     */
    private static void readOneBlock(BlockProperties blockProperties, String[] arrOfStr) {
        for (int i = 1; i < arrOfStr.length; i += 2) {
            if (arrOfStr[i].contains("-")) {
                String[] arr = arrOfStr[i].split("-");
                Integer num = valueOf(arr[1]);
                String colorOrImage = arrOfStr[i + 1];
                blockProperties.addColorK(num, colorOrImage);
            }
            switch (arrOfStr[i]) {
                case "symbol":
                    blockProperties.setSymbol(arrOfStr[i + 1]);
                    break;
                case "height":
                    blockProperties.setHeight(valueOf(arrOfStr[i + 1]));
                    break;
                case "width":
                    blockProperties.setWidth(valueOf(arrOfStr[i + 1]));
                    break;
                case "hit_points":
                    blockProperties.setHitPoints(valueOf(arrOfStr[i + 1]));
                    break;
                case "fill":
                    blockProperties.setFill(arrOfStr[i + 1]);
                    break;
                case "stroke":
                    blockProperties.setStroke(new ColorsParser().colorFromString(arrOfStr[i + 1]));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Check info.
     *
     * @param blockProperties the block properties
     */
    public static void checkInfo(BlockProperties blockProperties) {
        boolean missing = false;
        if (blockProperties.getSymbol() == null) {
            missing = true;
        }
        if (blockProperties.getHeight() == 0) {
            missing = true;
        }
        if (blockProperties.getWidth() == 0) {
            missing = true;
        }
        if (blockProperties.getFill() == null && (!blockProperties.getFillMap().containsKey(1))) {
            missing = true;
        }
        if (missing) {
            System.out.println("missing information at the blockProperties");
            System.exit(1);
        }
    }

//    /**
//     * Complete info.
//     *
//     * @param blockProperties the block properties
//     * @param defaultBlock    the default block
//     */
//    public static void completeInfo(BlockProperties blockProperties, BlockProperties defaultBlock) {
//        boolean missing = false;
//        if (blockProperties.getSymbol() == null) {
//            if (defaultBlock.getSymbol() != null) {
//                blockProperties.setSymbol(defaultBlock.getSymbol());
//            } else {
//                missing = true;
//            }
//        }
//        if (blockProperties.getHeight() == 0) {
//            if (defaultBlock.getHeight() > 0) {
//                blockProperties.setHeight(defaultBlock.getHeight());
//            } else {
//                missing = true;
//            }
//        }
//        if (blockProperties.getWidth() == 0) {
//            if (defaultBlock.getWidth() > 0) {
//                blockProperties.setWidth(defaultBlock.getWidth());
//            } else {
//                missing = true;
//            }
//        }
//        if (blockProperties.getFill() == null && (!blockProperties.getFillMap().containsKey(1))) {
//            if (defaultBlock.getFill() != null || (defaultBlock.getFillMap().containsKey(1))) {
//                if (defaultBlock.getFill() != null) {
//                    blockProperties.setFill(defaultBlock.getFill());
//                }
//                if (defaultBlock.getFillMap().containsKey(1)) {
//                    blockProperties.setFillMap(defaultBlock.getFillMap());
//                }
//                blockProperties.setFill(defaultBlock.getFill());
//            } else {
//                missing = true;
//            }
//        }
//        if (blockProperties.getStroke() == null && defaultBlock.getStroke() != null) {
//            blockProperties.setStroke(defaultBlock.getStroke());
//        }
//        if (blockProperties.getHitPoints() == 0 && defaultBlock.getHitPoints() != 0) {
//            blockProperties.setHitPoints(defaultBlock.getHitPoints());
//        }
//        if (missing) {
//            System.out.println("missing information at the blockProperties");
//            System.exit(1);
//        }
//    }

    /**
     * Read one space.
     *
     * @param space    the space
     * @param arrOfStr the arr of str
     */
    public static void readOneSpace(Space space, String[] arrOfStr) {
        for (int i = 1; i < arrOfStr.length; i++) {
            switch (arrOfStr[i]) {
                case "symbol":
                    space.setSymbol(arrOfStr[i + 1]);
                    break;
                case "width":
                    space.setWidth(valueOf(arrOfStr[i + 1]));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.BufferedReader reader) {
        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        BlocksFromSymbolsFactory blockFromSymbols = new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
        BlockProperties defaultBlock = null;
        String[] defString = null;
        try {
            String line = reader.readLine();
            while (line != null) {
                String temp = line.replaceAll(":", " ");
                String[] arrOfStr = temp.split(" ");
                switch (arrOfStr[0]) {
                    case "default":
                        defaultBlock = new BlockProperties();
                        defString = arrOfStr;
                        readOneBlock(defaultBlock, arrOfStr);
                        break;
                    case "bdef":
                        BlockProperties blockProperties = new BlockProperties();
                        if (defString != null) {
                            readOneBlock(blockProperties, defString);
                        }
                        readOneBlock(blockProperties, arrOfStr);
                        checkInfo(blockProperties);
                        blockCreators.put(blockProperties.getSymbol(), new BlockCreate(blockProperties));
                        break;
                    case "sdef":
                        Space space = new Space();
                        readOneSpace(space, arrOfStr);
                        spacerWidths.put(space.getSymbol(), space.getWidth());
                        break;
                    default:
                        break;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) { // Exception might have happened at constructor
                try {
                    reader.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
        return blockFromSymbols;
    }
}