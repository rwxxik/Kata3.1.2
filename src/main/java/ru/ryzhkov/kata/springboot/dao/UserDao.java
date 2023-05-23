package ru.ryzhkov.kata.springboot.dao;


import ru.ryzhkov.kata.springboot.models.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getAllUsers();

    User getUser(int id);

    void updateUser(int id, User updatedUser);

    void removeUser(int id);

}
