package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = runner.getGui().getKeyboardSensor();
    }

    /**
     * Run the high score animation.
     *
     * @return null
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                this.keyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}