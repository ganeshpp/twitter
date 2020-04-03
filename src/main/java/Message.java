import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Message {
    private String id;
    private String message;
    private LocalDateTime time;
    private String user_id;

    public Message(String id, String message, LocalDateTime time, String user_id) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;  // + ( long val = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - time.toEpochSecond( ZoneOffset.UTC););
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getUser_id() {
        return user_id;
    }

}
