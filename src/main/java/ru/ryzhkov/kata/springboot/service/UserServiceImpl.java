package ru.ryzhkov.kata.springboot.service;

import org.springframework.stereotype.Service;
import ru.ryzhkov.kata.springboot.dao.UserDao;
import ru.ryzhkov.kata.springboot.model.User;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        userDao.updateUser(id, updatedUser);
    }

    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }
}
