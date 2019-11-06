package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.dao.impl.MessageRepository;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@RestClientTest(MessageRepository.class)
public class MessageRepositoryTests {

    @Autowired
    IMessageRepository messageRepository;

    @Test
    public void addNewMessage_whenReturnsExpectedMessage_thenCorrect() {
        String content = "hello test";
        String userName = "AnyUserName";
        Message message = new Message(content, userName);
        Message returnedMessage = messageRepository.addNewMessage(content, userName);
        message.id = returnedMessage.id = "casted_id";
        assertEquals(message, returnedMessage);
    }

    @Test
    public void addTwoMessagesWithSameContent_ifReceivedMapWithExpectedMessages_thenCorrect() {
        String content = "duplicated content";
        String userName = "AnyUserName";
        Message message_1 = messageRepository.addNewMessage(content, userName);
        Message message_2 = messageRepository.addNewMessage(content, userName);

        Map<String, Message> res = messageRepository.getAllMessages();
        Message messageReceived_1 = res.get(message_1.id);
        Message messageReceived_2 = res.get(message_2.id);

        assertEquals(res.size(), 2);
        assertEquals(message_1, messageReceived_1);
        assertEquals(message_2, messageReceived_2);
    }

    @Test
    public void addMessageWithNullInsteadOfContentAndUserName_ifStorageSizeIsUnchanged_thenCorrect() {
        Integer sizeBeforeInsertion = messageRepository.getAllMessages().size();
        messageRepository.addNewMessage(null, null);
        Integer sizeAfterInsertion = messageRepository.getAllMessages().size();
        assertEquals(sizeBeforeInsertion, sizeAfterInsertion);
    }

    @Test
    public void addMessageWithEmptyStringInsteadOfContentAndUserName_ifStorageSizeUnchanged_thenCorrect() {
        Integer sizeBeforeInsertion = messageRepository.getAllMessages().size();
        messageRepository.addNewMessage("", "");
        Integer sizeAfterInsertion = messageRepository.getAllMessages().size();
        assertEquals(sizeBeforeInsertion, sizeAfterInsertion);
    }

    @Test
    public void populateMessageStorage_whenReceivedSortedByLocalDateTime_thenCorrect() {
        boolean areSorted = true;
        for (int i = 0; i < 12; i++) {
            messageRepository.addNewMessage("test message, one of couple (" + i + " of douzen)", "AnyUserName");
        }
        List<Message> res = messageRepository.getAllSortedMessages();
        for (int i = 0; i < 11; i++) {
            Message current = res.get(i);
            Message next = res.get(i + 1);
            if (current.posted.isAfter(next.posted)) {
                areSorted = false;
                i = 12;
            }
        }
        assertTrue(areSorted);
    }
}
