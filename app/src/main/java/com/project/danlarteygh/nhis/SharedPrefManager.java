package com.project.danlarteygh.nhis;

import android.content.Context;
import android.content.SharedPreferences;

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

    public boolean subLogin(String id, String fName, String surname, String otherName, String sex, String telNo, String office, String dob){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_ID, id);
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

    public String gettelNo() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TELNO, null);
    }
    public String getfullName() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString(KEY_USER_SURNAME, null)+" "+sharedPreferences.getString(KEY_USER_FNAME, null)+" "+sharedPreferences.getString(KEY_USER_OTHERNAME, null);
        return fullName;
    }

    public String getfName() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_FNAME, null);
    }

    public String getsurname() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SURNAME, null);
    }

    public String getotherName() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_OTHERNAME, null);
    }
    public String getoffice() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_OFFICE, null);
    }
    public String getdob() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_DOB, null);
    }
    public String getsex() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_SEX, null);
    }
}
