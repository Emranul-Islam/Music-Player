package com.emranul.musicplayer.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.Models.AlbumModel;
import com.emranul.musicplayer.R;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<AlbumModel> albumModels ;

    public AlbumAdapter(List<AlbumModel> albumModels) {
        this.albumModels = albumModels;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_album_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.albumName.setText(albumModels.get(position).getAlbumName());
        holder.albumRD.setText(albumModels.get(position).getNr_of_songs()+"");
        holder.albumImage.setImageBitmap(albumModels.get(position).getAlbumImg());
    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private ImageView albumImage;
        private TextView albumName, albumRD;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            albumImage = itemView.findViewById(R.id.raw_album_image);
            albumName = itemView.findViewById(R.id.raw_album_name);
            albumRD = itemView.findViewById(R.id.raw_album_artist);
        }
    }
}
