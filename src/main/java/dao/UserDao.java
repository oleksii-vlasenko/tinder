package dao;

import model.User;

import java.util.Optional;

public interface UserDao {

    Iterable<User> getUsers();
    Optional<User> getUser(int id);
    boolean deleteUser(User user);
    Optional<User> deleteUser(int id);
    boolean saveUser(User user);
    Optional<Integer> getSize();

    boolean load();
}
