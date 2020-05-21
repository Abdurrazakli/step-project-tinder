package Services;

import DAO.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.UUID;

public class UserService {
    UserMapper dao;
    public UserService(SqlSession session) {
        dao =session.getMapper(UserMapper.class);
    }


    public boolean ifUserExists(UUID currentUser) {
        return false;
    }
}
