package service;

import model.Message;

public class MessageSQLStorage implements Storage<Message> {
    @Override
    public Iterable<Message> get() {
        return null;
    }

    @Override
    public void save(Message message) {

    }
}
