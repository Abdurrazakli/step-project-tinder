package org.adilEfqan.tinder.Models;

import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String userID;
    private String username;
    private String password;
    private Gender gender;
    private LocalDateTime lastLogin;
    private String imageURL;

    public User(){}
    public User(String userID,String username, String password, String gender,String imageURL){ //for data from db
        this(username, password, gender, imageURL);
        this.userID=userID;
    } // fetch
    public User(String username, String password, String gender,String imageURL){ //from user
        this(username, password, gender);
        this.imageURL=imageURL;
    } //image
    public User(String username, String password, String gender){ // from user with default image
        super();
        this.username=username;
        this.password=password;
        this.gender=Gender.valueOf(gender);
    } //image not

    public String slug(){
        return this.userID;
    }
    public String prettyLastLogin(){
        return String.valueOf(this.lastLogin.toLocalDate());
    }
    public int daysAgo(){
        return Period.between(this.lastLogin.toLocalDate(),LocalDateTime.now().toLocalDate()).getDays();
    }
}
