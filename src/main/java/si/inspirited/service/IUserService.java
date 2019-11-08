package si.inspirited.service;

import si.inspirited.persistence.model.User;

import java.util.Map;

public interface IUserService {

    User addNewUser(String name);

    User getUserByUsername(String name);

    boolean detachUser(String name);

    Map<String, User> getAllUsers();

    void refreshUsersStorage();

    void addHistoryEntry(String userName, String messageId);

    void setInterlocutor(String userName, String interlocutor);
}
