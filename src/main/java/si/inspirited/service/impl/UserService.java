package si.inspirited.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.inspirited.persistence.dao.IUserRepository;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IUserService;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public User addNewUser(String name) {
        return userRepository.addNewUser(name);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.getUserByUsername(name);
    }

    @Override
    public boolean detachUser(String name) {
        return userRepository.removeUser(name);
    }

    @Override
    public Map<String, User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void refreshUsersStorage() {
        userRepository.refreshUsersStorage();
    }

    @Override
    public void addHistoryEntry(String userName, String messageId) {
        userRepository.addHistoryEntry(userName, messageId);
    }

    @Override
    public void setInterlocutor(String userName, String interlocutor) {
        userRepository.setInterlocutor(userName, interlocutor);
    }
}
