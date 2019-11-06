package si.inspirited.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MessageRepository implements IMessageRepository {

    private MessageRepository() {
        this.postedMessages = new ConcurrentHashMap<>(10);
    }

    private ConcurrentHashMap<String, Message> postedMessages;

    @Override
    public Message addNewMessage(String content, String userName) {
        Message res = new Message(content, userName);
        this.postedMessages.put(res.id, res);       //this id should be inserted to user.history
        return res;
    }

    @Override
    public Map<String, Message> getAllMessages() {
        return this.postedMessages;
    }
}
