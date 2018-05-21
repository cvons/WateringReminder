package com.example.christian.wateringreminder;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.christian.wateringreminder.Database.AppDatabase;
import com.example.christian.wateringreminder.Database.Plant;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "inventory")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Plant> plants = db.plantDao().getAllPlants();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(plants);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.add_plant_id:
                Intent intent = new Intent(this,
                        AddActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Unknown error occured", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}

