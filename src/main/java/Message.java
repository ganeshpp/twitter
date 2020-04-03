import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Message {
    private String id;
    private String message;
    private long time;
    private String user_id;

    public Message(String id, String message, String user_id) {
        this.id = id;
        this.message = message;
        this.time = System.currentTimeMillis();
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;  // + ( long val = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - time.toEpochSecond( ZoneOffset.UTC););
    }


    public String getTimeLineMessage() {
        return message + "(" +( System.currentTimeMillis() - time ) +")";
    }

    public String getId() {
        return id;
    }

    public  long getTime() {
        return time;
    }

    public String getUser_id() {
        return user_id;
    }

}
