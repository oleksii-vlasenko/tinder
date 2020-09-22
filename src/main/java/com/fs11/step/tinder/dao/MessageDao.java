package com.fs11.step.tinder.dao;

import com.fs11.step.tinder.model.Message;

import java.util.ArrayList;
import java.util.Optional;

public interface MessageDao {
    Optional<Boolean> save(Message message);

    Optional<ArrayList<Message>> getMessages(String req);

    Optional<Message> updateMessage(Message message);
}
