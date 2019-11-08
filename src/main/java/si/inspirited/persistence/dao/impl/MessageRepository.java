package si.inspirited.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.model.Message;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MessageRepository implements IMessageRepository {

    private MessageRepository() {
        this.postedMessages = new ConcurrentHashMap<>(10);
    }

    private ConcurrentHashMap<String, Message> postedMessages;

    @Override
    public Message addNewMessage(String userName, String content) {
        Message res;
        if (content != null && !"".equals(content.trim()) && userName != null && !"".equals(userName.trim())) {
            res = new Message(userName, content);
            this.postedMessages.put(res.id, res);       //this id should be inserted to user.history
        }
        else {  res = new Message(); }
        return res;
    }

    @Override
    public Map<String, Message> getAllMessages() {
        return this.postedMessages;
    }

    @Override
    public List<Message> getAllSortedMessages() {

        Map<String, Message> unsorted = this.postedMessages;
        List<Message> toSort = new ArrayList<>(unsorted.values());
        List<Message> sorted = toSort.stream()
                                             .sorted(Comparator.comparing(Message::getPosted)) //comparator - how you want to sort it
                                             .collect(Collectors.toList()); //collector - what you want to collect it to
        return sorted;
    }

    @Override
    public Message getMessageById(String id) {
        if (id == null || "".equals(id.trim())) { return new Message(); }
        return this.postedMessages.get(id);
    }

    @Override
    public void refreshMessagesStorage() {
        this.postedMessages = new ConcurrentHashMap<>(10);
    }
}
