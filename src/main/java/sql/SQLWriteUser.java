package sql;

import model.User;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLWriteUser {
    public static void main(String[] args) {
        User user = new User("TestName1", "ImageSRC2");
        user.addLike(4);
        user.addLike(5);
        user.addLike(6);

        Conn.get().ifPresent(conn -> {
                    try {
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO users(id, name, image, likes) VALUES (DEFAULT, ?, ?, ?);"
                        );
                        stmt.setString(1, user.getName());
                        stmt.setString(2, user.getImage());
                        stmt.setArray(3, conn.createArrayOf("integer", user.getLikes().toArray()));
                        stmt.execute();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
    }
}
