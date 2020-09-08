package com.emranul.musicplayer.Models;

import android.graphics.Bitmap;

public class AlbumModel {
    private long id;
    private String albumName;
    private String artistName;
    private int relYear;
    private Bitmap albumImg;

    public AlbumModel(long id, String albumName, String artistName, Bitmap albumImg, int relYear) {
        this.albumImg = albumImg;
        this.id = id;
        this.albumName = albumName;
        this.artistName = artistName;
        this.relYear = relYear;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumImg(Bitmap albumImg) {
        this.albumImg = albumImg;
    }

    public void setRelYear(int relYear) {
        this.relYear = relYear;
    }

    public long getID() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public Bitmap getAlbumImg() {
        return albumImg;
    }

    public int getRelYear() {
        return relYear;
    }

}
