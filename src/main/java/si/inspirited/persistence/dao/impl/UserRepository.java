package si.inspirited.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import si.inspirited.persistence.dao.IUserRepository;
import si.inspirited.persistence.model.User;

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
        return null;
    }

    @Override
    public Map<String, User> getAllUsers() {
        return null;
    }
}
