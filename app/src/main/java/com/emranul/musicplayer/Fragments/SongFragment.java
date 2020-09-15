package com.emranul.musicplayer.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.Adapters.SongAdapter;
import com.emranul.musicplayer.R;


public class SongFragment extends Fragment {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;

    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        recyclerView = view.findViewById(R.id.song_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        new SongData().execute("");


        return view;
    }

    private class SongData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            if (getContext() != null) {
                songAdapter = new SongAdapter(new com.emranul.musicplayer.SongLoader().getAllSong(getContext()), "");

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String s) {
            if (getActivity() != null) {
                recyclerView.setAdapter(songAdapter);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}