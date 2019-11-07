package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.model.User;
import si.inspirited.web.controller.UserController;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    UserController userController;

    @Test
    public void populateUserStorageWithUsers_whenReturnsListWithEqualNumberOfUsers_thenCorrect() {
        Optional<String> optionalName = Optional.of("AnyUser");
        Optional<String> optionalEmpty = Optional.of("");
        for (int i = 0; i < 12; i++) {
            userController.initNewUser(optionalName);
        }
        List<User> res = userController.getAllLoggedIn(optionalEmpty);

        assertEquals(res.size(), 12);
    }
}
