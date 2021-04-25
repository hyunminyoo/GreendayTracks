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

/* Adapter for Search fragment's recyclerView
 * recyclerView is at search_fragment.xml
 * indivisual rows for recyclerView is initialized by track_row.xml
 * */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<TrackData> trackData;
    private OnFavoriteClickListener mListener;

    public interface OnFavoriteClickListener{
        void onFavoriteClick(int position);
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener){
        mListener = listener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageview_url;
        private final TextView textview_artist_name;
        private final TextView textview_collection_name;
        private final TextView textview_track_name;
        private final ImageView imageview_favorite;

        public ViewHolder(View view, OnFavoriteClickListener listener, List<TrackData> trackDataList) {
            super(view);
            // Define click listener for the ViewHolder's View

            textview_artist_name = view.findViewById(R.id.textview_artist_name);
            textview_collection_name = view.findViewById(R.id.textview_collection_name);
            textview_track_name = view.findViewById(R.id.textview_track_name);
            imageview_url = view.findViewById(R.id.imageview_url);
            imageview_favorite = view.findViewById(R.id.imageview_favorite);

            //toggle switch for favorite button
            imageview_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAbsoluteAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            //pass position value to SearchFragment.java
                            listener.onFavoriteClick(position);

                            //set favorite button shape
                            if (imageview_favorite.getResources().equals(R.drawable.star_filled)){
                                //if filled make it empty when pressed
                                imageview_favorite.setImageResource(R.drawable.star_empty);

                            }else {
                                //if empty make it filled when pressed
                                imageview_favorite.setImageResource(R.drawable.star_filled);
                            }
                        }

                    }
                }
            });
        }
    }


    public SearchAdapter(List<TrackData> data, Context ct) {
        trackData = data;
        //initialize fresco
        Fresco.initialize(ct);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.track_row, viewGroup, false);

        return new ViewHolder(view, mListener, trackData);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        if(trackData != null){
            TrackData currentTrack = trackData.get(position);

            viewHolder.textview_track_name.setText(currentTrack.getTrackName());
            viewHolder.textview_collection_name.setText(currentTrack.getCollectionName());
            viewHolder.textview_artist_name.setText(currentTrack.getArtistName());

            //set favorite button shape when loading
            if (currentTrack.isFavorite()){
                viewHolder.imageview_favorite.setImageResource(R.drawable.star_filled);
            }else {
                viewHolder.imageview_favorite.setImageResource(R.drawable.star_empty);
            }

            //set imageurl
            Uri uri = Uri.parse(currentTrack.getArtWorkUrl());
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

    // Return the size of your TrackData
    // If null, return 0
    @Override
    public int getItemCount() {
        return (trackData == null ? 0 : trackData.size());
    }
}

