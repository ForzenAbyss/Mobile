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
 * Use the {@link Add_Expense_Fragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Expense_Fragment_1 extends Fragment {
    protected EditText expense_price, expense_name, expense_type, expense_time, expense_note;
    protected Button btn_add_expense;
    protected TextView MessageError;
    DBHelper DB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_Expense_Fragment_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Expense_Fragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_Expense_Fragment_1 newInstance(String param1, String param2) {
        Add_Expense_Fragment_1 fragment = new Add_Expense_Fragment_1();
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
        View view = inflater.inflate(R.layout.fragment_add__expense__1, container, false);
        btn_add_expense = view.findViewById(R.id.btn_add_expense);
        expense_name = view.findViewById(R.id.expense_name);
        expense_price = view.findViewById(R.id.expense_price);
        expense_note = view.findViewById(R.id.expense_note);
        expense_time = view.findViewById(R.id.expense_time);
        expense_type = view.findViewById(R.id.expense_type);
        MessageError = view.findViewById(R.id.MessageError);

        DB = new DBHelper(getContext());




        btn_add_expense.setOnClickListener(v -> {

            //String to display in Confirmation process
            String ex_name = expense_name.getText().toString();
            String ex_price = expense_price.getText().toString();
            String ex_note = expense_note.getText().toString();
            String ex_time = expense_time.getText().toString();
            String ex_type = expense_type.getText().toString();



            // Validate user's input.
            if (ex_name.isEmpty()) {
                MessageError.setText(R.string.Ex_name_Empty);
                return;
            }

            if (ex_price.isEmpty()) {
                MessageError.setText(R.string.Ex_price_Empty);
                return;
            }
            if (ex_note.isEmpty()){
                MessageError.setText(R.string.Ex_note_Empty);
                return;
            }

            if (ex_time.isEmpty()){
                MessageError.setText(R.string.Ex_time_Empty);
                return;
            }

            if (ex_type.isEmpty()){
                MessageError.setText(R.string.Ex_type_Empty);
                return;
            }

            String Expense_name = expense_name.getText().toString();
            String Expense_price = expense_price.getText().toString();
            String Expense_note = expense_note.getText().toString();
            String Expense_time = expense_time.getText().toString();
            String Expense_type = expense_type.getText().toString();
            String Expense_tourID = getArguments().getString("tour_id_fr");
            Boolean checkinsertdata = DB.InsertExpenseData(Expense_name, Expense_note, Expense_price , Expense_type, Expense_time, Expense_tourID);
            if(checkinsertdata==true)
                Toast.makeText(getContext(), "Expense is added Successfully!", Toast.LENGTH_SHORT).show();

            else
                Toast.makeText(getContext(), "Data Failed to Insert!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigateUp();


        });



        return view;
    }










}