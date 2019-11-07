package si.inspirited;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IMessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTests {

    @Autowired
    IMessageService messageService;

    @Test
    public void sendMessage_whenReturnedMapWithGivenMessage_thenCorrect() {
        String content = "hello test";
        String stubUserName = "SomeUser";
        messageService.addNewMessage(content, stubUserName);
        Map<String, Message> res = messageService.getAllMessages();
        List<Message> values = new ArrayList<>(res.values());
        assertEquals(values.size(), 1);
        assertEquals(values.get(0).content, content);
    }

    @Test
    public void sendDozenOfMessages_whenReceivedSortedListOfDeclaredCapacity_thenCorrect() {
        String content;
        String stubUserName = "SomeUser";
        boolean areSorted = true;
        for (int i = 0; i < 12; i++) {
            content = "message " + i;
            messageService.addNewMessage(content, stubUserName);
        }
        List<Message> res = messageService.getAllSortedMessages();

        for (int i = 0; i < 11; i++) {
            Message current = res.get(i);
            Message next = res.get(i + 1);
            if (current.posted.isAfter(next.posted)) {
                areSorted = false;
                i = 12;
            }
        }
        assertEquals(res.size(), 12);
        assertTrue(areSorted);
    }

    @After
    public void refreshMessagesStorage() {
        messageService.refreshMessagesStorage();
    }
}
