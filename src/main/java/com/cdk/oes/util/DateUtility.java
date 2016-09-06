package com.cdk.oes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qayyumn on 8/18/2016.
 */
public class DateUtility {
    public static Date stringToDate(String input, String dateFormat) {
        Date utilDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            utilDate = sdf.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utilDate;
    }


    public static Date stringToDateTime(String input) {
        DateFormat inputDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = inputDateFormatter.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Date getCurrentDateTime() {

        Date date = new Date();
        return date;
    }
}

