package com.example.navigatio_learn;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisteredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisteredFragment extends Fragment {
    protected EditText NameTour, Destination, DateTour, Risk, Description, Id_Tour;
    protected Button btn_Register_Registered, btn_Register_Delete, btn_Register_Update;
    protected TextView MessageError;
    DBHelper DB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisteredFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegisteredFragment newInstance(String param1, String param2) {
        RegisteredFragment fragment = new RegisteredFragment();
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
        View view = inflater.inflate(R.layout.fragment_registered, container, false);

        btn_Register_Registered = view.findViewById(R.id.btn_Register_Registered);
        btn_Register_Delete = view.findViewById(R.id.btn_Register_Delete);
        btn_Register_Update = view.findViewById(R.id.btn_Register_Update);



        DB = new DBHelper(getContext());

         Id_Tour = view.findViewById(R.id.Tour_ID);
         NameTour = view.findViewById(R.id.NameTour);
         Destination = view.findViewById(R.id.Destination);
         DateTour = view.findViewById(R.id.DateTour);
         Description = view.findViewById(R.id.Description);
         Risk = view.findViewById(R.id.Risk);
         MessageError = view.findViewById(R.id.MessageError);



        //Filter only Tour_ID to adjust and modify the rest of details in the trip.
        btn_Register_Update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Tour_ID = Id_Tour.getText().toString();
                String Journey_ID = NameTour.getText().toString();
                String Destination_ID = Destination.getText().toString();
                String DateTour_ID = DateTour.getText().toString();
                String Risk_ID = Risk.getText().toString();
                String TermOfService_ID = Description.getText().toString();

                Boolean updatedata = DB.UpdateTripData(Tour_ID, Journey_ID, Destination_ID, DateTour_ID, Risk_ID, TermOfService_ID);
                if(updatedata==true)
                    Toast.makeText(getContext(), "New Updated made successfully!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "New Update failed to make!", Toast.LENGTH_SHORT).show();
            }
        });

        //Filter only Tour_ID to delete the all of details in the trip.
        btn_Register_Delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Tour_ID = Id_Tour.getText().toString();
                Boolean deletedata = DB.DeleteData(Tour_ID);
                if(deletedata==true)
                    Toast.makeText(getContext(), "Delete Trip successfully!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Oops! Delete Trip error", Toast.LENGTH_SHORT).show();
            }
        });





            btn_Register_Registered.setOnClickListener(v -> {

            //String to display in Confirmation process
            String TourName = NameTour.getText().toString();
            String PlaceVisit = Destination.getText().toString();
            String DateVisit = DateTour.getText().toString();
            String RiskAssessment = Risk.getText().toString();
            String Detail = Description.getText().toString();



            // Validate user's input.
            if (TourName.isEmpty()) {
                MessageError.setText(R.string.Tourname_Empty);
                return;
            }

            if (PlaceVisit.isEmpty()) {
                MessageError.setText(R.string.Destination_Empty);
                return;
            }
            if (DateVisit.isEmpty()){
                MessageError.setText(R.string.Date_Empty);
                return;
            }

            if (RiskAssessment.isEmpty()){
                MessageError.setText(R.string.Risk_empty);
                return;
            }

            if (Detail.isEmpty()){
                MessageError.setText(R.string.Digits_empty);
                return;
            }

            Bundle storage = new Bundle();
            storage.putString(ConfirmFragment.ARG_PARAM_TOUR, TourName);
            storage.putString(ConfirmFragment.ARG_PARAM_DESTINATION, PlaceVisit);
            storage.putString(ConfirmFragment.ARG_PARAM_DATE, DateVisit);
            storage.putString(ConfirmFragment.ARG_PARAM_RISK, RiskAssessment);
            storage.putString(ConfirmFragment.ARG_PARAM_DESCRIPTION, Detail);

            Toast.makeText(getContext(), "Check & Confirm Information.", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_registeredFragment_to_confirmFragment, storage);
        });


        return view;

    }


}
