package com.emranul.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.emranul.musicplayer.Models.AlbumModel;
import com.emranul.musicplayer.Models.ArtistModel;
import com.emranul.musicplayer.Models.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SongLoader {

    public List<SongModel> getAllSong(Context context) {
        List<SongModel> songModelList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        final String id = MediaStore.Audio.AudioColumns._ID;
        final String title = MediaStore.Audio.AudioColumns.TITLE;
        final String albumId = MediaStore.Audio.AudioColumns.ALBUM_ID;
        final String album = MediaStore.Audio.AudioColumns.ALBUM;
        final String artistId = MediaStore.Audio.AudioColumns.ARTIST_ID;
        final String artist = MediaStore.Audio.AudioColumns.ARTIST;
        final String duration = MediaStore.Audio.AudioColumns.DURATION;
        final String track = MediaStore.Audio.AudioColumns.TRACK;

        String[] projection = new String[]{
                id, title, albumId, album, artistId, artist, duration, track

        };

        String sortOrder = MediaStore.Audio.Media.TITLE;

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, sortOrder);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id_ = cursor.getLong(cursor.getColumnIndex(id));
                String title_ = cursor.getString(cursor.getColumnIndex(title));
                long albumId_ = cursor.getLong(cursor.getColumnIndex(albumId));
                String album_ = cursor.getString(cursor.getColumnIndex(album));
                long artistId_ = cursor.getLong(cursor.getColumnIndex(artistId));
                String artist_ = cursor.getString(cursor.getColumnIndex(artist));
                int duration_ = cursor.getInt(cursor.getColumnIndex(duration));
                int track_ = cursor.getInt(cursor.getColumnIndex(track));

                songModelList.add(new SongModel(id_,title_,albumId_,album_,artistId_,artist_,duration_,track_));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return songModelList;
    }


    public List<AlbumModel> getAlbum(Context context) {

        List<AlbumModel> albumModels = new ArrayList<>();

        final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        final String _id = MediaStore.Audio.Albums._ID;
        final String album_name = MediaStore.Audio.Albums.ALBUM;
        final String artist = MediaStore.Audio.Albums.ARTIST;
        final String albumart = MediaStore.Audio.Albums.ALBUM_ART;
        final String tracks = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

        final String[] columns = {_id, album_name, artist, albumart, tracks};

        String sortOrder = MediaStore.Audio.Albums.ALBUM;

        Cursor cursor = context.getContentResolver().query(uri, columns, null, null, sortOrder);

        if (cursor != null && cursor.moveToFirst()) {

            do {

                long id = cursor.getLong(cursor.getColumnIndex(_id));
                String name = cursor.getString(cursor.getColumnIndex(album_name));
                String artist2 = cursor.getString(cursor.getColumnIndex(artist));
                String artPath = cursor.getString(cursor.getColumnIndex(albumart));
                Bitmap art = BitmapFactory.decodeFile(artPath);
                int nr = Integer.parseInt(cursor.getString(cursor.getColumnIndex(tracks)));

                albumModels.add(new AlbumModel(id, name, artist2, art, nr));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return albumModels;
    }

    public List<ArtistModel> getArtist(Context context) {
        List<ArtistModel> models = new ArrayList<>();

        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

        String[] projection = new String[]{
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS
        };
        String sortOrder = MediaStore.Audio.Artists.ARTIST;
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,sortOrder);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                models.add(new ArtistModel(cursor.getLong(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return models;
    }
}
