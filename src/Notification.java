public abstract class Notification {

    protected String id;
    protected String message;
    protected Channel channel;

    public Notification(String id, String message, Channel channel) {
        this.id = id;
        this.message = message;
        this.channel = channel;
    }

    public abstract String execute();

    public void setImplementation(Channel channel) {
        this.channel = channel;
    }
    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}