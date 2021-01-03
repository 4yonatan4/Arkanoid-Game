package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprite.Background;
import sprite.Counter;

import java.awt.Color;

import static java.lang.String.valueOf;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter numOfLives;
    private Counter score;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Instantiates a new End screen.
     *
     * @param k     the k
     * @param lives the lives
     * @param score the score
     */
    public EndScreen(KeyboardSensor k, Counter lives, Counter score) {
        this.keyboard = k;
        this.numOfLives = lives;
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Background background = new Background(new Color(0x6CFF7F));
        background.drawOn(d);
        if (numOfLives.getValue() > 0) {
            d.setColor(Color.RED);
            d.drawText(240, d.getHeight() - 400, "You Win!", 80);
        } else {
            d.setColor(Color.RED);
            d.drawText((WIDTH / 2) - 220, d.getHeight() - 400, "Game Over!", 80);
        }
        d.setColor(Color.BLUE);
        d.drawText(220, d.getHeight() - 300, "Your Score is", 60);
        d.drawText(370, d.getHeight() - 200, valueOf(this.score.getValue()), 70);
        d.setColor(Color.black);
        d.drawText((WIDTH / 2) - 140, d.getHeight() - 40, "press space to exit", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
