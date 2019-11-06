package si.inspirited.persistence.dao;

import si.inspirited.persistence.model.Message;

import java.util.List;
import java.util.Map;

public interface IMessageRepository {

    Message addNewMessage(String content, String userName);

    Map<String, Message> getAllMessages();

    List<Message> getAllSortedMessages();
}
