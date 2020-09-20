package dao;

import model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserDao {

    Optional<Boolean> save(User user);

    Optional<ArrayList<User>> getUsers();
    Optional<User> getUser(int id);

    Optional<User> updateUser(User user);
}
