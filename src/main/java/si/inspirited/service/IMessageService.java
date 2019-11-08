package si.inspirited.service;

import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;

import java.util.List;
import java.util.Map;

public interface IMessageService {

    Message addNewMessage(String userName, String content);

    Map<String, Message> getAllMessages();

    List<Message> getAllSortedMessages();

    void refreshMessagesStorage();
}
