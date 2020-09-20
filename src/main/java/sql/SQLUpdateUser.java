package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class SQLUpdateUser {
    public static void main(String[] args) {
        Conn.get().ifPresent(conn -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE users SET name = ? where id = ?;");
                preparedStatement.setString(1, "Unknown");
                preparedStatement.setInt(2, 1);
                preparedStatement.execute();
            } catch (SQLException throwables) {
                Optional.empty();
            }
        });
    }
}
