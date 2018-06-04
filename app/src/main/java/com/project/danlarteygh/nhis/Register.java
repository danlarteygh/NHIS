package com.project.danlarteygh.nhis;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import android.widget.TextView;
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

    private EditText editDate, e_fName, e_surname, e_otherName, editText_dob, e_telNo,e_ssnitNo;
    Spinner s_sex, s_office;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    private ProgressDialog progressDialog;
    private RadioGroup radioGroup;
    private RadioGroup radioGroupSex;
    private String pregnantt="";
    String rbSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RadioButton rbp = (RadioButton) findViewById(R.id.rbp);
        final TextView tv16 = Register.this.findViewById(R.id.textView16);
        final TextView tv18 = Register.this.findViewById(R.id.textView18);
        final TextView tv19 = Register.this.findViewById(R.id.textView19);
        final TextView tv20 = Register.this.findViewById(R.id.textView20);
        final TextView tv21 = Register.this.findViewById(R.id.textView21);
        final TextView tv = Register.this.findViewById(R.id.textViewSSNIT);
        final EditText ssnitNo= Register.this.findViewById(R.id.e_ssnitNo);
        final EditText telNo2= Register.this.findViewById(R.id.e_telNo2);
        final EditText e_name= Register.this.findViewById(R.id.e_name);
        final Button btn = (Button) findViewById(R.id.button2);
        final RadioButton rbs = (RadioButton) findViewById(R.id.rbs);
        final Spinner spinnerNetwork = findViewById(R.id.spinner);


        // Spinner element
        Spinner spinner = findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("");
        categories.add("MTN Mobile Money");
        categories.add("Vodafone Cash");
        categories.add("Tigo Cash");
        categories.add("Airtel Money");

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroupSex = (RadioGroup) findViewById(R.id.radioGroupSex);
        radioGroupSex.clearCheck();
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                       if (rbs.isChecked())
                        {
                            tv.setVisibility(View.VISIBLE);
                            ssnitNo.setVisibility(View.VISIBLE);
                            }
                        else if (rbp.isChecked()){
                            tv.setVisibility(View.INVISIBLE);
                            ssnitNo.setVisibility(View.INVISIBLE);
                            pregnantt="YES";
                        }
                    tv16.setVisibility(View.INVISIBLE);
                    tv18.setVisibility(View.INVISIBLE);
                    tv19.setVisibility(View.INVISIBLE);
                    tv20.setVisibility(View.INVISIBLE);
                    tv21.setVisibility(View.INVISIBLE);
                    spinnerNetwork.setVisibility(View.INVISIBLE);
                    telNo2.setVisibility(View.INVISIBLE);
                    e_name.setVisibility(View.INVISIBLE);

                    //set button's new text programmatically
                        //setText() method allow us to set a widget's displayed text
                        btn.setText("SUBMIT");
                  }
            }
        });
        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbx = (RadioButton) group.findViewById(checkedId);
                rbSex=rbx.getText().toString();
                if (null != rbx && checkedId > -1) {
                    RadioButton rbMale = (RadioButton) findViewById(R.id.rbMale);
                        RadioButton rbFemale = (RadioButton) findViewById(R.id.rbFemale);

                        if (rbMale.isChecked())
                        {
                            rbp.setVisibility(View.INVISIBLE);
                        }
                        else if (rbFemale.isChecked()){
                            rbp.setVisibility(View.VISIBLE);
                        }
                }
            }
        });
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
        categories.add("");
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

// set calendar date and update editDate
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
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

        e_fName = findViewById(R.id.e_fName);
        e_surname = findViewById(R.id.e_surname);
        e_otherName = findViewById(R.id.e_otherName);
        editText_dob = findViewById(R.id.editText_dob);
        s_office = findViewById(R.id.s_office);
        e_telNo= findViewById(R.id.e_telNo);
        e_ssnitNo= findViewById(R.id.e_ssnitNo);
        progressDialog = new ProgressDialog(this);
    }
    private void registerSub() {
        final String fName = e_fName.getText().toString().trim();
        final String surname = e_surname.getText().toString().trim();
        final String otherName = e_otherName.getText().toString().trim();
        final String dob = editDate.getText().toString().trim();
        final String sex= rbSex;
        final String password = editText_dob.getText().toString().trim();
        final String telNo = e_telNo.getText().toString();
        final String office = s_office.getSelectedItem().toString();
        final String ssnitNo = e_ssnitNo.getText().toString();
        final String pregnant= pregnantt;

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
                            Intent intent = new Intent(Register.this, About.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }}

    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
            Log.d("ssnitNo", ssnitNo );
            Log.d("pregnant", pregnant );

            params.put("sex", sex);
            params.put("fName", fName);
            params.put("surname", surname);
            params.put("otherName", otherName );
            params.put("dob", dob );
            params.put("telNo", telNo );
            params.put("office", office );
            params.put("ssnitNo", ssnitNo );
            params.put("pregnant", pregnant );

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void view_offices(View view) {
        Intent intent = new Intent(this, Offices.class);
        startActivity(intent);
    }
    public void openPayment(View view) {
        registerSub();
    }
}
