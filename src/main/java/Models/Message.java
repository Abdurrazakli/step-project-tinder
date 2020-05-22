package Models;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Message {
    private final UUID messageID;
    private final User From;
    private final User to;
    private final String message;
    private final ZonedDateTime date;

    public Message(UUID messageID, User from, User to, String message, ZonedDateTime date) {
        this.messageID = messageID;
        From = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }
}
