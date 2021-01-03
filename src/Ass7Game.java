import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levelreader.LevelSpecificationReader;
import levels.LevelInformation;
import menu.GameTask;
import menu.Menu;
import menu.MenuAnimation;
import menu.Set;
import menu.SubMenu;
import menu.Task;
import score.HighScoresTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass5game.
 */
public class Ass7Game {

    private GameFlow gameFlow;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresAnimation highScoresAnimation;
    private SubMenu<Task<Void>> subMenu;

    private Task<Void> showHighScoreTask = new Task<Void>() {
        @Override
        public Void run() {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                    keyboardSensor.SPACE_KEY, highScoresAnimation));
            return null;
        }
    };

    private Task<Void> quitTask = new Task<Void>() {
        @Override
        public Void run() {
            System.exit(0);
            return null;
        }
    };

    /**
     * makeLevels.
     *
     * @param is the name of the file
     * @return list<LevelInformation>
     * @throws FileNotFoundException exception
     */
    private List<LevelInformation> makeLevels(InputStream is) throws FileNotFoundException {
        LevelSpecificationReader l = new LevelSpecificationReader();
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_definitions.txt");
        //FileInputStream file = new FileInputStream(levelsFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        return l.fromReader(bufferedReader);
    }

    /**
     * Make sets list.
     *
     * @param bufferedReader the buffered reader
     * @return the list
     * @throws IOException the io exception
     */
    private List<Set> makeSets(BufferedReader bufferedReader) throws IOException {
        List<Set> setList = new ArrayList<>();
        String key = null, message = null, path = null;
        LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);
        String line = null;
        while ((line = lineNumberReader.readLine()) != null) {
            if ((lineNumberReader.getLineNumber() % 2) == 1) {
                String[] strArr = line.split(":");
                key = strArr[0];
                message = strArr[1];
            } else {
                path = line;
                setList.add(new Set(key, message, path));
            }
            line = bufferedReader.readLine();
        }
        return setList;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        Ass7Game ass7Game = new Ass7Game();
        InputStream is = null;
        if (args.length > 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("definitions/level_sets.txt");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        List<Set> setList = ass7Game.makeSets(bufferedReader);
        is.close();
        // if the file are not exist - first time!!!!
        File scoreFile = new File("highscores.ser");
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(scoreFile);
        highScoresTable.save(scoreFile);
        AnimationRunner animationRunner = new AnimationRunner();
        ass7Game.animationRunner = animationRunner;
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        ass7Game.keyboardSensor = keyboardSensor;
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboardSensor);
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable.getHighScores(),
                keyboardSensor);
        ass7Game.highScoresAnimation = highScoresAnimation;
        ass7Game.gameFlow = new GameFlow(animationRunner, keyboardSensor, highScoresTable,
                highScoresAnimation, scoreFile);
        ass7Game.subMenu = new SubMenu<>(keyboardSensor, animationRunner);
        for (Set s : setList) {
            ass7Game.subMenu.addSelection(s.getKey(), s.getMessage(),
                    new GameTask(animationRunner, s.getPath(), keyboardSensor, highScoresTable,
                            highScoresAnimation, scoreFile));
        }
        menu.addSelection("s", "Start Game", ass7Game.subMenu);
        menu.addSelection("h", "High Scores", ass7Game.showHighScoreTask);
        menu.addSelection("q", "Quit", ass7Game.quitTask);
        highScoresTable.save(scoreFile);
        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            ((MenuAnimation) menu).setStop(false);
        }
    }
}
