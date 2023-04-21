package cms.stephenwongc195.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Context {
    private static int userId;
    private static String userName;


    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Context.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Context.userName = userName;
    }



}
