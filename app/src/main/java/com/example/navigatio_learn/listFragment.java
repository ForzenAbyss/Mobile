package com.example.navigatio_learn;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link listFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<String> Tour_id, NameTour, Destination, DateTour, Risk, Description;
    DBHelper DB;
    MyAdapter adapter;
    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public listFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static listFragment newInstance(String param1, String param2) {
        listFragment fragment = new listFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        DB = new DBHelper(getContext());
        NameTour = new ArrayList<>();
        Destination = new ArrayList<>();
        DateTour = new ArrayList<>();
        Risk = new ArrayList<>();
        Description = new ArrayList<>();
        Tour_id = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        searchView = view.findViewById(R.id.sv_trip);
        adapter = new MyAdapter(getContext(), Tour_id, NameTour, Destination, DateTour, Risk, Description);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        displaydata();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchtrip(s);
                return true;
            }
        });
    }

    private void searchtrip(String s) {
        ArrayList<String> str = new ArrayList<String>();

        adapter = new MyAdapter(getContext(), Tour_id, str, Destination, DateTour, Risk, Description);


       for (String n : NameTour){

           if (n.toLowerCase(Locale.ROOT).contains(s.toLowerCase(Locale.ROOT))){
                str.add(n);
               //Toast.makeText(requireContext(), str.size(), Toast.LENGTH_SHORT).show();
           }
       }
       recyclerView.setAdapter(adapter);

    }

    //This function is used to manifest data from the database
    private void displaydata() {
        Cursor cursor = DB.DisplayData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "Please input data", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                //DBHelper.java at insertuserdata section (get position as int)
                Tour_id.add(cursor.getString(0));
                NameTour.add(cursor.getString(1)); // row 1 put it in NameTour
                Destination.add(cursor.getString(2)); // row 2 as Destination
                DateTour.add(cursor.getString(3)); // row 3 as DateTour
                Risk.add(cursor.getString(4)); // row 4 as Risk
                Description.add(cursor.getString(5)); // row 5 as Description

            }
        }

    }

}
