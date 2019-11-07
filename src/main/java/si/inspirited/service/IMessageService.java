package si.inspirited.service;

import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;
import java.util.Map;

public interface IMessageService {

    Map<String, Message> addNewMessage(String content, String userName);

    Map<String, Message> getAllMessages();
}
