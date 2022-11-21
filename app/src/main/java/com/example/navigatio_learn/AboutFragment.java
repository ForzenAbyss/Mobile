package com.example.navigatio_learn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {
    FirebaseFirestore firestore;
    DBHelper dbHelper;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        dbHelper = new DBHelper(getContext());
        firestore = FirebaseFirestore.getInstance();
        Button backup = view.findViewById(R.id.Backup);
        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backupData();
            }
        });
        return view;
    }

    private void backupData() {
        ArrayList<String> trip_name;
        ArrayList<String> destination;
        ArrayList<String> date;
        ArrayList<String> risk;
        ArrayList<String> validate;
        destination =(ArrayList<String>) getDest();
        date =(ArrayList<String>) getDate();
        trip_name = (ArrayList<String>) getTripname();
        risk = (ArrayList<String>) getRisk();
        validate = (ArrayList<String>) getValidate();

        DocumentReference documentReference = firestore.collection("Data").document("trip");


        Map<String, Object> hashMap = new HashMap<String, Object>()
        {{
            put("tripName", trip_name);
            put("destination", destination);
            put("date ", date);
            put("risk", risk);
            put("validate", validate);


        }};

        documentReference.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "sucess", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<String> getTripname() {
        return dbHelper.get_trip_nameData();
    }

    public List<String> getDest() {
        return dbHelper.get_trip_destination();
    }
    public List<String> getDate() {
        return dbHelper.get_trip_Date();
    }
    public List<String> getRisk() {
        return dbHelper.get_trip_Risk();
    }
    public List<String> getValidate() {
        return dbHelper.get_trip_Validation();
    }




}