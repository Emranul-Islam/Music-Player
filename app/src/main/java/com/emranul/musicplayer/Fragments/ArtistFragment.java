package com.emranul.musicplayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.Adapters.ArtistAdapter;
import com.emranul.musicplayer.R;
import com.emranul.musicplayer.SongLoader;

public class ArtistFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArtistAdapter artistAdapter;

    public ArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist, container, false);

        recyclerView = view.findViewById(R.id.artist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        artistAdapter = new ArtistAdapter(getContext(), new SongLoader().getArtist(view.getContext()));
        recyclerView.setAdapter(artistAdapter);

        artistAdapter.notifyDataSetChanged();
        return view;
    }
}