package org.adilEfqan.tinder.Services;

import lombok.extern.log4j.Log4j2;
import org.adilEfqan.tinder.DAO.UserMapper;
import org.adilEfqan.tinder.Models.User;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.Optional;
@Log4j2
public class UserService {
    private final SqlSession session;
    UserMapper dao;
    public UserService(SqlSession session) {
        dao =session.getMapper(UserMapper.class);
        this.session=session;
    }

    public Optional<String> authenticateUser(String username, String password) {
        Optional<User> user = Optional.ofNullable(dao.getBy(username));
        log.info(user);
        if (user.isPresent() && user.get().getPassword().equals(password)){
            User userUpdated = user.get();

            userUpdated.setLastLogin(LocalDateTime.now());
            log.info(userUpdated);
            dao.update(userUpdated);
            session.commit();
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

    public Optional<User> getUserByID(String id){
        return Optional.ofNullable(dao.getById(id));
    }

    public Optional<User> getANewLove(String userid) {
        return Optional.ofNullable(dao.getUserOneByOne(userid));
    }

}
