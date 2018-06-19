package com.harddrillstudio.wat.concurrent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePrinter {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static Date date;

    public static String getCurrentDateTime() {
        date = new Date();
        return formatter.format(date);
    }

}
