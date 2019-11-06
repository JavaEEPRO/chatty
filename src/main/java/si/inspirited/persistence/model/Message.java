package si.inspirited.persistence.model;

import lombok.*;
import si.inspirited.web.util.MessageUtil;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
public class Message {

    public Message() {
        this.id = MessageUtil.generateMessageId();
        this.posted = LocalDateTime.now();
    }

    public Message(String content) {
        this.id = MessageUtil.generateMessageId();
        this.content = content;
        this.posted = LocalDateTime.now();
    }


    public String id;     //found it necessary to add, cause multiple users can post messages with the same content

    LocalDateTime posted;

    public String content;

    //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
