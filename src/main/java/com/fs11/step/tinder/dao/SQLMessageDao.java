package com.fs11.step.tinder.dao;

import com.fs11.step.tinder.model.Message;
import com.fs11.step.tinder.sql.Conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class SQLMessageDao implements MessageDao {

    static final String GET_ALL_MESSAGES = "SELECT * FROM messages";
    static final String SQL_ADD_MESSAGE = "INSERT INTO messages(id, send, receive, text, time) VALUES (DEFAULT, ?, ?, ?, ?);";
    static final String FORMAT_GET_USER_MESSAGES = "SELECT * FROM messages WHERE (send = %d AND receive = %d) OR (send = %d AND receive = %d);";
    static final String UPDATE_MESSAGE = "UPDATE users SET name = ?, image = ?, likes = ?, dislikes = ? where id = ?;";

    public Optional<ArrayList<Message>> getMessages(int id, int resp) {
        String req = String.format(FORMAT_GET_USER_MESSAGES, id, resp, resp, id);
        return getMessages(req);
    }

    @Override
    public Optional<Boolean> save(Message message) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(SQL_ADD_MESSAGE);
                stmt.setInt(1, message.getSend());
                stmt.setInt(2, message.getReceive());
                stmt.setString(3, message.getText());
                stmt.setTimestamp(4, new Timestamp(message.getDate()));
                stmt.execute();
                return Optional.of(true);
            } catch (SQLException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<ArrayList<Message>> getMessages(String req) {
        return Conn.get().flatMap(conn -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(req);
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Message> messages = new ArrayList<>();
                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    int send = resultSet.getInt("send");
                    int receive = resultSet.getInt("receive");
                    String text = resultSet.getString("text");
                    long time = resultSet.getDate("time").getTime();

                    messages.add(new Message(id, send, receive, text, time));
                }
                return Optional.of(messages);
            } catch (SQLException ex) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<Message> updateMessage(Message message) {
        return Optional.empty();
    }
}
