package Models;

import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class Message {
    private String messageID;
    private String From;
    private String to;
    private String message;
    private LocalDateTime date;

    public Message(){

    }

    public Message(String from,String to,String message){
        this.From=from;
        this.to=to;
        this.message=message;
    }

    public String whenMessageWrote(){
        int minutes = (int) ChronoUnit.MINUTES.between(this.date, LocalDateTime.now());
        int hours = (int) ChronoUnit.HOURS.between(this.date,LocalDateTime.now());
        return (minutes < 59) ? String.valueOf(minutes).concat(" minute age") : hours < 23 ? String.valueOf(hours).concat(" hours ago") : String.valueOf(this.date.toLocalDate());
    }
}
