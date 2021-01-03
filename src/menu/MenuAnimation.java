package menu;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<Task<Void>> {

    private Task<Void> status;
    private List<Selection> selectionList;
    private List<Sub> subMenuList;
    private KeyboardSensor keyboard;
    private boolean stop;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Instantiates a new Menu animation.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public MenuAnimation(KeyboardSensor keyboardSensor) {
        this.keyboard = keyboardSensor;
        this.selectionList = new ArrayList<>();
    }

    @Override
    public void addSelection(String key, String message, Task<Void> returnVal) {
        selectionList.add(new Selection(key, message, returnVal));
    }

    @Override
    public Task<Void> getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<Task<Void>> subMenu) {
        subMenuList.add(new Sub(key, message, subMenu));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0x10FF51));
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        d.setColor(new Color(0x48FCFF));
        d.drawText(90, (HEIGHT / 2) - 200, "Arkanoid", 50);
        int yText = 180;
        for (Selection s : selectionList) {
            d.setColor(new Color(0xFFFD7E));
            d.drawText(130, yText, "(" + s.getKey() + ") " + s.getMessage(), 30);
            if (keyboard.isPressed(s.getKey())) {
                this.status = s.getTask();
                this.stop = true;
            }
            yText += 60;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Sets stop.
     *
     * @param newStop the new stop
     */
    public void setStop(boolean newStop) {
        this.stop = newStop;
    }
}
