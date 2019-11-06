package si.inspirited.web.util;

import java.util.UUID;

public class MessageUtil {

    public static String generateMessageId() {
        String res = UUID.randomUUID().toString();
        return res;
    }
}
