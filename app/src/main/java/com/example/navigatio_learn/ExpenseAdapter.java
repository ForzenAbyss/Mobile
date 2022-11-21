package com.example.navigatio_learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>  {
    private Context context;
    ArrayList<String>  Expense_Type, Expense_Note, Expense_Price, Expense_Name, Expense_Time;

    public ExpenseAdapter(Context context, ArrayList<String> expense_type, ArrayList<String> expense_note,ArrayList<String> expense_time ,ArrayList<String> expense_name, ArrayList<String> expense_price) {
        this.context = context;
        this.Expense_Type = expense_type;
        this.Expense_Note = expense_note;
        this.Expense_Price = expense_price;
        this.Expense_Time = expense_time;
        this.Expense_Name = expense_name;

    }

    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displayexpense,parent,false);
        return new MyViewHolder(v);

    }



    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder, int position) {
        holder.Expense_Type.setText(String.valueOf(Expense_Type.get(position)));
        holder.Expense_Note.setText(String.valueOf(Expense_Note.get(position)));
        holder.Expense_Price.setText(String.valueOf(Expense_Price.get(position)));
        holder.Expense_Time.setText(String.valueOf(Expense_Time.get(position)));
        holder.Expense_Name.setText(String.valueOf(Expense_Name.get(position)));
    }


    @Override
    public int getItemCount() {
        return Expense_Type.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Expense_Type, Expense_Note, Expense_Price, Expense_Name, Expense_Time;
        CardView Expense_ID;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Expense_Type = itemView.findViewById(R.id.Expense_Type);
            Expense_Note = itemView.findViewById(R.id.Expense_Note);
            Expense_Price = itemView.findViewById(R.id.Expense_Price);
            Expense_Time = itemView.findViewById(R.id.Expense_Time);
            Expense_Name = itemView.findViewById(R.id.Expense_Name);
            Expense_ID = itemView.findViewById(R.id.Expense_ID);

            /*Trip_ID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_listFragment_to_expense_Fragment);
                }
            });*/

        }

    }




}
