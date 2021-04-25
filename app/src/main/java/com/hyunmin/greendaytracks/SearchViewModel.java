package com.hyunmin.greendaytracks;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/* ViewModel for SearchFragment
 * connect to Database
 * get Query functions form TrackDataDao
 * */

public class SearchViewModel extends AndroidViewModel {

    private TrackDataDao trackDataDao;
    private AppDatabase appDatabase;

    public SearchViewModel(Application application){
        super(application);
        //set connection
        appDatabase = AppDatabase.getDatabase(application);
        trackDataDao = appDatabase.trackDataDao();
    }

    public LiveData<TrackData> getTrack(int trackId){
        return trackDataDao.getTrack(trackId);
    }

    public Boolean isFavorite(int trackId){
        return trackDataDao.isFavorite(trackId);
    }

    public void insert(TrackData trackData){
        new InsertAsyncTask(trackDataDao).execute(trackData);
    }

    public void delete(TrackData trackData){
        new DeleteAsyncTask(trackDataDao).execute(trackData);
    }


    //perform insert operations in background thread
    private class InsertAsyncTask extends AsyncTask<TrackData, Void, Void> {

        TrackDataDao mTrackDataDao;

        public InsertAsyncTask(TrackDataDao mTrackDataDao) {
            this.mTrackDataDao = mTrackDataDao;
        }

        @Override
        protected Void doInBackground(TrackData... trackData) {
            mTrackDataDao.insert(trackData[0]);
            return null;
        }
    }
    //perform delete operations in background thread
    private class DeleteAsyncTask extends AsyncTask<TrackData, Void, Void>{
        TrackDataDao mTrackDataDao;

        public DeleteAsyncTask(TrackDataDao trackDataDao){
            this.mTrackDataDao = trackDataDao;
        }

        @Override
        protected Void doInBackground(TrackData... trackDatas) {
            mTrackDataDao.delete(trackDatas[0]);
            return null;
        }
    }


}