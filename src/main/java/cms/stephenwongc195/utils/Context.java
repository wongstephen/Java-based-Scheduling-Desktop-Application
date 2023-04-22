package cms.stephenwongc195.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to store the user id and user name of the logged in user
 */
public class Context {
    private static int userId;
    private static String userName;

    /**
     * Returns user id as an int
     * @return
     */
    public static int getUserId() {
        return userId;
    }

    /**
     * Sets user id
     * @param userId
     */
    public static void setUserId(int userId) {
        Context.userId = userId;
    }

    /**
     * Returns user name as a string
     * @return
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Sets user name
     * @param userName
     */
    public static void setUserName(String userName) {
        Context.userName = userName;
    }



}
