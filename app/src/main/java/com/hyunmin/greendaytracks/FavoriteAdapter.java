package com.hyunmin.greendaytracks;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/* Adapter for building Recycler View
 * Recycler View is at favorite_fragment.xml
 * Each row is defined by track_row.xml
 * */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<TrackData> favoriteData;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageview_url;
        private final TextView textview_artist_name;
        private final TextView textview_collection_name;
        private final TextView textview_track_name;
        private final ImageView imageview_favorite;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textview_artist_name = view.findViewById(R.id.textview_artist_name);
            textview_collection_name = view.findViewById(R.id.textview_collection_name);
            textview_track_name = view.findViewById(R.id.textview_track_name);
            imageview_url = view.findViewById(R.id.imageview_url);
            imageview_favorite = view.findViewById(R.id.imageview_favorite);

        }

    }

    public FavoriteAdapter(Context context) {
        //fresco initialization
        Fresco.initialize(context);
    }

    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.track_row, viewGroup, false);

        return new FavoriteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder viewHolder, final int position) {

        if(favoriteData != null){
            TrackData currentData = favoriteData.get(position);
            viewHolder.textview_track_name.setText(currentData.getTrackName());
            viewHolder.textview_collection_name.setText(currentData.getCollectionName());
            viewHolder.textview_artist_name.setText(currentData.getArtistName());
            viewHolder.imageview_favorite.setImageResource(R.drawable.star_filled);

            Uri uri = Uri.parse(currentData.getArtWorkUrl());
            viewHolder.imageview_url.setImageURI(uri);

            viewHolder.itemView.setTag(position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentName = viewHolder.textview_track_name.getText().toString();
                    Toast.makeText(v.getContext(), currentName, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //When there is no Favorite saved
            viewHolder.textview_track_name.setText(R.string.no_favorite);
        }

    }

    // Return the size of your TrackData (if null, return 0)
    @Override
    public int getItemCount() {
        if(favoriteData != null)
            return favoriteData.size();
        else return 0;
    }

    public void setFavorites(List<TrackData> trackDatas){
        favoriteData = trackDatas;
        notifyDataSetChanged();
    }

    public TrackData getTrackAt(int position){
        return favoriteData.get(position);
    }
}


