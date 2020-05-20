package DAO;

import Models.User;

import java.util.Collection;
import java.util.UUID;

public class UserDao implements UserMapper {
    private final UserMapper userMapper;
    public UserDao(UserMapper userMapper) {
        this.userMapper=userMapper;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void update(User student) {

    }

    @Override
    public void deleteByID(UUID id) {

    }

    @Override
    public void insert(User student) {

    }
}
