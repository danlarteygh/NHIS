package com.project.danlarteygh.nhis;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Register extends AppCompatActivity implements OnItemSelectedListener {
    Context context = this;

    private EditText editDate, e_fName, e_surname, e_otherName, editText_dob, e_telNo;
    Spinner s_sex, s_office;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Spinner element
        Spinner spinner = findViewById(R.id.s_sex);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("MALE");
        categories.add("FEMALE");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //OFFICE SPINNER
        // Spinner element
        spinner = findViewById(R.id.s_office);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        categories = new ArrayList<String>(0);
        categories.add("REGIONAL OFFICE");
        categories.add("ABLEKUMA");
        categories.add("ASHIEDU KETEKE");
        categories.add("AYAWASO");
        categories.add("DANGBE EAST");
        categories.add("DANGBE WEST");
        categories.add("GA DISTRICT");
        categories.add("KPESHIE");
        categories.add("OKAIKOI");
        categories.add("OSU KLOTTEY");
        categories.add("TEMA");


        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        editDate = findViewById(R.id.editText_dob);

// init - set date to current date
       /* long currentdate = System.currentTimeMillis();
        String dateString = sdf.format(currentdate);
        editDate.setText(dateString); */

// set calendar date and update editDate
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //        DatePickerDialog.getdatepicker().setMaxDate(myCalendar.getTimeInMillis());
                updateDate();


            }

        };

// onclick - popup datepicker
        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        s_sex = findViewById(R.id.s_sex);
        e_fName = findViewById(R.id.e_fName);
        e_surname = findViewById(R.id.e_surname);
        e_otherName = findViewById(R.id.e_otherName);
        editText_dob = findViewById(R.id.editText_dob);
        s_office = findViewById(R.id.s_office);
        e_telNo= findViewById(R.id.e_telNo);

        progressDialog = new ProgressDialog(this);



    }


    private void registerSub() {
        final String fName = e_fName.getText().toString().trim();
        final String surname = e_surname.getText().toString().trim();
        final String otherName = e_otherName.getText().toString().trim();
        final String dob = editDate.getText().toString().trim();
        // final String sex= s_sex.getText().toString().trim();
//   final String password = password.getText().toString().trim();
        final String password = editText_dob.getText().toString().trim();
        final String telNo = e_telNo.getText().toString();
        final String office = s_office.getSelectedItem().toString();
        final String sex = s_sex.getSelectedItem().toString();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register.this, Payment.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }}

    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Log.e("server response", "onResponse: "+response );
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
@Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            Log.d("firstname", fName);
            Log.d("surname", surname);
            Log.d("othername", otherName);
            Log.d("dob", dob );
            Log.d("sex", sex );
            Log.d("telNo", telNo );
            Log.d("office", office );

            params.put("sex", sex);
            params.put("fName", fName);
            params.put("surname", surname);
            params.put("otherName", otherName );
            params.put("dob", dob );
            params.put("telNo", telNo );
            params.put("office", office );
 //continue here
           /* params.put("otherName",  );
            params.put("otherName", otherName );
            params.put("otherName", otherName );*/
            Log.d("params", params.toString());
            return params;
        }
    };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void updateDate() {
        editDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void view_offices(View view) {
        Intent intent = new Intent(this, Offices.class);
        startActivity(intent);
    }
    public void openPayment(View view) {
        registerSub();


    }






}
