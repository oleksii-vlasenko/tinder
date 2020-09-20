package controller;

import dao.CollectionMessageDao;
import model.Message;
import service.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public Optional<Boolean> save(Message message) {
        return this.messageService.save(message);
    }

    public Optional<List<Message>> getUserMessages(int sendId, int receiveId) {
        return this.messageService.getUserMessages(sendId, receiveId)
                .map(l ->
                        l.stream().map(m -> {
                            m.setMarker(m.getSend() == sendId ? -1 : 0);
                            return m;
                        })
                                .sorted((a, b) -> (int) (a.getDate() - b.getDate()))
                                .collect(Collectors.toList()));
    }
}
