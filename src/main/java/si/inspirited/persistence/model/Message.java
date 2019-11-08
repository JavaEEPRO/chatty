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
        this.interlocutorsName = "";
    }

    public Message(String userName, String content) {
        this.id = MessageUtil.generateMessageId();
        this.content = content;
        this.posted = LocalDateTime.now();
        this.userName = userName;
        this.interlocutorsName = "";
    }

    public Message(String userName, String interlocutorsName, String content) {
        this(userName, content);
        this.interlocutorsName = interlocutorsName;
    }

    public String id;

    public LocalDateTime posted;

    public String content;

    String userName;

    public String interlocutorsName;

    //

    public LocalDateTime getPosted() {
        return this.posted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(content, message.content) &&
                Objects.equals(userName, message.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userName);
    }
}
