package com.emranul.musicplayer.Notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.emranul.musicplayer.Models.SongModel;
import com.emranul.musicplayer.R;

import java.util.List;

import static com.emranul.musicplayer.App.CHANNEL_ID;

public class CreateNotification {

    public static final String ACTION_PREVIOUS = "actionpreveius";
    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_NEXT = "actionnext";

    public static Notification notification;

    public static void createNotification(Context context, List<SongModel> songModels, int icPlay, int position, int size) {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

        PendingIntent pendingIntentPrev;
        int icPrev;
        if (position == 0) {
            pendingIntentPrev = null;
            icPrev = 0;
        } else {
            Intent intentPrev = new Intent(context, NotificationService.class)
                    .setAction(ACTION_PREVIOUS);
            pendingIntentPrev = PendingIntent.getBroadcast(
                    context,
                    0,
                    intentPrev,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            icPrev = R.drawable.ic_prev;
        }


        Intent intentPlay = new Intent(context, NotificationService.class)
                .setAction(ACTION_PLAY);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(
                context,
                0,
                intentPlay,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        PendingIntent pendingIntentNext;
        int icNext;
        if (position == 0) {
            pendingIntentNext = null;
            icNext = 0;
        } else {
            Intent intentPrev = new Intent(context, NotificationService.class)
                    .setAction(ACTION_NEXT);
            pendingIntentNext = PendingIntent.getBroadcast(
                    context,
                    0,
                    intentPrev,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            icNext = R.drawable.ic_next;
        }


        notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle(songModels.get(position).getTitle())
                .setContentText(songModels.get(position).getArtist())
                .setOnlyAlertOnce(true)
                .setShowWhen(false)
                .addAction(icPrev, "Prev", pendingIntentPrev)
                .addAction(icPlay, "Play", pendingIntentPlay)
                .addAction(icNext, "Next", pendingIntentNext)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                        .setMediaSession(mediaSessionCompat.getSessionToken())
                )
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        managerCompat.notify(2, notification);


    }


}
