package com.example.android.foodie;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefernesConfig {

    private SharedPreferences sharedPreferences;

    private Context context;

    public SharedPrefernesConfig(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preferences),Context.MODE_PRIVATE);
    }

    public void writeStatus(boolean Status)
    {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.status),Status);

        editor.commit();
    }

    public boolean readLoginStatus()
    {
        boolean status = false;

        status =  sharedPreferences.getBoolean(context.getResources().getString(R.string.status),false);

        return status;
    }


    public void addname(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getResources().getString(R.string.username),name);

        editor.commit();
    }

    public String readname()
    {
        String Username ="";

        Username = sharedPreferences.getString(context.getResources().getString(R.string.username),Username);

        return Username;
    }
    public void addphone(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getResources().getString(R.string.userphone),name);

        editor.commit();
    }

    public String readphone()
    {
        String Userphone ="";

        Userphone = sharedPreferences.getString(context.getResources().getString(R.string.userphone),Userphone);

        return Userphone;
    }
}
