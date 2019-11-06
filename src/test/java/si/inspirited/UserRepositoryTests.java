package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.dao.IUserRepository;
import si.inspirited.persistence.dao.impl.UserRepository;
import si.inspirited.persistence.model.User;
import java.util.Map;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@RestClientTest(UserRepository.class)
public class UserRepositoryTests {

    @Autowired
    IUserRepository userRepository;

    @Test
    public void addNewUser_whenReturnsMapWithOneExpectedUser_thenCorrect() {
        User user = new User();
        String name = user.setDefaultName();
        Map<String, User> res = userRepository.addNewUser(name);
        User userReturned = res.get(name);

        assertEquals(res.size(), 1);
        assertEquals(user, userReturned);
    }
}
