package com.example.springBootdemo2.dao;

import com.example.springBootdemo2.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    User getUserId(long id);

    User removeUser(long id);

    User getByName(String username);
}
