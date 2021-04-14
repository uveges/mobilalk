package com.example.sales_management2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {

    private static final String CHANNEL_ID = "shop_notification_channel";
    private final int NOTIFICATION_ID = 0;

    private Context mContext;
    private NotificationManager notificationManager;

    public NotificationHandler(Context context){
        this.mContext = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();

    }
    //értesítési csatorna

    private void createChannel(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Shop Notification", NotificationManager.IMPORTANCE_DEFAULT);

        channel.enableLights(true);             //legyen fény!
        channel.setLightColor(Color.RED);       //bármilyen RGB -vel működik
        channel.enableVibration(true);          //rezegjen!
        channel.setDescription("Notifications from Shop application");      //mit csinál ez a channel?

        //jöjjön létre a channel; azt az objektumot kapja, amit itt felparamétereztünk
        this.notificationManager.createNotificationChannel(channel);

    }

    public void send(String message) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle("Shop Application")                //alapvető üzenet
                .setContentText(message)                            //message, amit a user megadott, amikor megjívta ezt a függvényt
                .setSmallIcon(R.drawable.ic_baseline_widgets_24);    //kell neki egy ikon
                //.setContentIntent(pendingIntent);

        this.notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

}
