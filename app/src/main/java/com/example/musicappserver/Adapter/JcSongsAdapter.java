package com.example.musicappserver.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicappserver.Model.GetSongs;
import com.example.musicappserver.Model.Utility;
import com.example.musicappserver.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JcSongsAdapter extends RecyclerView.Adapter<JcSongsAdapter.SongsAdapterView> {

    private int selectedPosition;
    Context context;
    List<GetSongs> arrayListSongs;
    private RecyclerItemClickListner listner;

    public JcSongsAdapter(Context context, List<GetSongs> arrayListSongs, RecyclerItemClickListner listner) {
        this.context = context;
        this.arrayListSongs = arrayListSongs;
        this.listner = listner;
    }

    @NonNull
    @Override
    public JcSongsAdapter.SongsAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.songs_row,parent,false);
        return new SongsAdapterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JcSongsAdapter.SongsAdapterView holder, int position) {
        GetSongs getSongs = arrayListSongs.get(position);

        if(getSongs != null){
            if(selectedPosition == position){
                holder.ivPlayActive.setVisibility(View.VISIBLE);
            } else {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent));
                holder.ivPlayActive.setVisibility(View.INVISIBLE);
            }
        }

        Glide.with(context).load(getSongs.getAlbum_art()).into(holder.iv_artwork);
        holder.tvTitle.setText(getSongs.getSongTitle());
        holder.tvArtist.setText(getSongs.getArtist());
        holder.tvDuration.setText(Utility.convertDuration(Long.parseLong(getSongs.getSongDuration())));
        holder.bind(getSongs,listner);
    }

    @Override
    public int getItemCount() {
        return arrayListSongs.size();
    }

    public class SongsAdapterView extends RecyclerView.ViewHolder{
        private TextView tvTitle,tvArtist,tvDuration;
        private ImageView iv_artwork;
        private ImageView ivPlayActive;
        public SongsAdapterView(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            ivPlayActive = itemView.findViewById(R.id.iv_play_active);
            iv_artwork = itemView.findViewById(R.id.iv_artwork);
        }

        public void bind(GetSongs getSongs, RecyclerItemClickListner listner) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onClickListener(getSongs,getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListner {
        void onClickListener(GetSongs songs, int position);
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
