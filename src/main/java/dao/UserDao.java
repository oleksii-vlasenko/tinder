package dao;

import model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserDao {

    ArrayList<User> getUsers();
    Optional<User> getUser(int id);
    boolean deleteUser(User user);
    Optional<User> deleteUser(int id);
    boolean saveUser(User user);
    int getSize();

    boolean load();
}
