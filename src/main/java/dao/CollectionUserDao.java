package dao;

import model.User;

import java.util.ArrayList;
import java.util.Optional;

public class CollectionUserDao implements UserDao{

    private static final ArrayList<User> users = new ArrayList<>();

    @Override
    public Iterable<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> getUser(int id) {
        return Optional.of(users.get(id));
    }

    @Override
    public boolean deleteUser(User user) {
        return users.remove(user);
    }

    @Override
    public Optional<User> deleteUser(int id) {
        return Optional.of(users.remove(id));
    }

    @Override
    public boolean saveUser(User user) {
        return users.add(user);
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public Optional<Integer> getSize() {
        return Optional.of(users.size());
    }
}
