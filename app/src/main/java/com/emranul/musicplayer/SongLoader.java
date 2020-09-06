package com.emranul.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.emranul.musicplayer.Models.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SongLoader {

    public List<SongModel> getAllSong(Context context) {
        List<SongModel> songModelList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Audio.AudioColumns._ID,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.ARTIST_ID,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.TRACK
        };
        String sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,sortOrder);

        if (cursor != null  && cursor.moveToFirst()){
            do {
                songModelList.add(new SongModel(cursor.getLong(0),cursor.getString(1),cursor.getLong(2),cursor.getString(3)
                        ,cursor.getLong(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7)));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return songModelList;
    }
}
