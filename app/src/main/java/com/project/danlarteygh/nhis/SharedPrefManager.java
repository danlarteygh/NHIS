package com.project.danlarteygh.nhis;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by danlarteygh on 3/19/18.
 */

public class SharedPrefManager {

    private static SharedPrefManager mInstance;

    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_TELNO = "usertelno";
    private static final String KEY_USER_FNAME = "userfname";
    private static final String KEY_USER_SURNAME = "usersurname";
    private static final String KEY_USER_OTHERNAME = "userothername";
    private static final String KEY_USER_SEX = "usersex";
    private static final String KEY_USER_OFFICE = "useroffice";
    private static final String KEY_USER_DOB = "userdob";


    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean subLogin(int id, String fName, String surname, String otherName, String sex, String telNo, String office, String dob){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_FNAME,fName);
        editor.putString(KEY_USER_SURNAME, surname);
        editor.putString(KEY_USER_OTHERNAME,otherName);
        editor.putString(KEY_USER_SEX,sex);
        editor.putString(KEY_USER_TELNO,telNo);
        editor.putString(KEY_USER_OFFICE,office);
        editor.putString(KEY_USER_DOB,dob);


        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_ID, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


    public String getID(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

  /*  public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
*/



}
