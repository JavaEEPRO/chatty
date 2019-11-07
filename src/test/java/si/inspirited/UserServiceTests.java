package si.inspirited;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.dao.impl.MessageRepository;
import si.inspirited.persistence.dao.impl.UserRepository;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IUserService;
import si.inspirited.service.impl.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void joinedNewUser_whenReceivedMapContainsExpectedUser_thenCorrect() {
        User user = joinUser();
        Map<String, User> res = userService.getAllUsers();
        List<User> mapToList = new ArrayList<>(res.values());
        User receivedUser = mapToList.get(0);

        assertEquals(user, receivedUser);
    }

    @Test
    public void detachingExistingUser_whenReceivedMapNotContainsAnymoreGivenUser_thenCorrect() {
        for (int i = 0; i < 12; i++) {
            User user = joinUser("AnyUser" + i);
        }
        Integer mapSizeBeforeDetaching = userService.getAllUsers().size();
        boolean isDetached = userService.detachUser("AnyUser11");
        Integer mapSizeAfterDetaching = userService.getAllUsers().size();
        Map<String, User> res = userService.getAllUsers();

        assertFalse(res.containsKey("AnyUser11"));
        assertTrue(isDetached);
        assertNotEquals(mapSizeBeforeDetaching, mapSizeAfterDetaching);


    }

    private User joinUser() {
        User user = new User("NoNamedUser");
        userService.addNewUser("NoNamedUser");
        return user;
    }

    private User joinUser(String name) {
        User user = new User(name);
        userService.addNewUser(name);
        return user;
    }

}
