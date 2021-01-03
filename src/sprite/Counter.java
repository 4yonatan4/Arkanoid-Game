package sprite;

/**
 * The type sprite.Counter.
 */
public class Counter {

    private int count = 0;

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return count;
    }
}