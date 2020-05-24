package Models;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LikeDislike {
    private final String currentUserID;
    private final String toUserID;
    private final boolean isLiked;
}
