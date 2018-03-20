package com.plan.ahead.Utilities;

/**
 * Created by mihai on 20.03.2018.
 */

public class TimeStringUtils {
    // the time string must be like this: HH:MM

    public static int getHour(String time)
    {
        return Integer.parseInt(time.split(":")[0]);
    }

    public static int getMinutes(String time)
    {
        return Integer.parseInt(time.split(":")[1]);
    }

    public static String intsToTime(int h, int m)
    {
        if(h>24)
            h=24;
        if(h<0)
            h=0;
        if(m>60)
            m = 60;
        if(m<0)
            m=0;

        return h + ":" + m;
    }

    public static int timeCompare(String time1, String time2)
    {
        // -1: time 1 > time 2
        // 0 : time 1 = time 2
        // 1 : time 1 < time 2

        if(getHour(time1) == getHour(time2)) {
            if(getMinutes(time1) > getMinutes(time2))
                return -1;
            else if(getMinutes(time1) < getMinutes(time2))
                return 1;
        }

        else if(getHour(time1) > getHour(time2))
            return -1;

        else if(getHour(time1) < getHour(time2))
            return 1;

        return 0;
    }
}
