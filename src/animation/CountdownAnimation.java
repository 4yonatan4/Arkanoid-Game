package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private double time;
    private Sleeper sleeper;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.time = numOfSeconds / countFrom;
        this.sleeper = new Sleeper();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        if (countFrom == 3) {
            d.drawText((d.getWidth() / 2) - 25, d.getHeight() / 2, String.valueOf(countFrom), 90);
            countFrom--;
        } else if (countFrom > 0 && countFrom < 3) {
            d.drawText((d.getWidth() / 2) - 25, d.getHeight() / 2, String.valueOf(countFrom), 90);
            countFrom--;
            sleeper.sleepFor((long) time);
        } else {
            sleeper.sleepFor((long) time);
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}