package com.emranul.musicplayer.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.Models.ArtistModel;
import com.emranul.musicplayer.R;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private List<ArtistModel> artistModels;

    public ArtistAdapter(List<ArtistModel> artistModels) {
        this.artistModels = artistModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_artist_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.artistName.setText(artistModels.get(position).getArtistName());
        holder.numOfSong.setText(artistModels.get(position).getNumOfSong() + "");
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
