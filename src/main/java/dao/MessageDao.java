package dao;

import model.Message;
import model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface MessageDao {
    Optional<Boolean> save(Message message);

    Optional<ArrayList<Message>> getMessages(String req);

    Optional<Message> updateMessage(Message message);
}
