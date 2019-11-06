package si.inspirited.web.util;

import java.util.UUID;

public class UserUtil {

    public UserUtil() {}

    public static String generateUserName() {
        String res = "User_";
        final String suffix = UUID.randomUUID().toString();
        res += suffix;

        return res;
    }
}
