package com.example.questionlarry;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link Form#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Form<DatabaseReference> extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button date_button,submit,date_BT ;
    private com.google.firebase.database.DatabaseReference mDatabase;
    EditText name_ET,expectations_ET;
    Spinner location_SP,department_SP;
    RadioGroup gender_RG,year_of_study_RG;
    CheckBox hobbies_CB;
    RadioButton gender_selected_RB;


    public Form() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Form newInstance(String param1, String param2) {
        Form fragment = new Form();
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
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        Spinner l_spinner = view.findViewById(R.id.location_SP);
        Spinner d_spinner = view.findViewById(R.id.department_SP);

        ArrayAdapter<CharSequence> location_sp_adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.locations,android.R.layout.simple_spinner_item);
        location_sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        l_spinner.setAdapter(location_sp_adapter);

        ArrayAdapter<CharSequence> department_sp_adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.departments,android.R.layout.simple_spinner_item);
        department_sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        d_spinner.setAdapter(department_sp_adapter);

        String text = l_spinner.getSelectedItem().toString();

        date_button = view.findViewById(R.id.date_BT);

        date_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                date_button.setText(day + "/" + month + "/" + year);

                            }
                        }, year , month , dayOfMonth);

                datePickerDialog.show();

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();

        submit = view.findViewById(R.id.submit_BT);
        name_ET = view.findViewById(R.id.name_ET);
        location_SP = view.findViewById(R.id.location_SP);
        date_BT = view.findViewById(R.id.date_BT);
        gender_RG = view.findViewById(R.id.gender_RD);
        hobbies_CB = view.findViewById(R.id.hobbies_CB);
        department_SP = view.findViewById(R.id.department_SP);
        year_of_study_RG = view.findViewById(R.id.yearostudy_RD);
        expectations_ET = view.findViewById(R.id.expectations_ET);


        // change rb state to checked after getting it's text


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = name_ET.getText().toString();
                String location = location_SP.getSelectedItem().toString();
                String date = date_BT.getText().toString();
                String gender;

                //Toast.makeText(getContext(),gender_selected_RB.getText(),Toast.LENGTH_SHORT).show();

                String hobbies;

                if(hobbies_CB.isChecked())
                {
                     hobbies = "Yes";
                }else{
                    hobbies = "No";
                }

                String department = department_SP.getSelectedItem().toString();

                //int year_of_study_checked_id = year_of_study_RG.getCheckedRadioButtonId();
               // RadioButton year_of_study_selected_RB = view.findViewById(year_of_study_checked_id);
               // String year_of_study = year_of_study_selected_RB.getText().toString();

                String expectations = expectations_ET.getText().toString();

                Person person = new Person(name,location,date,"ferfi",hobbies,department,"elsoev",expectations);

                mDatabase.child("Persons").child(name).setValue(person);

            }
        });

        return view;
    }

    // public void StoreData(View view){





        // change rb state to checked after getting it's text
       /* int gender_checked_id = gender_RG.getCheckedRadioButtonId();
        RadioButton gender_selected_RB = view.findViewById(gender_checked_id);
        String gender = gender_selected_RB.getText().toString();

        String hobbies;

        if(hobbies_CB.isChecked())
        {
             hobbies = "Yes";
        }else{
            hobbies = "No";
        }

        String department = department_SP.getSelectedItem().toString();

        int year_of_study_checked_id = year_of_study_RG.getCheckedRadioButtonId();
        RadioButton year_of_study_selected_RB = view.findViewById(year_of_study_checked_id);
        String year_of_study = year_of_study_selected_RB.getText().toString();

        String expectations = expectations_ET.getText().toString();

        Person person = new Person(name,location,date,gender,hobbies,department,year_of_study,expectations);

        mDatabase.child("Persons").setValue(person);
        */
   // }


}
