package gg.bayes.challenge.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

    public static long convertTimestampStringToLong(String input) {
        LocalTime localTime = LocalTime.parse(input);
        return ChronoUnit.MILLIS.between(LocalTime.MIDNIGHT, localTime);
    }

}
