package si.inspirited.service;

import si.inspirited.persistence.model.User;

import java.util.Map;

public interface IUserService {

    Map<String, User> addNewUser(String name);

    boolean detachUser(String name);

    Map<String, User> getAllUsers();
}
