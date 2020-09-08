package com.emranul.musicplayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.Adapters.AlbumAdapter;
import com.emranul.musicplayer.R;
import com.emranul.musicplayer.SongLoader;


public class AlbumFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;


    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_album, container, false);

        recyclerView = view.findViewById(R.id.album_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        albumAdapter = new AlbumAdapter(new SongLoader().getAlbum(getContext()), getContext());
        albumAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(albumAdapter);




        return view;
    }
}