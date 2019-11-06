package si.inspirited.persistence.model;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class Message {

    LocalDateTime posted;

    String content;
}
