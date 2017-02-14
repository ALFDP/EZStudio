package com.alfdp.ezstudio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_SCREEN_TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        checkFirstRun();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int NOT_EXIST = -1;


        // Get current version code
        int currentVersionCode = 0;
        try {
            currentVersionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            // handle exception
            e.printStackTrace();
            return;
        }

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, NOT_EXIST);

        // Check for first run or upgrade

        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            Log.e("First RUN", "FALSE");
            Toast.makeText(this, "LATEST UPDATE", Toast.LENGTH_SHORT).show();
            return;

        } else if (savedVersionCode == NOT_EXIST) {

            // TODO This is a new install (or the user cleared the shared preferences)
            Log.e("First RUN", "TRUE");
            Toast.makeText(this, "FIRST CONFIG", Toast.LENGTH_SHORT).show();

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
            Log.e("First RUN", "UPGRADE");
            Toast.makeText(this, "UPGRADE CONFIG", Toast.LENGTH_SHORT).show();

        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).commit();

    }
}
