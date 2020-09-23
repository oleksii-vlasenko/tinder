package com.fs11.step.tinder.dao;

import com.fs11.step.tinder.model.User;
import com.fs11.step.tinder.sql.Conn;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class SQLUserDao implements UserDao {

    static final String GET_ALL_USERS = "SELECT * FROM users";
    static final String ADD_USER = "INSERT INTO users(id, name, image, likes, dislikes) VALUES (DEFAULT, ?, ?, ?, ?) RETURNING id;";
    static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    static final String UPDATE_USER = "UPDATE users SET name = ?, image = ?, likes = ?, dislikes = ? WHERE id = ?;";

    @Override
    public Optional<Integer> save(User user) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(ADD_USER);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getImage());
                stmt.setArray(3, conn.createArrayOf("integer", user.getLikes().toArray()));
                stmt.setArray(4, conn.createArrayOf("integer", user.getDislikes().toArray()));
                stmt.execute();
                ResultSet rs = stmt.getResultSet();
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                return Optional.of(id);
            } catch (SQLException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<ArrayList<User>> getUsers() {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_USERS);
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String image = resultSet.getString("image");
                    Array likesArr = resultSet.getArray("likes");

                    Set<Integer> likes = stream((Object[]) likesArr.getArray())
                            .map(Object::toString)
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());

                    Array dislikesArr = resultSet.getArray("dislikes");
                    Set<Integer> dislikes = stream((Object[]) dislikesArr.getArray())
                            .map(Object::toString)
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());

                    User user = new User(name, image, likes, dislikes);
                    user.setId(id);

                    users.add(user);
                }
                return Optional.of(users);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<User> getUser(int userId) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_ID);
                preparedStatement.setInt(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                int id;
                String name;
                String image;
                Set<Integer> likes;
                Set<Integer> dislikes;
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    name = resultSet.getString("name");
                    image = resultSet.getString("image");

                    Array likesArr = resultSet.getArray("likes");
                    likes = stream((Object[]) likesArr.getArray())
                            .map(Object::toString)
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());

                    Array dislikesArr = resultSet.getArray("dislikes");
                    dislikes = stream((Object[]) dislikesArr.getArray())
                            .map(Object::toString)
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());
                    User user = new User(name, image, likes, dislikes);
                    user.setId(id);
                    return Optional.of(user);
                }
                return Optional.empty();
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }


    @Override
    public Optional<User> updateUser(User user) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getImage());
                preparedStatement.setArray(3, conn.createArrayOf("integer", user.getLikes().toArray()));
                preparedStatement.setArray(4, conn.createArrayOf("integer", user.getDislikes().toArray()));
                preparedStatement.setInt(5, (int) user.getId());
                preparedStatement.execute();
                return Optional.of(user);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }
}
