package com.plan.ahead.Utilities;

public class DateStringUtils {
    // the time string must be like this: DD/MM/YYYY

    public static int getDay(String date)
    {
        return Integer.parseInt(date.split("/")[0]);
    }

    public static int getMonth(String date)
    {
        return Integer.parseInt(date.split("/")[1]);
    }

    public static int getYear(String date)
    {
        return Integer.parseInt(date.split("/")[2]);
    }

    public static int dateCompare(String date1, String date2)
    {
        // -1: date 1 > date 2
        // 0 : date 1 = date 2
        // 1 : date 1 < date 2

        if(getYear(date1) == getYear(date2)) {
            if(getMonth(date1) == getMonth(date2)) {
                if (getDay(date1) == getDay(date2))
                    return 0;
                else if (getDay(date1) > getDay(date2))
                    return -1;
                else if (getDay(date1) < getDay(date2))
                    return 1;
            }

            if(getMonth(date1) > getMonth(date2))
                return -1;
            else if(getMonth(date1) < getMonth(date2))
                return 1;
        }

        else if(getYear(date1) > getYear(date2))
            return -1;

        else if(getYear(date1) < getYear(date2))
            return 1;

        return 0;
    }
}
