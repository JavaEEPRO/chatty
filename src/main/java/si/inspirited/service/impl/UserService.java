package si.inspirited.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.inspirited.persistence.dao.IUserRepository;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IUserService;
import java.util.Map;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public Map<String, User> addNewUser(String name) {
        return null;
    }

    @Override
    public Map<String, User> getAllUsers() {
        return null;
    }
}