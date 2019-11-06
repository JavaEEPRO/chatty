package si.inspirited.persistence.model;

import lombok.*;
import si.inspirited.web.util.UserUtil;

import java.util.Map;

@Setter
@Getter
public class User {

    public User() {}

    public String name;

    User opponent;

    Map<User, Message> history;

    //
    boolean isNew() {
        return name == null;
    }

    public String setDefaultName() {
        return this.name = UserUtil.generateUserName();
    }
}
