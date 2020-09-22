package dao;

import model.Message;
import sql.Conn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

public class CollectionAuthDao implements AuthDao {

    private final String CREATE = "INSERT INTO authentications(id, email, password, id) VALUES (DEFAULT, ?, ?, ?) RETURNING id;";
    private final String CHECK = "SELECT * FROM authentications WHERE (email = ? AND password = ?);";
    private final String CHECK_EMAIL = "SELECT * FROM authentications WHERE email = ?;";
    private final String CHECK_USER = "SELECT authentications.\"user\" FROM authentications WHERE id = ?;";
    private final String COMBINE = "UPDATE authentications SET \"user\" = ? WHERE id = ? RETURNING \"user\";";

    @Override
    public Optional<Integer> create(String email, String password) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(CREATE);
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.setInt(3, -1);
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
    public Optional<Integer> check(String email, String password) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(CHECK);
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet resultSet = stmt.executeQuery();
                int userId = 0;
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                }
                return Optional.of(userId);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }

    public Optional<Boolean> checkEmail(String email) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL);
                stmt.setString(1, email);
                ResultSet resultSet = stmt.executeQuery();
                int userId = 0;
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                }
                return Optional.of(userId != 0);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<Integer> checkUser(int id) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(CHECK_USER);
                stmt.setInt(1, id);
                ResultSet resultSet = stmt.executeQuery();
                int userId = 0;
                if (resultSet.next()) {
                    userId = resultSet.getInt("user");
                }
                return Optional.of(userId);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<Boolean> combine(int authId, int userId) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(COMBINE);
                stmt.setInt(1, userId);
                stmt.setInt(2, authId);
                stmt.execute();
                ResultSet rs = stmt.getResultSet();
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                return Optional.of(userId == id);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }
}
