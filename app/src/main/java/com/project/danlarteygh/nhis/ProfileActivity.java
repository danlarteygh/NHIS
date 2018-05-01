package com.project.danlarteygh.nhis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewID, textViewtelNo, textViewfullName, textViewOffice, textViewSex, textViewDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
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
    }
}
