package com.project.danlarteygh.nhis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
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

        bRenew.setOnClickListener(this);

    }

    private void subLogin() {
        final String id = editTextID.getText().toString().trim();
        final String telNo = editTextTelNo.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .subLogin(
                                                obj.getString("id"),
                                                obj.getString("fName"),
                                                obj.getString("surname"),
                                                obj.getString("otherName"),
                                                obj.getString("sex"),
                                                obj.getString("telNo"),
                                                obj.getString("office"),
                                                obj.getString("dob")
                                        );
                                editTextTelNo.setText("");
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                              //  finish();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("telNo", telNo);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void openRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == bRenew) {
            subLogin();

        }
    }
}
