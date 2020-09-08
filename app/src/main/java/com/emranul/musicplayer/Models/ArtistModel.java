package com.emranul.musicplayer.Models;

public class ArtistModel {
    private long artistId;
    private String artistName;
    private int numOfSong;
    private int numOfAlbum;

    public ArtistModel(long artistId, String artistName, int numOfSong, int numOfAlbum) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.numOfSong = numOfSong;
        this.numOfAlbum = numOfAlbum;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getNumOfSong() {
        return numOfSong;
    }

    public void setNumOfSong(int numOfSong) {
        this.numOfSong = numOfSong;
    }

    public int getNumOfAlbum() {
        return numOfAlbum;
    }

    public void setNumOfAlbum(int numOfAlbum) {
        this.numOfAlbum = numOfAlbum;
    }
}
