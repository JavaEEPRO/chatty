package si.inspirited.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IMessageService;

import java.util.List;
import java.util.Map;

@Service
public class MessageService implements IMessageService {

    @Autowired
    IMessageRepository messageRepository;

    @Override
    public Message addNewMessage(String userName, String content) {
        return messageRepository.addNewMessage(userName, content);
    }

    @Override
    public Message addNewMessage(String userName, String interlocutor, String content) {
        return messageRepository.addNewMessage(userName, interlocutor, content);
    }

    @Override
    public Map<String, Message> getAllMessages() {
        return messageRepository.getAllMessages();
    }

    @Override
    public List<Message> getAllSortedMessages() {
        return messageRepository.getAllSortedMessages();
    }

    @Override
    public void refreshMessagesStorage() {
        messageRepository.refreshMessagesStorage();
    }

    @Override
    public Message getMessageById(String id) {
        return messageRepository.getMessageById(id);
    }
}