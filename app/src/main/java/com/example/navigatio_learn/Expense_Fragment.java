package com.example.navigatio_learn;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Expense_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Expense_Fragment extends Fragment {
    RecyclerView recyclerview_Expense;
    ArrayList<String>  Expense_Type, Expense_Note, Expense_Price, Expense_Name, Expense_Time;
    TextView Trip_Expense, Date_Expense, Destination_Expense, Risk_expense, Digital_expense;
    DBHelper dbHelper;
    Button add_to_expense;
    ExpenseAdapter Adapter;
    int trip_id_fr;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Expense_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Expense_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Expense_Fragment newInstance(String param1, String param2) {
        Expense_Fragment fragment = new Expense_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expense, container, false);
        dbHelper = new DBHelper(getContext());
        recyclerview_Expense = view.findViewById(R.id.recyclerview_Expense);
        /*tour_id = dbHelper.DisplayID();*/
        Trip_Expense = view.findViewById(R.id.Trip_Expense);
        Date_Expense = view.findViewById(R.id.Date_Expense);
        Destination_Expense = view.findViewById(R.id.Destination_Expense);
        Risk_expense = view.findViewById(R.id.Risk_Expense);
        Digital_expense = view.findViewById(R.id.Digital_Expense);
        add_to_expense = view.findViewById(R.id.To_Add_expense);
        Expense_Type = new ArrayList<>();
        Expense_Price = new ArrayList<>();
        Expense_Note = new ArrayList<>();
        Expense_Time = new ArrayList<>();
        Expense_Name = new ArrayList<>();



        Toast.makeText(getContext(), getArguments().getString("tour_id"),Toast.LENGTH_SHORT).show();
        String id = getArguments().getString("tour_id");


        if (getArguments() != null) {
            String Digits = getArguments().getString("name_tour");
            Trip_Expense.setText(Digits);
        }
        if (getArguments() != null) {
            String Digits = getArguments().getString("DateTour");
            Date_Expense.setText(Digits);
        }
        if (getArguments() != null) {
            String Digits = getArguments().getString("destination_tour");
            Destination_Expense.setText(Digits);
        }
        if (getArguments() != null) {
            String Digits = getArguments().getString("Risk_List");
            Risk_expense.setText(Digits);
        }
        if (getArguments() != null) {
            String Digits = getArguments().getString("Description");
            Digital_expense.setText(Digits);
        }
        add_to_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("tour_id_fr",id );
                Navigation.findNavController(view).navigate(R.id.action_expense_Fragment_to_add_Expense_Fragment_1, bundle);
            }
        });
        Adapter = new ExpenseAdapter(getContext(), Expense_Type, Expense_Note, Expense_Price, Expense_Name, Expense_Time);
        recyclerview_Expense.setAdapter(Adapter);
        recyclerview_Expense.setLayoutManager( new LinearLayoutManager(getContext()));
        trip_id_fr = Integer.parseInt(id);
        displaydata();




        return view;
    }
    private void displaydata() {
        Cursor cursor = dbHelper.DisplayExpense(trip_id_fr);
        if(cursor.getCount()==0)
        {
            Toast.makeText(getContext(),"Please input data", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                //DBHelper.java at insertuserdata section (get position as int)
                Expense_Type.add(cursor.getString(1));
                Expense_Time.add(cursor.getString(2));
                Expense_Price.add(cursor.getString(3)); // row 1 as Destination
                Expense_Note.add(cursor.getString(4)); // row 0 put it in NameTour
                Expense_Name.add(cursor.getString(5));




            }
        }

    }


}