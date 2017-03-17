package com.tortel.hangoutsautoanswer;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.util.Date;

public class NotificationListener extends NotificationListenerService {
    private static final String HANGOUTS_PACKAGE = "com.google.android.talk";
    private static final String ACTION_ANSWER = "answer";
    private static final long MIN_TIME_BETWEEN = 60*1000;

    private Date lastReceivedDate;

    public NotificationListener() {
        super();
        Log.v("Constructor called");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("onCreate called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onListenerConnected(){
        Log.v("Listener connected");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Nothing
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rankingMap) {
        // Nothing
    }

    @Override
    public void onNotificationPosted(StatusBarNotification notification){
        handleNotification(notification);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
        handleNotification(sbn);
    }

    private void handleNotification(StatusBarNotification notification){
        Log.v("Notification from "+notification.getPackageName());
        if(notification.getPackageName().equals(HANGOUTS_PACKAGE)){
            Log.v("Got hangouts notification");
            Notification.Action actions[] = notification.getNotification().actions;

            if(lastReceivedDate != null && lastReceivedDate.getTime()+MIN_TIME_BETWEEN <= notification.getPostTime()){
                Log.v("Last notification was within a minute, ignoring");
            } else {
                if(actions != null){
                    for(Notification.Action action : actions){
                        Log.v("Action with title: "+action.title);

                        if(action.title.toString().toLowerCase().equals(ACTION_ANSWER)){
                            try {
                                Log.v("Calling send on answer pending intent");
                                action.actionIntent.send();
                                //lastReceivedDate = new Date();
                            } catch (PendingIntent.CanceledException e) {
                                Log.e("Exception sending pendingIntent", e);
                            }
                        }
                    }
                } else {
                    Log.v("Actions null");
                }
            }
        }
    }

    @Override
    public void onListenerDisconnected() {
        Log.v("Listener disconnected");
        // Nothing
    }

    @Override
    public void onNotificationRankingUpdate(RankingMap rankingMap) {
        // Nothing
    }

    @Override
    public void onListenerHintsChanged(int hints) {
        // Nothing
    }
}
