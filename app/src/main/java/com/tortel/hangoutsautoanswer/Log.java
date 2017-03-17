package com.tortel.hangoutsautoanswer;

public class Log {
    private static final String TAG = "NotificationListenerApp";

    public static void v(String str){
        android.util.Log.v(TAG, str);
    }

    public static void e(String str, Throwable e){
        android.util.Log.e(TAG, str, e);
    }
}
