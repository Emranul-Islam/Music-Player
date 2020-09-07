package com.emranul.musicplayer.Adapters;

import android.content.ContentUris;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emranul.musicplayer.Models.SongModel;
import com.emranul.musicplayer.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<SongModel> songModels;


    public SongAdapter(List<SongModel> songModels) {
        this.songModels = songModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_song_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(songModels.get(position).getTitle());
        holder.artist.setText(songModels.get(position).getDuration()+"");
        Glide.with(holder.itemView.getContext())
                .load(getImage(songModels.get(position).getAlbumId()))
                .placeholder(R.drawable.ic_music)
                .into(holder.image);

    }

    private Uri getImage(long albumId) {

        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }

    @Override
    public int getItemCount() {
        return songModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name , artist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.raw_image);
            name = itemView.findViewById(R.id.raw_music);
            artist = itemView.findViewById(R.id.raw_artist);
        }
    }
}
