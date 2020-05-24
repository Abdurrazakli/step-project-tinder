package Models;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LikeDislike {
    private String currentUserID;
    private String toUserID;
    private boolean isLiked;
}
