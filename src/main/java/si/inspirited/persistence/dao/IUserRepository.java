package si.inspirited.persistence.dao;

import si.inspirited.persistence.model.User;

import java.util.Map;

public interface IUserRepository {

    Map<String, User> addNewUser(String name);

    Map<String, User> getAllUsers();
}
