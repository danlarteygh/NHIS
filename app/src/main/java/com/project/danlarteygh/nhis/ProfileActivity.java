package com.project.danlarteygh.nhis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textViewID, textViewtelNo, textViewfullName, textViewOffice, textViewSex, textViewDOB;
    private RadioGroup radioGroup;
    ScrollView scrollvieww ;
    ProgressDialog progressDialog1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       final Spinner spinnerNetwork = findViewById(R.id.spinner2);
        final RadioButton rbp = (RadioButton) findViewById(R.id.rbp);
        final TextView tv22 = ProfileActivity.this.findViewById(R.id.textView22);
        final TextView tv23 = ProfileActivity.this.findViewById(R.id.textView23);
        final TextView tv24 = ProfileActivity.this.findViewById(R.id.textView24);
        final TextView tv25 = ProfileActivity.this.findViewById(R.id.textView25);
        final TextView tv26 = ProfileActivity.this.findViewById(R.id.textView26);
        final TextView tv27 = ProfileActivity.this.findViewById(R.id.textView27);
        final TextView tv28 = ProfileActivity.this.findViewById(R.id.textView28);
        final EditText telNo3= ProfileActivity.this.findViewById(R.id.e_telNo3);
        final EditText e_name2= ProfileActivity.this.findViewById(R.id.e_name2);
        final Button btn = (Button) findViewById(R.id.button3);
        final RadioButton rbs = (RadioButton) findViewById(R.id.rbs);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();




        // Creating adapter for spinner

        // Spinner element
        Spinner spinner = findViewById(R.id.spinner2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        categories.add("MTN Mobile Money");
        categories.add("Vodafone Cash");
        categories.add("Tigo Cash");
        categories.add("Airtel Money");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        }
        if (SharedPrefManager.getInstance(this).getsex().toString()=="MALE"){
            tv27.setVisibility(View.INVISIBLE);
            tv28.setVisibility(View.INVISIBLE);
            radioGroup.setVisibility(View.INVISIBLE);
        }
        textViewID = (TextView) findViewById(R.id.textViewID);
        textViewtelNo = (TextView) findViewById(R.id.textViewTelNo);
        textViewfullName = (TextView) findViewById(R.id.textViewFullName);
        textViewOffice = (TextView) findViewById(R.id.textViewOffice);
        textViewSex = (TextView) findViewById(R.id.textViewSex);
        textViewDOB = (TextView) findViewById(R.id.textViewDOB);


        textViewtelNo.setText(SharedPrefManager.getInstance(this).gettelNo());
        textViewID.setText(SharedPrefManager.getInstance(this).getID());
        textViewfullName.setText(SharedPrefManager.getInstance(this).getfullName());
        textViewOffice.setText(SharedPrefManager.getInstance(this).getoffice());
        textViewSex.setText(SharedPrefManager.getInstance(this).getsex());
        textViewDOB.setText(SharedPrefManager.getInstance(this).getdob());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                   /* if (rb.getText()==rb.getText())
                    {*/
                    //  RadioButton rbp = (RadioButton) findViewById(R.id.rbp);


                     if (rbp.isChecked()){
                         // tv21.setVisibility(View.INVISIBLE);

                    }
                    tv22.setVisibility(View.INVISIBLE);
                    tv23.setVisibility(View.INVISIBLE);
                    tv24.setVisibility(View.INVISIBLE);
                    tv25.setVisibility(View.INVISIBLE);
                    tv26.setVisibility(View.INVISIBLE);
                    spinnerNetwork.setVisibility(View.INVISIBLE);
                    telNo3.setVisibility(View.INVISIBLE);
                    e_name2.setVisibility(View.INVISIBLE);

                    //set button's new text programmatically
                    //setText() method allow us to set a widget's displayed text
                    btn.setText("SUBMIT");
                   // scrollvieww.scrollTo(0, (int) btn.getY());
                    //  focusOnView();

                    //   }
                    //Toast.makeText(Register.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       /* final String sexx = s_sex.getSelectedItem().toString();
        if (sexx=="MALE"){rbp.setVisibility(View.VISIBLE);}*/
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void submitRenewal(View view) {
     /*   progressDialog1.setMessage("Registering user...");
        progressDialog1.show();*/
        Toast.makeText(ProfileActivity.this, "NHIS Renewed Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, About.class);
        startActivity(intent);}





}
