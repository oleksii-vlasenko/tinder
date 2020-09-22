package com.fs11.step.tinder.controller;

import com.fs11.step.tinder.dao.SQLUserDao;
import com.fs11.step.tinder.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserController {

    private final SQLUserDao userDao;

    public UserController(SQLUserDao SQLUserDao) {
        this.userDao = SQLUserDao;
    }

    public Optional<Integer> save(User user) {
        return this.userDao.save(user);
    }

    public Optional<ArrayList<User>> getAll() {
        return this.userDao.getUsers();
    }

    public Optional<User> get(int id) {
        return this.userDao.getUser(id);
    }

    public Optional<User> update(User user) {
        return this.userDao.updateUser(user);
    }

    public Optional<List<User>> getActual(int id) {
        return this.getAll().map(l ->
                l.stream()
                        .filter(y -> y.getId() != id)
                        .filter(u ->
                                !(this.get(id).map(ul -> ul
                                        .getLikes()
                                        .contains(u.getId()))
                                        .orElse(false)
                                        || this.get(id).map(ud -> ud
                                        .getDislikes()
                                        .contains(u.getId()))
                                        .orElse(false)))
                        .collect(Collectors.toList()));
    }

    public void addLike(int userId, int id) {
        this.get(userId).map(u -> {
            u.addLike(id);
            this.update(u);
            return u;
        });
    }

    public void addDislike(int userId, int id) {
        this.get(userId).map(u -> {
            u.addDislike(id);
            this.update(u);
            return u;
        });
    }

    public List<User> getLiked(int id) {
        return this.getAll().map(l ->
                l.stream()
                        .filter(y -> y.getId() != id)
                        .filter(u ->
                                this.get(id).map(ul -> ul
                                        .getLikes()
                                        .contains(u.getId()))
                                        .orElse(false))
                        .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public Optional<Integer> getSize(int id) {
        return this.getActual(id)
                .map(List::size);
    }
}
