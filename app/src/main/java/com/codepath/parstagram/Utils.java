package com.codepath.parstagram;

import android.text.format.DateUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils extends AppCompatActivity {

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(Date date) throws ParseException, java.text.ParseException {
        String stringDate = String.valueOf(date);
        String parseFormat = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(parseFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        long dateMillis = sf.parse(stringDate).getTime();
        relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();

        return relativeDate;
    }
}