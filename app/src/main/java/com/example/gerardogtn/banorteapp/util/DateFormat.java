package com.example.gerardogtn.banorteapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gerardogtn on 9/25/15.
 */
public class DateFormat {

    private static SimpleDateFormat sDateFormat= new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static String getDateFormat(Date date){
        return sDateFormat.format(date);
    }
}
