package com.emranul.musicplayer;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.emranul.musicplayer.Adapters.ViewPagerAdapter;
import com.emranul.musicplayer.Fragments.AlbumFragment;
import com.emranul.musicplayer.Fragments.ArtistFragment;
import com.emranul.musicplayer.Fragments.SongFragment;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {


//    public static ImageButton like, notlike, dislike, notdislike;
//    public static ImageButton play, pause, play_main, pause_main;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AlbumFragment albumFragment;
    private ArtistFragment artistFragment;
    private SongFragment songFragment;
    public static MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        permissionGranted();

                    }

                    @Override
                    public void onPermissionDenied(final PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();


    }

    private void permissionGranted() {


        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);

        artistFragment = new ArtistFragment();
        albumFragment = new AlbumFragment();
        songFragment = new SongFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        viewPagerAdapter.addFragment(songFragment, "Song");
        viewPagerAdapter.addFragment(albumFragment, "Album");
        viewPagerAdapter.addFragment(artistFragment, "Artist");


        viewPager.setAdapter(viewPagerAdapter);


    }


}