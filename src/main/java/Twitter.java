import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Twitter {

    private static HashMap<String, User> users;
    private static HashMap<String, ArrayList<Message>> messages;
    private static HashMap<String, HashSet<String>> following;

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static HashMap<String, ArrayList<Message>> getMessages() {
        return messages;
    }

    public static HashMap<String, HashSet<String>> getFollowing() {
        return following;
    }


    public Twitter() {
        users = new HashMap<>();
        messages = new HashMap<>();
        following = new HashMap<>();
    }


    public static String publish(String token, String userName, String message) {
        // if(validate(user_id,token))
        UUID uuid = UUID.randomUUID();
        Message msg = new Message(uuid.toString(), message, LocalDateTime.now(), userName);
        if (messages.get(userName).add(msg))
            return uuid.toString();
        return null;
    }

    public static ArrayList<Message> viewTimeline(String token, String userName) {
        // if(validate(userName,token))
        PriorityQueue<Message> queue = new PriorityQueue<Message>((a, b) -> a.getTime().compareTo(b.getTime()));
        queue.addAll(following.get(userName).stream().map(user -> messages.get(user)).flatMap(l -> l.stream()).collect(Collectors.toList()));
        return new ArrayList(Arrays.asList(queue.toArray()));
    }

    public static boolean followUser(String token, String userName, String userToFollow) {
        // if(validate(userName,token))
        return following.get(userName).add(userToFollow);
    }

    public static boolean validate(String _id, String token) {
        return true;
    }

    public static synchronized boolean AddUser(String userName, String passwd) {
        if (users.get(userName) != null) return false;
        User user = new User(userName, passwd);
        users.put(userName, user);
        HashSet<String> followers = new HashSet<String>();
        messages.put(userName, new ArrayList<Message>(0));
        followers.add(userName);
        following.put(userName, followers);
        return true;
    }

    public static User getUser(String token, String master_user_id, String userName) {
        // if(validate(userName,token))
        return users.get(userName);
    }

    public static ArrayList<Message> getMessages(String token, String userName) {
        return Twitter.messages.get(userName);
    }

    public static Message getMessage(String token, String userName, String msg_id) {
        return messages.get(userName).stream().filter(msg -> msg.getId().equals(msg_id)).findFirst().orElse(null);
    }

    public static List<User> getFollowers(String token, String userName) {
        return following.get(userName).stream().filter(user -> !user.equals(userName)).map(user -> users.get(user)).collect(Collectors.toList());
    }
}


