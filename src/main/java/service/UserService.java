package service;

import dao.CollectionUserDao;
import model.User;

import java.util.ArrayList;
import java.util.Optional;

public class UserService {

    private final CollectionUserDao collectionUserDao;


    public UserService(CollectionUserDao collectionUserDao) {
        this.collectionUserDao = collectionUserDao;
    }

    public Optional<Boolean> save(User user) {
        return this.collectionUserDao.save(user);
    }

    public Optional<ArrayList<User>> getUsers() {
        return this.collectionUserDao.getUsers();
    }

    public Optional<User> getUser(int id) {
        return this.collectionUserDao.getUser(id);
    }

    public Optional<User> updateUser(User user) {
        return this.collectionUserDao.updateUser(user);
    }
}
