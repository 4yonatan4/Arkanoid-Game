package levelreader;

import collidable.Block;
import levels.LevelInfo;
import levels.LevelInformation;
import sprite.Background;
import sprite.Velocity;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    private BlocksFromSymbolsFactory blocksFromSymbolsFactory;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.BufferedReader reader) {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("START_LEVEL")) {
                    LevelInfo levelInfo = readOneLevel(reader);
                    levelInformationList.add(levelInfo);
                }
                line = reader.readLine();
            }
        } catch (EOFException e) {
            return levelInformationList;
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
        return levelInformationList;
    }

    /**
     * Read one level level info.
     *
     * @param reader the reader
     * @return the level info
     */
    public LevelInfo readOneLevel(java.io.BufferedReader reader) {
        LevelInfo levelInfo = new LevelInfo();
        try {
            String line = reader.readLine();
            while (!line.equals("END_LEVEL")) {
                String[] arrOfStr = line.split(":");
                switch (arrOfStr[0]) {
                    case "level_name":
                        levelInfo.setLevelName(arrOfStr[1]);
                        break;
                    case "ball_velocities":
                        List<Velocity> velocityList = new ArrayList<>();
                        String[] arrOfVel = arrOfStr[1].split(" ");
                        for (String s : arrOfVel) {
                            String[] arrOfangleAndSpeed = s.split(",");
                            int angle = valueOf(arrOfangleAndSpeed[0]);
                            int speed = valueOf(arrOfangleAndSpeed[1]);
                            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
                            velocityList.add(velocity);
                        }
                        levelInfo.setBallVelocities(velocityList);
                        break;
                    case "background":
                        levelInfo.setBackground(new Background(arrOfStr[1]));
                        break;
                    case "paddle_speed":
                        levelInfo.setPaddleSpeed(valueOf(arrOfStr[1]));
                        break;
                    case "paddle_width":
                        levelInfo.setPaddleWidth(valueOf(arrOfStr[1]));
                        break;
                    case "block_definitions":
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream((arrOfStr[1]));
                        if (is != null) {
                            levelInfo.setBlocksFile(is);
                        }
                        break;
                    case "blocks_start_x":
                        levelInfo.setBlocksStartX(valueOf(arrOfStr[1]));
                        break;
                    case "blocks_start_y":
                        levelInfo.setBlocksStartY(valueOf(arrOfStr[1]));
                        break;
                    case "row_height":
                        levelInfo.setRowHeight(valueOf(arrOfStr[1]));
                        break;
                    case "num_blocks":
                        levelInfo.setNumOfBlocks(valueOf(arrOfStr[1]));
                        break;
                    case "START_BLOCKS":
                        List<Block> blockList = readBlocks(levelInfo.getBlocksFile(), reader, levelInfo.getRowHeight(),
                                levelInfo.getBlocksStartX(), levelInfo.getBlocksStartY());
                        levelInfo.setBlockList(blockList);
                        break;
                    case "END_BLOCKS":
                        break;
                    default:
                        break;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelInfo;
    }

    /**
     * Read blocks list.
     *
     * @param reader     the reader
     * @param blocksFile the blocks List
     * @param rowHeight  the rowHeight
     * @param x          the x
     * @param y          the y
     * @return the list
     * @throws IOException IOException
     */
    private List<Block> readBlocks(InputStream blocksFile, BufferedReader reader, double rowHeight,
                                   double x, double y) throws IOException {
        BufferedReader blockReader = new BufferedReader(new InputStreamReader(blocksFile));
        this.blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(blockReader);
        List<Block> blockList = new ArrayList<>();
        double xPos = x, yPos = y;
        String line = reader.readLine();
        while (!(line.equals("END_BLOCKS"))) {
            String[] symbols = line.split("");
            for (String s : symbols) {
                if (this.blocksFromSymbolsFactory.isSpaceSymbol(s)) {
                    xPos += this.blocksFromSymbolsFactory.getSpaceWidth(s);
                }
                if (this.blocksFromSymbolsFactory.isBlockSymbol(s)) {
                    Block block = this.blocksFromSymbolsFactory.getBlock(s, (int) xPos, (int) yPos);
                    block.colorOrImage();
                    blockList.add(block);
                    xPos += block.getCollisionRectangle().getWidth();
                }
            }
            line = reader.readLine();
            yPos += rowHeight;
            xPos = x;
        }
        return blockList;
    }
}
