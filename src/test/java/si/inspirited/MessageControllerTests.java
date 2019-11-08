package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IMessageService;
import si.inspirited.service.IUserService;
import si.inspirited.web.controller.MessageController;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTests {

    @Autowired
    MessageController messageController;

    @Autowired
    IUserService userService;

    @Autowired
    IMessageService messageService;

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

    @Test
    public void sayMessage_whenUserHistoryListCapacityIsChanged_thenCorrect() {
        User user = joinUser();
        String content = "regular test content";
        messageController.postMessage(user.name, content);
        User refreshedEntity = userService.getAllUsers().get(user.name);

        assertEquals(refreshedEntity.history.size(), 1);
    }

    @Test
    public void sayAddressedMessage_whenMessagesListReceivedWithExpectedMessage_thenCorrect() {
        User user = joinUser();
        User interlocutor = joinUser();
        String content = "hello test";
        Integer sizeBeforeMessagePosted = messageController.getSortedMessagesList(Optional.of(user.name)).size();
        messageController.addressedMessage(user.name, interlocutor.name, content);
        Integer sizeAfterMessagePosted = messageController.getSortedMessagesList(Optional.of(user.name)).size();

        user = userService.getUserByUsername(user.name);    //refreshing state
        String messageId = user.history.size() > 0 ? user.history.get(0) : "";
        Message message = messageService.getMessageById(messageId);

        assertNotEquals(sizeBeforeMessagePosted, sizeAfterMessagePosted);
        assertEquals(message.content, content);
        assertEquals(message.interlocutorsName, interlocutor.name);
    }

    @Test
    public void sayAddressedMessageIfUsersAreNotRegistered_whenReceivedMessagesListSizeIsUnchanged_thenCorrect() {
        String content = "message from unauthorized user";
        String unauthorizedUserName = "OuterUser";
        String unauthorizedInterlocutorsName = "OuterInterlocutor";
        Integer sizeBeforeMessagePosted = messageController.getSortedMessagesList(Optional.of("")).size();
        messageController.addressedMessage(unauthorizedUserName, unauthorizedInterlocutorsName, content);
        Integer sizeAfterMessagePosted = messageController.getSortedMessagesList(Optional.of("")).size();

        assertEquals(sizeBeforeMessagePosted, sizeAfterMessagePosted);
    }

    @Test
    public void sayAddressedMessageToSenderHimself_whenReceivedMessagesListIsUnchanged_thenCorrect() {
        String content = "recursive message";
        User sender = joinUser();
        Integer sizeBeforeMessagePosted = messageController.getSortedMessagesList(Optional.of("")).size();
        messageController.addressedMessage(sender.name, sender.name, content);
        Integer sizeAfterMessagePosted = messageController.getSortedMessagesList(Optional.of("")).size();

        assertEquals(sizeBeforeMessagePosted, sizeAfterMessagePosted);
    }

    @Test
    public void sayAddressedMessage_ThenCommonMessage_whenUsersInterlocutorInLastMessageIsEmpty_thenCorrect() {
        String addressedContent = "this is addressed message, it has interlocutor";
        String commonContent = "this is NOT addressed message, it has NO interlocutor";
        User sender = joinUser();
        User interlocutor = joinUser();
        messageController.addressedMessage(sender.name, interlocutor.name, addressedContent);
        messageController.postMessage(sender.name, commonContent);

        List<Message> res = messageController.getSortedMessagesList(Optional.of(sender.name));
        Message message_1 = res.get(0);
        Message message_2 = res.get(1);

        assertNotEquals(message_1.interlocutorsName, message_2.interlocutorsName);
    }

    //
    private User joinUser() {
        return userService.addNewUser("AnyName");
    }
}
