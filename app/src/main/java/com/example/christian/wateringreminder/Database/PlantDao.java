package com.example.christian.wateringreminder.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PlantDao {
    @Query("SELECT * FROM plant")
    List<Plant> getAllPlants();

    @Insert
    void insertAll(Plant... plants);
}
