package com.fs11.step.tinder.service;

import com.fs11.step.tinder.model.Message;

public class MessageSQLStorage implements Storage<Message> {
    @Override
    public Iterable<Message> get() {
        return null;
    }

    @Override
    public void save(Message message) {

    }
}
