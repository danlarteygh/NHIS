package com.project.danlarteygh.nhis;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by danlarteygh on 4/25/18.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        startActivity(new Intent(SplashActivity.this, Login.class));

        // close splash activity
        finish();
    }
}
