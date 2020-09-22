package com.fs11.step.tinder.dao;

import com.fs11.step.tinder.model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserDao {

    Optional<Integer> save(User user);

    Optional<ArrayList<User>> getUsers();
    Optional<User> getUser(int id);

    Optional<User> updateUser(User user);
}
