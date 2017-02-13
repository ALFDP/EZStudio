package com.alfdp.ezstudio.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maxim on 13/02/2017.
 */

public class DateHelper {

    public DateHelper() {
    }

    public String getActualDate() {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        return date;
    }
}
