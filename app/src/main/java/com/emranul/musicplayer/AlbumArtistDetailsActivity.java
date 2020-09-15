package com.emranul.musicplayer;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emranul.musicplayer.Adapters.SongAdapter;
import com.emranul.musicplayer.Models.AlbumModel;
import com.emranul.musicplayer.Models.ArtistModel;
import com.emranul.musicplayer.Models.SongModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;
import java.util.Random;

import static com.emranul.musicplayer.Adapters.AlbumAdapter.albumModels;
import static com.emranul.musicplayer.Adapters.ArtistAdapter.artistModels;

public class AlbumArtistDetailsActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerView;
    private TextView f2TV, f3TV;
    private ImageView image;
    private int position;
    private String action;
    public static List<SongModel> artistSong, albumSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_artist_details);

        collapsingToolbarLayout = findViewById(R.id.aad_collapsing_layout);
        f2TV = findViewById(R.id.aad_f2);
        f3TV = findViewById(R.id.aad_f3);
        image = findViewById(R.id.aad_image);

        position = Integer.parseInt(getIntent().getStringExtra("POSITION"));
        action = getIntent().getStringExtra("ACTION");

        recyclerView = findViewById(R.id.aad_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        if (action.equals("ALBUM")) {
            albumSet();
        } else if (action.equals("ARTIST")) {
            artistSet();
        }


    }

    private void albumSet() {
        List<AlbumModel> album = albumModels;
        albumSong = new SongLoader().getAlbumSong(this, album.get(position).getID());

        Glide.with(this)
                .load(album.get(position).getAlbumImg())
                .placeholder(R.drawable.ic_music)
                .into(image);
        collapsingToolbarLayout.setTitle(album.get(position).getAlbumName());
        f2TV.setText(album.get(position).getArtistName());
        f3TV.setText(album.get(position).getRelYear() + "");

        SongAdapter songAdapter = new SongAdapter(albumSong, "ALBUM");
        recyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();

    }

    private void artistSet() {

        List<ArtistModel> artist = artistModels;
        artistSong = new SongLoader().getArtistSong(this, artist.get(position).getArtistId());

        collapsingToolbarLayout.setTitle(artist.get(position).getArtistName());
        f2TV.setText("Songs: " + artist.get(position).getNumOfSong());
        f3TV.setText("Albums: " + artist.get(position).getNumOfAlbum());

        Glide.with(this)
                .load(getImageUri(artistSong))
                .placeholder(R.drawable.ic_music)
                .into(image);

        SongAdapter songAdapter = new SongAdapter(artistSong, "ARTIST");
        recyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();
    }


    private Uri getImageUri(List<SongModel> artistSong) {
        Random random = new Random();
        int a = random.nextInt(artistSong.size());
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), artistSong.get(a).getAlbumId());
    }


}