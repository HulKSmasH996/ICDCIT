package com.kiit.viper.icdcit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Schedule extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.snackbarPosition);
        setContentView(R.layout.activity_schedule);

    }

    public void day1(View view) {

        if(isNetworkAvailable(this)) {
            Intent intent = new Intent(Schedule.this, Day1.class);
            startActivity(intent);
        }
         else {
            Snackbar snackbar = Snackbar.make(
                    coordinatorLayout,
                    "No Internet Connection",
                    Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#dc4b3f"));
            snackbar.show();
        }
    }


    public void day2(View view) {
        Intent intent = new Intent(Schedule.this,Day2.class);
        startActivity(intent);
    }

    public void day3(View view) {
        Intent intent = new Intent(Schedule.this,Day3.class);
        startActivity(intent);
    }

    public void day4(View view) {
        Intent intent = new Intent(Schedule.this,Day4.class);
        startActivity(intent);
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

}
