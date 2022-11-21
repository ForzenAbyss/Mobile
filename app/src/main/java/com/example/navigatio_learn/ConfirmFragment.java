package com.example.navigatio_learn;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmFragment extends Fragment {
    public static final String ARG_PARAM_TOUR = "name";
    public static final String ARG_PARAM_DESTINATION = "destination";
    public static final String ARG_PARAM_DATE = "date";
    public static final String ARG_PARAM_RISK = "risk";
    public static final String ARG_PARAM_DESCRIPTION = "detail";
    protected  Button btn_Confirm_Confirm, btn_Confirm_Edit;
    protected  TextView Confirm_Journey, Confirm_Place, Confirm_Date, Confirm_Risk, Confirm_Digits;
    protected  CheckBox CheckTest;
    DBHelper DB;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfirmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmFragment newInstance(String param1, String param2) {
        ConfirmFragment fragment = new ConfirmFragment();
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
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        //Verify id from the xml file
        Confirm_Journey = view.findViewById(R.id.Confirm_Journey);
        Confirm_Place = view.findViewById(R.id.Confirm_Place);
        Confirm_Date = view.findViewById(R.id.Confirm_Date);
        Confirm_Risk = view.findViewById(R.id.Confirm_Risk);
        Confirm_Digits = view.findViewById(R.id.Confirm_Digits);
        btn_Confirm_Confirm = view.findViewById(R.id.btn_Confirm_Confirm);
        btn_Confirm_Edit = view.findViewById(R.id.btn_Confirm_Edit);
        DB = new DBHelper(getContext());


        //Set evenListener for CheckBox
        CheckTest = view.findViewById(R.id.checkTest);
        CheckTest.setOnClickListener(checkTestClick);






        //If any existed value is entered from registration form, display inputs from the string in RegisteredFragment
        if (getArguments() != null) {
            String Journey = getArguments().getString(ARG_PARAM_TOUR);
            Confirm_Journey.setText(Journey);
        }

        if (getArguments() != null) {
            String Place = getArguments().getString(ARG_PARAM_DESTINATION);
            Confirm_Place.setText(Place);
        }

        if (getArguments() != null) {
            String Date = getArguments().getString(ARG_PARAM_DATE);
            Confirm_Date.setText(Date);
        }

        if (getArguments() != null) {
            String Risk = getArguments().getString(ARG_PARAM_RISK);
            Confirm_Risk.setText(Risk);
        }

        if (getArguments() != null) {
            String Digits = getArguments().getString(ARG_PARAM_DESCRIPTION);
            Confirm_Digits.setText(Digits);
        }

        // Click EditButton to return to Registration phase
        btn_Confirm_Edit.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Return To Registration!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.registeredFragment);;
        });

        // Click ConfirmedButton to verify the information
        btn_Confirm_Confirm.setEnabled(false);
        //Creating confirm button and embedding insert function into this button as well
        //Insert Function
        btn_Confirm_Confirm.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
            String Journey_ID = Confirm_Journey.getText().toString();
            String Destination_ID = Confirm_Place.getText().toString();
            String DateTour_ID = Confirm_Date.getText().toString();
            String Risk_ID = Confirm_Risk.getText().toString();
            String TermOfService_ID = Confirm_Digits.getText().toString();
            Boolean checkinsertdata = DB.InsertTripData(Journey_ID, Destination_ID, DateTour_ID, Risk_ID, TermOfService_ID);
            if(checkinsertdata==true)
                Toast.makeText(getContext(), "Trip is added Successfully!", Toast.LENGTH_SHORT).show();

            else
                Toast.makeText(getContext(), "Data Failed to Insert!", Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    protected View.OnClickListener checkTestClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CheckBox checkTest =(CheckBox) view;

            if(checkTest.isChecked()){
                btn_Confirm_Confirm.setEnabled(true);
                return;
            }
            btn_Confirm_Confirm.setEnabled(false);
        }

    };






}