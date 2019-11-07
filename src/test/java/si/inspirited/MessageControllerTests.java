package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.service.IUserService;
import si.inspirited.web.controller.MessageController;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTests {

    @Autowired
    MessageController messageController;

    @Autowired
    IUserService userService;

    @Test
    public void sayMessageIfUserIsNotRegistered_ifSizeOfReceivedListWithMessagesIsUnchanged_thenCorrect() {
        userService.refreshUsersStorage();
        assertEquals(userService.getAllUsers().size(), 0);

        Optional<String> optionalName;
        optionalName = Optional.of("unregisteredUser");
        Integer sizeBeforeIntrusion = messageController.getSortedMessagesList(optionalName).size();
        messageController.postMessage("unregisteredUser", "alien' content");
        Integer sizeAfterIntrusion = messageController.getSortedMessagesList(optionalName).size();

        assertEquals(sizeBeforeIntrusion, sizeAfterIntrusion);
    }
}
