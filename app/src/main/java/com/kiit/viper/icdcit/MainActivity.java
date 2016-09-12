package com.kiit.viper.icdcit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import com.github.clans.fab.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // int[] p = {R.drawable., R.drawable.image2....}
  ProgressBar progressBar;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;
    ViewFlipper viewFlipper;
    TextView textView;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       coordinatorLayout=(CoordinatorLayout)findViewById(R.id.snackbarPosition);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        setSupportActionBar(toolbar);
        textView =(TextView)findViewById(R.id.text);
       if(!isNetworkAvailable(this)){
            Snackbar snackbar = Snackbar.make(
                    coordinatorLayout,
                    "No Internet Connection",
                    Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#dc4b3f"));
            snackbar.show();}
            new JsonTask().execute("https://api.myjson.com/bins/4fqed");
        viewFlipper.startFlipping();
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_facebook);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floating_twitter);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floating_google_plus);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.floating_instagram);

           /*

        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent googleplusIntent = getOpenGPlusIntent(MainActivity.this);
                startActivity(googleplusIntent);
            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent instagramIntent = getOpenInstagramIntent(MainActivity.this);
                startActivity(instagramIntent);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            new JsonTask().execute("https://api.myjson.com/bins/4fqed");
            if(!isNetworkAvailable(this)) {
                Snackbar snackbar = Snackbar.make(
                        coordinatorLayout,
                        "No Internet Connection",
                        Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.WHITE);
               View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.parseColor("#dc4b3f"));
                snackbar.show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (id == R.id.nav_registration) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_dates) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, ImpDates.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
            //Calling this method to close this activity when internet is not available

        } else if (id == R.id.nav_schedule) {
            Intent intent = new Intent(MainActivity.this, Schedule.class);
            startActivity(intent);


        } else if (id == R.id.nav_speakers) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, Speakers.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_call) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, CallPapers.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();



        }else if (id == R.id.nav_indsym) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, IndustrySymposium.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_pic) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, ProjectInnovation.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_srs) {
            if (isNetworkAvailable(this)) {
                Intent intent = new Intent(MainActivity.this, StudentResearchSymposium.class);
                startActivity(intent);

            } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_committee) {
            Intent intent = new Intent(MainActivity.this, ConferenceCommittee.class);
            startActivity(intent);


        }else if (id == R.id.nav_mail) {
            Intent intent = new Intent(MainActivity.this, Mail.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/icdcit/?fref=ts")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/icdcit/?fref=ts")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenTwitterIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0); //Checks if Twitter is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/icdcit")); //Trys to make intent with Twitter's's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/icdcit")); //catches and opens a url to the desired page
        }
    }


    public static Intent getOpenGMailIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.gm", 0); //Checks if G+ is even installed.
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setType("plain/text");
            sendIntent.setData(Uri.parse("icdcit@kiit.ac.in"));
            sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"icdcit@kiit.ac.in"});
            return sendIntent;//Trys to make intent with G+'s URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("mailto:" + "icdcit@kiit.ac.in")); //catches and opens a url to the desired page
        }
    }


    public void FacebookIntent(View view) {
        Intent facebookIntent = getOpenFacebookIntent(MainActivity.this);
        startActivity(facebookIntent);
    }

    public void TwitterIntent(View view) {
        Intent twitterIntent = getOpenTwitterIntent(MainActivity.this);
        startActivity(twitterIntent);

    }

    public void GmailIntent(View view) {
        Intent googleplusIntent = getOpenGMailIntent(MainActivity.this);
        startActivity(googleplusIntent);
    }

    public void GoogleMapsIntent(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);

    }

    public void IndustrySymposium(View view) {
        if (isNetworkAvailable(this)) {
            Intent intent = new Intent(MainActivity.this, IndustrySymposium.class);
            startActivity(intent);

        } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
    }

    public void ProjectInnovationContest(View view) {
        if (isNetworkAvailable(this)) {
            Intent intent = new Intent(MainActivity.this, ProjectInnovation.class);
            startActivity(intent);

        } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
    }

    public void StudentResearchSymposium(View view) {
        if (isNetworkAvailable(this)) {
            Intent intent = new Intent(MainActivity.this,StudentResearchSymposium.class);
            startActivity(intent);

        } else Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
    }

    class JsonTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                String Json = buffer.toString();
                JSONObject parentObject = new JSONObject(Json);
                JSONArray jsonArray = parentObject.getJSONArray("icdcit");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("p1");
                String p2=jsonObject.getString("p2");
                return p1+"\n"+p2;

                //return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);

            try {
                textView.setText(s);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                textView.setText("Refresh");
            }

        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
    }
}

