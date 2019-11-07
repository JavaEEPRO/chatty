package si.inspirited.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import si.inspirited.persistence.dao.IUserRepository;
import si.inspirited.persistence.model.User;
import si.inspirited.web.util.UserUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository implements IUserRepository {

    public UserRepository() {
        this.loggedUsers = new ConcurrentHashMap<>(10);
    }

    private ConcurrentHashMap<String, User> loggedUsers;


    @Override
    public User addNewUser(String name) {
        //1. if passed name is null or "", " ", or contains spec chars, or map contains the same name, as received name, then generate another name automatically
        String regex = "\\w+";
        if (name == null || "".equals(name.trim()) || !name.matches(regex) || this.loggedUsers.containsKey(name)) {
            name = UserUtil.generateUserName();
        }
        //2. add user
        User newUser = new User(name);
        this.loggedUsers.put(name, newUser);
        return newUser;
    }

    @Override
    public Map<String, User> getAllUsers() {
        return this.loggedUsers;
    }

    @Override
    public void refreshUsersStorage() {
        this.loggedUsers = new ConcurrentHashMap<>(10);
    }

    @Override
    public boolean removeUser(String name) {
        if (name == null || "".equals(name.trim())) { return false; }
        if (!this.loggedUsers.containsKey(name)) { return false; }
        this.loggedUsers.remove(name);
        return true;
    }

    @Override
    public void addHistoryEntry(String userName, String messageId) {
        if (userName != null && !"".equals(userName.trim()) && messageId != null && !"".equals(messageId.trim())) {
            User user = this.loggedUsers.get(userName);
            this.loggedUsers.remove(userName);
            user.history.add(messageId);
            this.loggedUsers.put(userName, user);
        }
    }
}
