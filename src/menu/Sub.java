package menu;

/**
 * The type Sub.
 */
public class Sub {

    private String key, message;
    private Menu<Task<Void>> subMenu;

    /**
     * Instantiates a new Sub.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    public Sub(String key, String message, Menu<Task<Void>> subMenu) {
        this.key = key;
        this.message = message;
        this.subMenu = subMenu;
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
     * Gets sub menu.
     *
     * @return the sub menu
     */
    public Menu<Task<Void>> getSubMenu() {
        return subMenu;
    }
}
