package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Instantiates a new Animation runner.
     */
    public AnimationRunner() {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public biuoop.GUI getGui() {
        return this.gui;
    }
}