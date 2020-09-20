package service;

import dao.CollectionMessageDao;
import model.Message;

import java.util.ArrayList;
import java.util.Optional;

public class MessageService {

    private final CollectionMessageDao collectionMessageDao;

    public MessageService(CollectionMessageDao collectionMessageDao) {
        this.collectionMessageDao = collectionMessageDao;
    }

    public Optional<Boolean> save(Message message) {
        return this.collectionMessageDao.save(message);
    }

    public Optional<ArrayList<Message>> getUserMessages(int sendId, int receiveId) {
        return this.collectionMessageDao.getMessages(sendId, receiveId);
    }
}
