package cms.stephenwongc195.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {

    public static ZonedDateTime convertEstToLocal(int estHour) {
        // instantiate est time
        ZoneId estZone = ZoneId.of("America/New_York");
        ZonedDateTime estTime = ZonedDateTime.now(estZone);
        // set est time to param time
        estTime = estTime.withHour(estHour);
        estTime = estTime.withMinute(0);
        estTime = estTime.withSecond(0);
        // convert est time to system default time
        ZonedDateTime localTime = estTime.withZoneSameInstant(ZoneId.systemDefault());
        return localTime;
    }

}
