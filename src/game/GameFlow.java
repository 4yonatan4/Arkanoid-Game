package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import score.HighScoresTable;
import score.ScoreInfo;
import sprite.Counter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter numOfLives;
    private Counter score;
    private HighScoresTable highScoresTable;
    private HighScoresAnimation highScoresAnimation;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar                  the ar
     * @param ks                  the ks
     * @param highScoresTable     highscore table
     * @param highScoresAnimation highscoreanimation
     * @param file                the file
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks,
                    HighScoresTable highScoresTable, HighScoresAnimation highScoresAnimation, File file) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.highScoresTable = highScoresTable;
        this.highScoresAnimation = highScoresAnimation;
        this.file = file;
    }

    /**
     * playerNameDialog.
     *
     * @return String the playername
     */
    private String playerNameDialog() {
        DialogManager dialog = this.animationRunner.getGui().getDialogManager();
        return (dialog.showQuestionDialog("Name", "What is your name?", ""));
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     * @throws IOException the io exception
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        this.numOfLives = new Counter();
        this.score = new Counter();
        this.numOfLives.increase(levels.get(0).numOfLives());
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor, this.animationRunner,
                    this.numOfLives, this.score);
            level.initialize();
            // level has more blocks and player has more lives
            while ((level.getNumOfLives().getValue() > 0)
                    && level.getCounterBlocks().getValue() < levelInfo.numberOfBlocksToRemove()) {
                level.playOneTurn();
            }
            if (level.getCounterBlocks().getValue() >= levelInfo.numberOfBlocksToRemove()) {
                this.score.increase(100);
            }
            if (level.getNumOfLives().getValue() == 0) {
                break;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                this.keyboardSensor.SPACE_KEY, new EndScreen(this.keyboardSensor,
                this.numOfLives, this.score)));
        if ((highScoresTable.getRank(this.score.getValue())) != 0) {
            ScoreInfo scoreInfo = new ScoreInfo(playerNameDialog(), this.score.getValue());
            highScoresTable.add(scoreInfo);
        }
        highScoresTable.save(file);
        animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                this.keyboardSensor.SPACE_KEY, highScoresAnimation));
    }
}