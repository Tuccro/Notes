package com.tuccro.notes.util;

import android.content.Context;

import com.tuccro.notes.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tuccro on 2/1/16.
 */
public class DateFormatter {

    long date;
    Context context;

    public DateFormatter(long date, Context context) {
        this.date = date;
        this.context = context;
    }

    public String getDateOrDay() {

        String formattedDate = getFormattedDate(date);
        String todayFormattedDate = getFormattedDate(System.currentTimeMillis());

        Calendar today = Calendar.getInstance();

        Calendar someDay = Calendar.getInstance();
        today.setTimeInMillis(date);

        if (formattedDate.equals(todayFormattedDate)) {
            return context.getString(R.string.today);
        } else if (isOneDayDifference(today, someDay)) {
            return context.getString(R.string.yesterday);
        } else {
            return formattedDate;
        }
    }

    public String getFormattedDate(long millis) {
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(millis);
        return curStringDate;
    }

    private boolean isOneDayDifference(Calendar dayOne, Calendar dayTwo) {

        if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)
                && Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR)) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
