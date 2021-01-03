package menu;

/**
 * The type Selection.
 */
public class Selection {

    private Task<Void> task;
    private String key, message;

    /**
     * Instantiates a new Selection.
     *
     * @param key     the key
     * @param message the message
     * @param task    the task
     */
    public Selection(String key, String message, Task<Void> task) {
        this.key = key;
        this.message = message;
        this.task = task;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public Task<Void> getTask() {
        return this.task;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
