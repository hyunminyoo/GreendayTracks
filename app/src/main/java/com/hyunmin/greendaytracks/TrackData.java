package com.hyunmin.greendaytracks;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* Define Data and Columns for Database
 * include Constructor and getter, setter
 *  */

@Entity
public class TrackData {

    @PrimaryKey
    private int trackId;

    @ColumnInfo(name = "track_name")
    private String trackName;

    @ColumnInfo(name = "collection_name")
    private String collectionName;

    @ColumnInfo(name = "artist_name")
    private String artistName;

    @ColumnInfo(name = "artwork_url")
    private String artWorkUrl;

    //check if the track has been checked as favorite
    //false for nonfavorite tracks and true for favorite tracks
    @ColumnInfo(name = "is_favorite", defaultValue = "0")
    private boolean isFavorite;



    public TrackData(int trackId, String trackName, String collectionName, String artistName, String artWorkUrl, boolean isFavorite) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.collectionName = collectionName;
        this.artistName = artistName;
        this.artWorkUrl = artWorkUrl;
        this.isFavorite = isFavorite;
    }

    public TrackData(int trackId, String trackName, String collectionName, String artistName, String artWorkUrl) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.collectionName = collectionName;
        this.artistName = artistName;
        this.artWorkUrl = artWorkUrl;
    }

    public TrackData(){

    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtWorkUrl() {
        return artWorkUrl;
    }

    public void setArtWorkUrl(String artWorkUrl) {
        this.artWorkUrl = artWorkUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


}
