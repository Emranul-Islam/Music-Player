package com.emranul.musicplayer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.AlbumArtistDetailsActivity;
import com.emranul.musicplayer.Models.ArtistModel;
import com.emranul.musicplayer.R;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private Context context;
    public static List<ArtistModel> artistModels;

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
                Intent intent = new Intent(context, AlbumArtistDetailsActivity.class);
                intent.putExtra("POSITION", String.valueOf(position));
                intent.putExtra("ACTION", "ARTIST");
                context.startActivity(intent);
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
