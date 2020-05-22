package Services;

import DAO.UserMapper;
import Models.User;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final SqlSession session;
    UserMapper dao;
    public UserService(SqlSession session) {
        dao =session.getMapper(UserMapper.class);
        this.session=session;
    }

    public Optional<String> authenticateUser(String username, String password) {
        Optional<User> user = Optional.ofNullable(dao.getBy(username));
        System.out.println(user);
        if (user.isPresent() && user.get().getPassword().equals(password)){
            return Optional.of(user.get().getUserID());
        }else return Optional.empty();

    }


    public boolean authenticateAndRegisterUser(User newUser) {
        if(!checkUserAuthentication(newUser.getUsername())){
            registerUser(newUser);
            return true;
        }else return false;  // true user exists cant register
    }

    private boolean checkUserAuthentication(String newUserName) {
        return Optional.ofNullable(dao.getBy(newUserName)).isPresent();
    }

    public void registerUser(User newUser) {
        dao.insert(newUser);
        session.commit();
    }

    public Optional<User> getUserByID(UUID id){
        return Optional.ofNullable(dao.getById(id));
    }

    public User getANewLove(String userid) {
        return dao.getUserOneByOne(userid);
    }

}
