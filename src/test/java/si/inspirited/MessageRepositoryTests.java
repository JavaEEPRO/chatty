package si.inspirited;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import si.inspirited.persistence.dao.IMessageRepository;
import si.inspirited.persistence.dao.impl.MessageRepository;
import si.inspirited.persistence.model.Message;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@RestClientTest(MessageRepository.class)
public class MessageRepositoryTests {

    @Autowired
    IMessageRepository messageRepository;

    @Test
    public void addNewMessage_whenReturnsExpectedMessage_thenCorrect() {
        String content = "hello test";
        Message message = new Message(content);
        Message returnedMessage = messageRepository.addNewMessage(content);
        message.id = returnedMessage.id = "casted_id";
        assertEquals(message, returnedMessage);
    }
}
