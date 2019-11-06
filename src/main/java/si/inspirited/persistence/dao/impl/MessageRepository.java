package si.inspirited.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MessageRepository implements IMessageRepository {

    public MessageRepository() {
        this.postedMessages = new ConcurrentHashMap<>(10);
    }

    private ConcurrentHashMap<String, Message> postedMessages;

    @Override
    public Message addNewMessage(String content) {
        Message res = new Message(content);
        this.postedMessages.put(content, res);
        return res;
    }

    @Override
    public Map<User, Message> getAllMessages() {
        return null;
    }
}
