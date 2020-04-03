import java.util.ArrayList;

public class User {

    private String id;
    private String password;
    private String userName;
    private String secretToken;

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }


    public User(String userName, String password) {
        this.id = ""; //generate
        this.password = password;
        this.userName = userName;
        this.secretToken = ""; //generate for every login && update user object
    }

    public ArrayList<Message> viewTimeLine() {
        return Twitter.viewTimeline(secretToken, userName);
    }

    public boolean followUser(String userToFollow) {
        return Twitter.followUser(secretToken, userName, userToFollow);
    }

    public String publishMessage(String msg) {
        return Twitter.publish(secretToken, userName, msg);
    }

    public Message getMessage(String message_id) {
        return Twitter.getMessage(secretToken, userName, message_id);
    }

    public ArrayList<Message> getMessages() {
        return Twitter.getMessages(secretToken, userName);
    }

    public ArrayList<User> getfollowers() {
        return new ArrayList<>(Twitter.getFollowers(secretToken, userName));
    }
}
