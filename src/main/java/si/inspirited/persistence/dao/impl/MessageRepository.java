package si.inspirited.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;

import java.util.Map;

@Repository
public class MessageRepository implements IMessageRepository {

    @Override
    public Message addNewMessage(String content) {
        return null;
    }

    @Override
    public Map<User, Message> getAllMessages() {
        return null;
    }
}
