public class PushChannel implements Channel {

    @Override
    public String send(String message) {
        return "PUSH: [Notification] " + message;
    }
}