package sql;

import model.User;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;

public class SQLReadUser {
    public static void main(String[] args) {
        Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = stmt.executeQuery();
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
        }).ifPresent(al -> al.forEach(System.out::println));
    }
}
