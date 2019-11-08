package si.inspirited.persistence.dao;

import si.inspirited.persistence.model.User;

import java.util.Map;

public interface IUserRepository {

    User addNewUser(String name);

    User getUserByUsername(String name);

    Map<String, User> getAllUsers();

    void refreshUsersStorage();

    boolean removeUser(String name);

    void addHistoryEntry(String userName, String messageId);

    void setInterlocutor(String userName, String interlocutorsName);
}
