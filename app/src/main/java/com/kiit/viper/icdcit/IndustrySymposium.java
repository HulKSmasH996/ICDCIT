package com.kiit.viper.icdcit;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class IndustrySymposium extends AppCompatActivity {


    ProgressBar progressBar1;
    ProgressBar progressBar2;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_symposium);
        progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2) ;
        textView1=(TextView)findViewById(R.id.text1);
        textView2=(TextView)findViewById(R.id.text3);
        new JsonTask1().execute("https://api.myjson.com/bins/1segd");
        new JsonTask2().execute("https://api.myjson.com/bins/3zpe5");



    }
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
            new JsonTask1().execute("https://api.myjson.com/bins/1segd");
            new JsonTask2().execute("https://api.myjson.com/bins/3zpe5");


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class JsonTask1 extends AsyncTask<String, String, String> {


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
                String p3=jsonObject.getString("desc1");
                String p4=jsonObject.getString("desc2");
                String p5=jsonObject.getString("desc3");
                String p6=jsonObject.getString("desc4");
                String p7=jsonObject.getString("desc5");
                String p8=jsonObject.getString("desc6");
                String p9=jsonObject.getString("desc7");
                return p1+"\n\n"+p2+"\n\n"+p3+"\n\n"+p4+"\n\n"+p5+"\n\n"+p6+"\n\n"+p7+"\n\n"+p8+"\n\n"+p9+"\n";

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
            progressBar1.setVisibility(View.GONE);

            try {
                textView1.setText(s);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                textView1.setText("Refresh");
            }

        }

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
    }
    class JsonTask2 extends AsyncTask<String, String, String> {


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

                return p1+"\n";

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
            progressBar2.setVisibility(View.GONE);

            try {
                textView2.setText(s);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                textView2.setText("Refresh");
            }

        }

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
    }
}
