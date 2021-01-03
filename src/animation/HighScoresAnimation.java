package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import score.ScoreInfo;

import java.awt.Color;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private List<ScoreInfo> highScoreList;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Instantiates a new High scores animation.
     *
     * @param highScoreList the high score list
     * @param keyboard      the keyboard
     */
    public HighScoresAnimation(List<ScoreInfo> highScoreList, KeyboardSensor keyboard) {
        this.highScoreList = highScoreList;
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0x3DFFD3));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        d.setColor(new Color(0xFF5A4F));
        d.drawText((WIDTH / 2) - 130, (HEIGHT / 2) - 200, "High Scores", 50);
        d.setColor(new Color(0x0EFF26));
        d.drawText((WIDTH / 2) - 250, (HEIGHT / 2) - 100, "Player Name", 40);
        d.drawText((WIDTH / 2) + 150, (HEIGHT / 2) - 100, "Score", 40);
        d.setColor(new Color(0xFFFD64));
        d.drawText((WIDTH / 2) - 250, (HEIGHT / 2) - 90,
                "__________________", 50);
        d.setColor(new Color(0x118FFF));
        int xPlayer = (WIDTH / 2) - 220;
        int y = (HEIGHT / 2) - 40;
        int xScore = (WIDTH / 2) + 170;
        for (ScoreInfo i : this.highScoreList) {
            d.drawText(xPlayer, y, i.getName(), 35);
            d.drawText(xScore, y, valueOf(i.getScore()), 35);
            y += 60;
        }
        d.setColor(Color.black);
        d.drawText((WIDTH / 2) - 140, d.getHeight() - 40, "press space to exit", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
