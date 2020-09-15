package com.emranul.musicplayer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emranul.musicplayer.AlbumArtistDetailsActivity;
import com.emranul.musicplayer.Models.AlbumModel;
import com.emranul.musicplayer.R;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    public static List<AlbumModel> albumModels;
    private Context context;

    public AlbumAdapter(List<AlbumModel> albumModels, Context context) {
        this.albumModels = albumModels;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_album_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumViewHolder holder, final int position) {
        holder.albumName.setText(albumModels.get(position).getAlbumName());
        holder.albumRD.setText(albumModels.get(position).getArtistName());
        holder.albumImage.setImageBitmap(albumModels.get(position).getAlbumImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long albumId = albumModels.get(position).getID();
//                String albumName = albumModels.get(position).getAlbumName();
//                String albumArtist = albumModels.get(position).getArtistName();
//                int relYear = albumModels.get(position).getRelYear();

                Intent intent = new Intent(context, AlbumArtistDetailsActivity.class);
                intent.putExtra("POSITION", String.valueOf(position));
                intent.putExtra("ACTION", "ALBUM");
                context.startActivity(intent);


            }
        });
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
