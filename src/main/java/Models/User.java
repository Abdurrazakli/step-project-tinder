package Models;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private final UUID userID;
    private final String username;
    private final String password;
    private final Gender gender;
    private final String imageURL;
}
