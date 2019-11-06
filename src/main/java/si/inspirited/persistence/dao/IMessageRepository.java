package si.inspirited.persistence.dao;

import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;

import java.util.Map;

public interface IMessageRepository {

    Message addNewMessage(String content);

    Map<User, Message> getAllMessages();
}
