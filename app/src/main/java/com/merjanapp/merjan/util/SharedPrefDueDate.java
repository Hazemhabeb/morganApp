package com.merjanapp.merjan.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefDueDate {
    // LogCat tag
    private static String TAG = SharedPrefDueDate.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "MerjanApp";

    private static final String KEY_DUE_DATE = "KEY_DUE_DATE";
    private static final String PERSON_ID_CODE = "PERSON_ID";

    public SharedPrefDueDate(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setPersonId(int numSub) {

        editor.putInt(PERSON_ID_CODE, numSub);

        // commit changes
        editor.commit();

    }


    public void setKEY_DUE_DATE(String code) {
        editor.putString(KEY_DUE_DATE, code);

        // commit changes
        editor.commit();
    }

    public String getKEY_DUE_DATE() {
        return pref.getString(KEY_DUE_DATE, "null");
    }


    public int getpersonID() {
        return pref.getInt(PERSON_ID_CODE, 0);
    }
}
