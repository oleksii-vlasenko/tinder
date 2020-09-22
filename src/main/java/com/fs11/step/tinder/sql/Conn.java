package com.fs11.step.tinder.sql;

import java.sql.*;
import java.util.Optional;

public class Conn {

    public static Optional<Connection> get(String url, String username, String password) {
        try {
            return Optional.of(DriverManager.getConnection(url, username, password));
        } catch (SQLException ex) {
            return Optional.empty();
        }
    }

    public static Optional<Connection> get() {
        return get(
                "jdbc:postgresql://localhost:5432/step",
                "postgres",
                "admin"
        );
    }
}
