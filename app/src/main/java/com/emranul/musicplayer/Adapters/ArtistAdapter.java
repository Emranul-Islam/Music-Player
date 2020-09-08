package com.emranul.musicplayer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.Fragments.AlbumDetailsFragment;
import com.emranul.musicplayer.Models.ArtistModel;
import com.emranul.musicplayer.R;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private Context context;
    private List<ArtistModel> artistModels;

    public ArtistAdapter(Context context, List<ArtistModel> artistModels) {
        this.context = context;
        this.artistModels = artistModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_artist_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.artistName.setText(artistModels.get(position).getArtistName());
        holder.numOfSong.setText(artistModels.get(position).getNumOfSong() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = artistModels.get(position).getArtistId();
                String artist = artistModels.get(position).getArtistName();
                String numAlbum = String.valueOf(artistModels.get(position).getNumOfAlbum());
                int numSong = artistModels.get(position).getNumOfSong();


                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment;
                fragment = AlbumDetailsFragment.newInstance(id, "ARTIST", artist, numAlbum, numSong);

                fragmentTransaction.hide(((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.slider_mainframe));

                fragmentTransaction.add(R.id.slider_mainframe, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView artistName, numOfSong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.raw_artist_name);
            numOfSong = itemView.findViewById(R.id.raw_artist_num);
        }
    }
}
