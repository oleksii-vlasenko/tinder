package service;

import model.User;
import sql.Conn;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSQLStorage implements Storage<User> {
    @Override
    public Iterable<User> get() {
        return null;
    }

    @Override
    public void save(User user) {
//        Conn.get().ifPresent(conn -> {
//            try {
//                PreparedStatement stmt = conn.prepareStatement(
//                        "INSERT INTO users(a, b, op, r) VALUES (?, ?, ?, ?);"
//                );
//                stmt.setInt(1, op.a);
//                stmt.setInt(2, op.b);
//                stmt.setString(3, op.op);
//                stmt.setInt(4, op.r);
//                stmt.execute();
//            } catch(SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
    }
}
