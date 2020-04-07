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
        return message;
    }


    public String getTimeLineMessage() {
        return user_id +"-"+ getMessage() + "( " + durationMessage(System.currentTimeMillis() - time ) +" )";
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

    public String durationMessage(long duration )
    {
        if(duration < 60 * 1000)
                return (duration/1000) +" seconds ago";
        else if(duration < 60 * 60 * 1000 )
                return (duration/(60 * 1000) +" minutes ago");
         else if(duration < 60 * 60 * 1000 )
               return (duration/(60 * 60 * 24 * 1000) +" hours ago");

         return "....long time back";

    }

}
