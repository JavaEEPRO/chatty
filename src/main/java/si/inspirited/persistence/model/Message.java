package si.inspirited.persistence.model;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class Message {

    public Message() {}

    public Message(String content) {
        this.content = content;
    }

    LocalDateTime posted;

    String content;
}
