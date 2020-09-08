package com.emranul.musicplayer.Fragments;

import android.content.ContentUris;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emranul.musicplayer.Adapters.SongAdapter;
import com.emranul.musicplayer.R;
import com.emranul.musicplayer.SongLoader;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AlbumDetailsFragment extends Fragment {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private long ID;
    private String C_ONE, C_TWO, CHECK;
    private int C_THREE;
    private Bitmap albumArt;
    private RecyclerView recyclerView;
    private TextView artist, relYear;
    private ImageView image;
    private SongAdapter adapter;


    public static AlbumDetailsFragment newInstance(long id, String check, String f1, String f2, int f3) {

        Bundle args = new Bundle();
        args.putLong("_ID", id);
        args.putString("CHECK", check); // kon fragment theke asbe seta mane 2 fragment er kaj ek jaygay tai ei system
        args.putString("_NAME", f1);
        args.putString("_ARTIST", f2);
        args.putInt("_NUM", f3);
        AlbumDetailsFragment fragment = new AlbumDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ID = getArguments().getLong("_ID");
        CHECK = getArguments().getString("CHECK");
        C_ONE = getArguments().getString("_NAME");
        C_TWO = getArguments().getString("_ARTIST");
        C_THREE = getArguments().getInt("_NUM");
        super.onCreate(savedInstanceState);
    }

    public AlbumDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album_details, container, false);

        collapsingToolbarLayout = view.findViewById(R.id.ad_collapsing_layout);


        artist = view.findViewById(R.id.ad_artist);
        relYear = view.findViewById(R.id.ad_numofsong);
        image = view.findViewById(R.id.ad_image);
        recyclerView = view.findViewById(R.id.ad_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        collapsingToolbarLayout.setTitle(C_ONE);

        if (CHECK.equals("ALBUM")) {
            artist.setText(C_TWO);
            relYear.setText(C_THREE + "");

            Glide.with(getContext())
                    .load(getImage(ID))
                    .placeholder(R.drawable.ic_music)
                    .into(image);


            adapter = new SongAdapter(new SongLoader().getAlbumSong(getContext(), ID));
        } else {
            artist.setText("Total Album: " + C_TWO);
            relYear.setText("Total Song: " + C_THREE);

            Glide.with(getContext())
                    .load(getImage(ID))
                    .placeholder(R.drawable.ic_music)
                    .into(image);
            adapter = new SongAdapter(new SongLoader().getArtistSong(getContext(), ID));


        }


        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    private Uri getImage(long albumId) {

        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }
}