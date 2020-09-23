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
                "jdbc:postgresql://ec2-23-23-242-234.compute-1.amazonaws.com:5432/d71i2s271ho0re",
                "ctybnljlycbvrf",
                "36cfa5cced2e1bb72b23464dc7e80f766244732cf666ac7ea9595c7fb0c7ff89"
        );
    }
}
