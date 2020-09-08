package com.emranul.musicplayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.emranul.musicplayer.Adapters.ViewPagerAdapter;
import com.emranul.musicplayer.R;
import com.google.android.material.tabs.TabLayout;

public class MainFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AlbumFragment albumFragment;
    private ArtistFragment artistFragment;
    private SongFragment songFragment;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        toolbar = view.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewpager);

        artistFragment = new ArtistFragment();
        albumFragment = new AlbumFragment();
        songFragment = new SongFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), 0);

        viewPagerAdapter.addFragment(songFragment, "Song");
        viewPagerAdapter.addFragment(albumFragment, "Album");
        viewPagerAdapter.addFragment(artistFragment, "Artist");


        viewPager.setAdapter(viewPagerAdapter);


        return view;
    }
}