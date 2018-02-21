package com.mouris.mario.newspaper.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ShouldUpdateUtils {

    private static final String SHARED_PREF_NAME = "news_preferences";


    private ShouldUpdateUtils() {
    }

    static public void setLastUpdateToToday(Context context, String category) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Date today = new Date(System.currentTimeMillis());
        sharedPref.edit().putLong(category, today.getTime()).apply();
    }

    static public boolean shouldUpdate(Context context, String category) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        long lastUpdate = sharedPref.getLong(category, 0);
        return System.currentTimeMillis() - lastUpdate > TimeUnit.DAYS.toMillis(1);
    }
}
