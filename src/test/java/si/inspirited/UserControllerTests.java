package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IUserService;
import si.inspirited.web.controller.UserController;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    UserController userController;

    @Autowired
    IUserService userService;

    @Test
    public void populateUserStorageWithUsers_whenReturnsListWithEqualNumberOfUsers_thenCorrect() {
        populateUserStorage();
        Optional<String> optionalEmpty = Optional.of("");
        List<User> res = userController.getAllLoggedIn(optionalEmpty);

        assertEquals(res.size(), 12);
    }

    @Test
    public void populateUserStorage_andGetWithNameParameterPassed_whenReturnsListWithExpectedSize_thenCorrect() {
        populateUserStorage();

        Optional<String> optionalTargetName = Optional.of("AnyUser");
        List<User> res = userController.getAllLoggedIn(optionalTargetName);

        assertEquals(res.size(), 11);
    }

    private void populateUserStorage() {
        Optional<String> optionalName = Optional.of("AnyUser");
        for (int i = 0; i < 12; i++) {
            userController.initNewUser(optionalName);
        }
    }
}
