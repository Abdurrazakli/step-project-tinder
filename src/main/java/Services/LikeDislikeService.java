package Services;


import DAO.LikeMapper;
import Models.LikeDislike;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

@Log4j2
public class LikeDislikeService {
    private final String LIKE = "like";
    private final String DISLIKE = "dislike";

    private final SqlSession session;
    private final LikeMapper likeMapper;

    public LikeDislikeService(SqlSession session) {
        this.session = session;
        this.likeMapper = session.getMapper(LikeMapper.class);
    }

    public void actBetween(String userId, String toUserID, String action) {
        boolean isLiked = isLike(action);
        LikeDislike likeDislike = new LikeDislike(userId, toUserID, isLiked);
        log.debug(likeDislike);
        likeMapper.insertToLike(likeDislike);
    }

    private boolean isLike(String action) {
        if(action.equals(LIKE)) return true;
        if(action.equals(DISLIKE)) return false;
        log.debug("Undefined action!");
        throw new RuntimeException("Undefined action!");
    }
}
