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
    public Map<String, User> addNewUser(String name) {
        //1. if map contains received name, then generate another automatically
        if (this.loggedUsers.containsKey(name)) {
            name = UserUtil.generateUserName();
        }
        //2. add user
        User newUser = new User(name);
        this.loggedUsers.put(name, newUser);
        return this.loggedUsers;
    }

    @Override
    public Map<String, User> getAllUsers() {
        return null;
    }
}
