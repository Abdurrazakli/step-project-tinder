package Models;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class Message {
    private final String messageID;
    private final User From;
    private final User to;
    private final String message;
    private final LocalDateTime date;
}
