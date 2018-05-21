package com.example.christian.wateringreminder.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

@Database(entities = {Plant.class}, version = 2 )
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlantDao plantDao();
}