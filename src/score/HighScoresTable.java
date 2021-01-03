package score;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable {

    private int size;
    private List<ScoreInfo> highScoreList;
    private SortComparator sortComparator;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final int LIST_SIZE = 5;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScoreList = new ArrayList<>();
        sortComparator = new SortComparator();
    }

    /**
     * Add a high-score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        int newRank = this.getRank(score.getScore());
        if (newRank <= this.size) {
            highScoreList.add(score);
            highScoreList.sort(sortComparator);
        }
        if (highScoreList.size() > size) {
            highScoreList.remove(this.size);
        }
    }

    /**
     * Return table size.
     *
     * @return the int
     */
    public int size() {
        return highScoreList.size();
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        highScoreList.sort(sortComparator);
        return highScoreList;
    }

    /**
     * return the rank of the current score.
     * where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        List<ScoreInfo> highScoreSortList = this.getHighScores();
        int rank = ONE;
        if (highScoreSortList.isEmpty()) {
            return rank;
        }
        for (ScoreInfo i : highScoreSortList) {
            if (score < i.getScore()) {
                rank++;
            }
            if (score >= i.getScore()) {
                return rank;
            }
            if (rank > this.size) {
                return ZERO;
            }
        }
        return rank;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.highScoreList.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        this.clear();
        ScoreInfo scoreInfo = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            while ((scoreInfo = (ScoreInfo) objectInputStream.readObject()) != null) {
                add(scoreInfo);
            }
        } catch (EOFException e) { // End of the file
            return;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            for (ScoreInfo i : getHighScores()) {
                objectOutputStream.writeObject(i);
            }
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(LIST_SIZE);
        try {
            highScoresTable.load(filename);
            return highScoresTable;
        } catch (Exception e) {
            return new HighScoresTable(LIST_SIZE);
        }
    }
}