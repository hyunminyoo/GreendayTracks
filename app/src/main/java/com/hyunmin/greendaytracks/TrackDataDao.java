package com.hyunmin.greendaytracks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

/*Dao Query methods for Database
 * */

@Dao
public interface TrackDataDao {
    //get all tracks
    @Query("SELECT * FROM trackdata")
    LiveData<List<TrackData>> getAll();

    //get specific track with track_id = trackId
    @Query("SELECT * FROM trackData WHERE trackid IN (:trackId)")
    LiveData<TrackData> getTrack(int trackId);

    //check if Database has track with track_id = id
    @Query("SELECT EXISTS (SELECT 1 FROM trackdata WHERE trackId=:id)")
    Boolean isFavorite(int id);


    //Insert and Delete
    @Insert
    void insert(TrackData tracks);

    @Delete
    void delete(TrackData track);

}
