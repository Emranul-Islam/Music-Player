package com.emranul.musicplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.emranul.musicplayer.Models.SongModel;
import com.emranul.musicplayer.Notification.CreateNotification;

import java.util.ArrayList;
import java.util.List;

import static com.emranul.musicplayer.Adapters.SongAdapter.getImage;
import static com.emranul.musicplayer.Adapters.SongAdapter.songModels;
import static com.emranul.musicplayer.AlbumArtistDetailsActivity.albumSong;
import static com.emranul.musicplayer.AlbumArtistDetailsActivity.artistSong;
import static com.emranul.musicplayer.MainActivity.mediaPlayer;

public class MusicPlayerActivity extends AppCompatActivity {

    public static ImageButton play, pause, prev, next;
    public static ImageButton like, notlike, dislike, notdislike;
    private int position;
    private List<SongModel> song = new ArrayList<>();
    private Uri uri;
    private Handler handler = new Handler();
    private ImageView imageView;
    private SeekBar seekBar;
    private TextView startTimeTV, endTimeTV;
    private Thread nextThread, prevThread;
    private String path, fragment;
    private Toolbar toolbar;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);


        position = Integer.parseInt(getIntent().getStringExtra("POSITION"));
        fragment = getIntent().getStringExtra("FRAGMENT");

        notificationManagerCompat = NotificationManagerCompat.from(this);

        like = findViewById(R.id.imageButton2);
        notlike = findViewById(R.id.imageButton2new);
        dislike = findViewById(R.id.button);
        notdislike = findViewById(R.id.buttontwo);
        play = findViewById(R.id.play_button_main);
        pause = findViewById(R.id.pause_button_main);
        prev = findViewById(R.id.mp_prev);
        next = findViewById(R.id.mp_next);
        imageView = findViewById(R.id.mp_image);
        seekBar = findViewById(R.id.mp_seekbar);
        startTimeTV = findViewById(R.id.StartTime);
        endTimeTV = findViewById(R.id.endTime);
        toolbar = findViewById(R.id.mp_toolbar);


        if (fragment.equals("ALBUM")) {
            song = albumSong;
        } else if (fragment.equals("ARTIST")) {
            song = artistSong;
        } else {
            song = songModels;
        }

        setSupportActionBar(toolbar);


        setAllValue();
        play.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);

        path = song.get(position).getTrack();
        if (song != null) {
            uri = Uri.parse(path);
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this, uri);
            mediaPlayer.start();


        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                mediaPlayer.start();

            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
            }
        });


        setNotification();
    }

    private void setNotification() {
        CreateNotification.createNotification(MusicPlayerActivity.this, song, R.drawable.ic_play, position, song.size() - 1);
    }


    //There i set all of extra value on ui:
    private void setAllValue() {

        toolbar.setTitle(song.get(position).getTitle());

        Glide.with(this)
                .load(getImage(song.get(position).getAlbumId()))
                .placeholder(R.drawable.ic_music)
                .into(imageView);

        endTimeTV.setText(formatTime(song.get(position).getDuration() / 1000));

        seekBar.setMax(song.get(position).getDuration() / 1000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaPlayer != null && b) {
                    mediaPlayer.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int time = mediaPlayer.getCurrentPosition() / 1000;
                    int fullTIme = mediaPlayer.getDuration() / 1000;
                    seekBar.setProgress(time);
                    startTimeTV.setText(formatTime(time));
                    if (time == fullTIme) {
                        position = (position + 1) % song.size();
                        uri = Uri.parse(song.get(position).getTrack());
                        mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, uri);
                        mediaPlayer.start();

                        setAllValue();
                    }

                }
                handler.postDelayed(this, 1000);
            }

        });

    }

    private String formatTime(int time) {
        String totalOut = "";
        String totalnew = "";
        String min = String.valueOf(time / 60);
        String sec = String.valueOf(time % 60);
        totalOut = min + ":" + sec;
        totalnew = min + ":0" + sec;
        if (sec.length() == 1) {
            return totalnew;
        } else {
            return totalOut;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextThreadBtn();
        prevThreadBtn();
    }

    private void nextThreadBtn() {
        nextThread = new Thread() {
            @Override
            public void run() {
                super.run();
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextBtnClicked();
                    }
                });
            }
        };
        nextThread.start();
    }

    private void nextBtnClicked() {
        if (mediaPlayer.isPlaying()) {
            if (position > 0) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = (position + 1) % song.size();
                uri = Uri.parse(song.get(position).getTrack());
                mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, uri);
                mediaPlayer.start();
                setNotification();
                setAllValue();
            }
        }
    }


    private void prevThreadBtn() {
        prevThread = new Thread() {
            @Override
            public void run() {
                super.run();
                prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayer.isPlaying()) {
                            if (position > 0 && mediaPlayer.getCurrentPosition() < 5000) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                position = (position - 1) % song.size();
                                uri = Uri.parse(song.get(position).getTrack());
                                mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, uri);
                                mediaPlayer.start();
                                setNotification();
                                setAllValue();
                            } else {
                                mediaPlayer.seekTo(0);
                            }
                        }
                    }
                });
            }
        };
        prevThread.start();
    }


}