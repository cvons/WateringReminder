package com.example.christian.wateringreminder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.christian.wateringreminder.Database.Plant;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Plant> plants;

    public MyAdapter(List<Plant> plants) {
        this.plants = plants;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView itemTitle;
        public TextView itemSpecies;
        public TextView nextWatering;
        public TextView lastWatering;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemSpecies = (TextView)itemView.findViewById(R.id.item_species);
            nextWatering = (TextView)itemView.findViewById(R.id.next_watering);
            lastWatering = (TextView)itemView.findViewById(R.id.last_watering);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                   int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.itemTitle.setText(plants.get(position).getPlant());
        viewHolder.itemSpecies.setText(plants.get(position).getSpecies());
        viewHolder.nextWatering.setText(plants.get(position).getNextWatering());
        viewHolder.lastWatering.setText(plants.get(position).getLastWatering());
    }

    @Override
    public int getItemCount() { return plants.size(); }
}
