package si.inspirited.persistence.model;

import lombok.*;
import si.inspirited.web.util.UserUtil;

import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class User {

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String name;

    User opponent;

    Map<User, String> history;

    //
    boolean isNew() {
        return name == null;
    }

    public String setDefaultName() {
        return this.name = UserUtil.generateUserName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
