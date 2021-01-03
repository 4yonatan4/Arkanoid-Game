package menu;

/**
 * The type Set.
 */
public class Set {

    private String key, message, path;

    /**
     * Instantiates a new Set.
     *
     * @param key     the key
     * @param message the message
     * @param path    the path
     */
    public Set(String key, String message, String path) {
        this.key = key;
        this.message = message;
        this.path = path;
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
     * Sets key.
     *
     * @param newKey the key
     */
    public void setKey(String newKey) {
        this.key = newKey;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param newMessage the message
     */
    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param newPath the path
     */
    public void setPath(String newPath) {
        this.path = newPath;
    }

}
