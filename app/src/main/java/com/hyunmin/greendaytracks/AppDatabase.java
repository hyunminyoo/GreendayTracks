package com.hyunmin.greendaytracks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/* Initialize and build Database
 * Database entities are at TrackData class
 * Query operations are at TrackDataDao interface
 * */

@Database(entities = {TrackData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TrackDataDao trackDataDao();

    //insure that dbclass to be singleton
    private static volatile AppDatabase appDatabase;

    static AppDatabase getDatabase(final Context context){
        if(appDatabase == null){
            synchronized (AppDatabase.class){
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "favorite-db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDatabase;
    }
}
