package com.hyunmin.greendaytracks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/* ViewModel for FavoriteFragment
 * Gets Database connection
 * */

public class FavoriteViewModel extends AndroidViewModel {

    private TrackDataDao trackDataDao;
    private AppDatabase appDatabase;
    private LiveData<List<TrackData>> favorites;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(application);
        trackDataDao = appDatabase.trackDataDao();
        favorites = trackDataDao.getAll();
    }

    //updates UI for Favorite fragment
    LiveData<List<TrackData>> getFavorites(){
        return favorites;
    }
}