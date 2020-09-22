package controller;

import dao.CollectionUserDao;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserController {

    private final CollectionUserDao collectionUserDao;

    public UserController(CollectionUserDao collectionUserDao) {
        this.collectionUserDao = collectionUserDao;
    }

    public Optional<Integer> save(User user) {
        return this.collectionUserDao.save(user);
    }

    public Optional<ArrayList<User>> getAll() {
        return this.collectionUserDao.getUsers();
    }

    public Optional<User> get(int id) {
        return this.collectionUserDao.getUser(id);
    }

    public Optional<User> update(User user) {
        return this.collectionUserDao.updateUser(user);
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

    public boolean addLike(int userId, int id) {
        return this.get(userId).map(u -> {
            u.addLike(id);
            this.update(u);
            return u;
        }).isPresent();
    }

    public boolean addDislike(int userId, int id) {
        return this.get(userId).map(u -> {
            u.addDislike(id);
            this.update(u);
            return u;
        }).isPresent();
    }

    public Optional<List<User>> getLiked(int id) {
        return this.getAll().map(l ->
                l.stream()
                        .filter(y -> y.getId() != id)
                        .filter(u ->
                                this.get(id).map(ul -> ul
                                        .getLikes()
                                        .contains(u.getId()))
                                        .orElse(false))
                        .collect(Collectors.toList()));
    }

    public Optional<Integer> getSize(int id) {
        return this.getActual(id)
                .map(List::size);
    }
}
