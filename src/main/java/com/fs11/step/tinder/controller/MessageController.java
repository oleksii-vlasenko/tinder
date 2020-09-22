package com.fs11.step.tinder.controller;

import com.fs11.step.tinder.dao.SQLMessageDao;
import com.fs11.step.tinder.model.Message;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageController {

    private final SQLMessageDao messageDao;

    public MessageController(SQLMessageDao SQLMessageDao) {
        this.messageDao = SQLMessageDao;
    }

    public Optional<Boolean> save(Message message) {
        return this.messageDao.save(message);
    }

    public Optional<List<Message>> getUserMessages(int sendId, int receiveId) {
        return this.messageDao.getMessages(sendId, receiveId)
                .map(l ->
                        l.stream()
                                .peek(m -> {
                                    m.setMarker(m.getSend() == sendId ? -1 : 0);
                                })
                                .sorted((a, b) ->
                                        a.getDate() != b.getDate()
                                                ? (int) (a.getDate() - b.getDate())
                                                : (a.getId() - b.getId()))
                                .collect(Collectors.toList()));
    }
}
