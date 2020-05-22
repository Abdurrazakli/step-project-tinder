package Services;

import DAO.UserMapper;
import Models.User;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    UserMapper dao;
    public UserService(SqlSession session) {
        dao =session.getMapper(UserMapper.class);
    }

    public Optional<UUID> authenticateUser(String username, String password) {
        Optional<User> user = dao.getBy(username);
        return  user.isPresent() && user.get().getPassword().equals(password) ? Optional.of(user.get().getUserID()) : Optional.empty();
    }

    public boolean authenticateAndRegisterUser(User newUser) {
        if(!checkUserAuthentication(newUser.getUsername())){
            registerUser(newUser);
            return true;
        }else return false;  // true user exists cant register
    }

    private boolean checkUserAuthentication(String newUserName) {
        return dao.getBy(newUserName).isPresent();
    }

    public void registerUser(User newUser) {
        dao.insert(newUser);
    }

    public Optional<User> getUserByID(UUID id){
        return dao.getById(id);
    }

    public LinkedList<User> getAllUsers() {
        return (LinkedList<User>)dao.getAll();
    }
}
