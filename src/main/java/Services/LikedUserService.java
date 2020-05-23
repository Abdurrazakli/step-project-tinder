package Services;

import DAO.UserMapper;
import Models.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LikedUserService {
    private final UserMapper mapper;
    public LikedUserService(SqlSession session) {
        this.mapper = session.getMapper(UserMapper.class);
    }

    public List<User> getAllLikedUsers(String userID){
        return mapper.getLikedUser(userID);
    }
}
