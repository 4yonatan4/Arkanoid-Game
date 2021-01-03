package menu;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levelreader.LevelSpecificationReader;
import levels.LevelInformation;
import score.HighScoresTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Game task.
 */
public class GameTask implements Task<Void> {

    private AnimationRunner runner;
    private List<LevelInformation> levels;
    private GameFlow gameFlow;
    private File scoreFile;


    /**
     * Instantiates a new Game task.
     *
     * @param runner              the runner
     * @param path                the path
     * @param keyboardSensor      the keyboard sensor
     * @param highScoresTable     the high scores table
     * @param highScoresAnimation the high scores animation
     * @param scoreFile           the score file
     * @throws IOException the io exception
     */
    public GameTask(AnimationRunner runner, String path, KeyboardSensor keyboardSensor,
                    HighScoresTable highScoresTable,
                    HighScoresAnimation highScoresAnimation, File scoreFile) throws IOException {
        this.runner = runner;
        InputStream file = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
        this.levels = new LevelSpecificationReader().fromReader(bufferedReader);
        this.gameFlow = new GameFlow(runner, keyboardSensor, highScoresTable, highScoresAnimation, scoreFile);
        this.scoreFile = scoreFile;
    }

    @Override
    public Void run() {
        if (!levels.isEmpty()) {
            try {
                this.gameFlow.runLevels(levels);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
