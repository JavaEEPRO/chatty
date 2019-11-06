package si.inspirited.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IMessageService;
import java.util.Map;

@Service
public class MessageService implements IMessageService {

    @Autowired
    IMessageRepository messageRepository;

    @Override
    public Message addNewMessage(String content) {
        return null;
    }

    @Override
    public Map<User, Message> getAllMessages() {
        return null;
    }
}