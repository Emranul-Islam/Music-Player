package com.emranul.musicplayer.Models;

public class SongModel {
    private final long _id;
    private final String title;
    private final long albumId;
    private final String albumName;
    private final long artistId;
    private final String artist;
    private final int duration;
    private final String track;


    public SongModel(long _id, String title, long albumId, String albumName, long artistId, String artist, int duration, String track) {
        this._id = _id;
        this.title = title;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artist = artist;
        this.duration = duration;
        this.track = track;
    }

    public long get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public long getArtistId() {
        return artistId;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String toString() {
        return "SongModel{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", artistId=" + artistId +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", trackNumber=" + track +
                "}\n\n";
    }
}
