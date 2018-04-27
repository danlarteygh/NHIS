package com.project.danlarteygh.nhis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText editTextID, editTextTelNo;
    private Button bRenew;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextTelNo = (EditText) findViewById(R.id.editTextTelNo);
        bRenew = (Button) findViewById(R.id.bRenew);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

//        buttonLogin.setOnClickListener(this);

    }



    public void openRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void openAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
   /* public void openPayment(View view) {
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    } */
}
