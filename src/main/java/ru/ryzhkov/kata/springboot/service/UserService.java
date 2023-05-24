package ru.ryzhkov.kata.springboot.service;

import ru.ryzhkov.kata.springboot.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    User getUser(int id);

    void updateUser(int id, User updatedUser);

    void removeUser(int id);
}
