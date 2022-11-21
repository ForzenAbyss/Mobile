package com.example.navigatio_learn;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    private Context context;
    CardView Trip_ID;
    ArrayList<String> Tour_id, NameTour_list, Destination_list, DateTour_list, Risk_list, Description_list;

    public MyAdapter(Context context, ArrayList<String> tour_id, ArrayList<String> nameTour_list, ArrayList<String> destination_list, ArrayList<String> dateTour_list, ArrayList<String> risk_list, ArrayList<String> description_list) {
        this.context = context;
        this.NameTour_list = nameTour_list;
        this.Tour_id = tour_id;
        this.Destination_list = destination_list;
        this.DateTour_list = dateTour_list;
        this.Risk_list = risk_list;
        this.Description_list = description_list;

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displaytrip,parent,false);
        return new MyViewHolder(v);

    }



    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.Tour_id.setText(String.valueOf(Tour_id.get(position)));
        holder.NameTour_list.setText(String.valueOf(NameTour_list.get(position)));
        holder.Destination_list.setText(String.valueOf(Destination_list.get(position)));
        holder.DateTour_list.setText(String.valueOf(DateTour_list.get(position)));
        holder.Risk_list.setText(String.valueOf(Risk_list.get(position)));
        holder.Description_list.setText(String.valueOf(Description_list.get(position)));
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("tour_id", Tour_id.get(position));
            bundle.putString("name_tour", NameTour_list.get(position));
            bundle.putString("destination_tour", Destination_list.get(position));
            bundle.putString("DateTour", DateTour_list.get(position));
            bundle.putString("Risk_List", Risk_list.get(position));
            bundle.putString("Description", Description_list.get(position));
            Navigation.findNavController(view).navigate(R.id.action_listFragment_to_expense_Fragment, bundle);
        });



    }


    @Override
    public int getItemCount() {

        return NameTour_list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NameTour_list, Destination_list, DateTour_list, Description_list, Risk_list, Tour_id;
        CardView Trip_ID;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            NameTour_list = itemView.findViewById(R.id.TourName_display);
            Destination_list = itemView.findViewById(R.id.Destination_display);
            DateTour_list = itemView.findViewById(R.id.DateTour_display);
            Risk_list = itemView.findViewById(R.id.Risk_display);
            Description_list = itemView.findViewById(R.id.Description_display);
            Tour_id = itemView.findViewById(R.id.Tour_id);
            Trip_ID = itemView.findViewById(R.id.Trip_ID);



        }


    }




}
