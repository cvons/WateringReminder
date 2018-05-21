package com.example.christian.wateringreminder.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Plant {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "plant_name")
    private String plant;

    @ColumnInfo(name = "species_name")
    private String species;

    @ColumnInfo(name = "next_watering")
    private String nextWatering;

    @ColumnInfo(name = "last_watering")
    private String lastWatering;

    @ColumnInfo(name = "cycle")
    private int cycle;

    public Plant(String plant, String species, String nextWatering, String lastWatering, int cycle) {
        this.plant = plant;
        this.species = species;
        this.nextWatering = nextWatering;
        this.lastWatering = lastWatering;
        this.cycle = cycle;
    }

    public String getNextWatering() {
        return nextWatering;
    }

    public void setNextWatering(String nextWatering) {
        this.nextWatering = nextWatering;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(int cycle) {
        this.lastWatering = lastWatering;
    }
}
