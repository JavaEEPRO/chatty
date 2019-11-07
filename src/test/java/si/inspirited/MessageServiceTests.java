package si.inspirited;

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
}
