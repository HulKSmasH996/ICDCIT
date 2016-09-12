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

public class ImpDates extends AppCompatActivity {
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;


    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_dates);
        progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);
        progressBar4=(ProgressBar)findViewById(R.id.progressBar4);


        textView1=(TextView)findViewById(R.id.text);
        textView2=(TextView)findViewById(R.id.text2);
        textView3=(TextView)findViewById(R.id.text3);
        textView4=(TextView)findViewById(R.id.text4);

        new JsonTask1().execute("https://api.myjson.com/bins/39zmx");
        new JsonTask2().execute("https://api.myjson.com/bins/lyuh");
        new JsonTask3().execute("https://api.myjson.com/bins/m6k9");
        new JsonTask4().execute("https://api.myjson.com/bins/19z1l");



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
            new JsonTask1().execute("https://api.myjson.com/bins/39zmx");
            new JsonTask2().execute("https://api.myjson.com/bins/lyuh");
            new JsonTask3().execute("https://api.myjson.com/bins/m6k9");
            new JsonTask4().execute("https://api.myjson.com/bins/19z1l");


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
                JSONArray jsonArray = parentObject.getJSONArray("dates");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("p1");
                String p2=jsonObject.getString("p2");
                String p3=jsonObject.getString("p3");
                String p4=jsonObject.getString("p4");
                String p5=jsonObject.getString("p5");
                String p6=jsonObject.getString("p6");
                String p7=jsonObject.getString("p7");
                String p8=jsonObject.getString("p8");


                return p1+"\t"+p2+"\n\n"+p3+"\t"+p4+"\n\n"+p5+"\t"+p6+"\n\n"+p7+"\t"+p8+"\n";

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
                JSONArray jsonArray = parentObject.getJSONArray("dates");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("p1");
                String p2=jsonObject.getString("p2");
                String p3=jsonObject.getString("p3");
                String p4=jsonObject.getString("p4");
                String p5=jsonObject.getString("p5");
                String p6=jsonObject.getString("p6");
                String p7=jsonObject.getString("p7");
                String p8=jsonObject.getString("p8");
                String p9=jsonObject.getString("p9");
                String p10=jsonObject.getString("p10");



                return p1+"\t"+p2+"\n\n"+p3+"\t"+p4+"\n\n"+p5+"\t"+p6+"\n\n"+p7+"\t"+p8+"\n\n"+p9+"\t"+p10+"\n";

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
    class JsonTask3 extends AsyncTask<String, String, String> {


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
                JSONArray jsonArray = parentObject.getJSONArray("dates");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("p1");
                String p2=jsonObject.getString("p2");
                String p3=jsonObject.getString("p3");
                String p4=jsonObject.getString("p4");



                return p1+"\t"+p2+"\n\n"+p3+"\t"+p4+"\n";

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
            progressBar3.setVisibility(View.GONE);

            try {
                textView3.setText(s);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                textView3.setText("Refresh");
            }

        }

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
    }
    class JsonTask4 extends AsyncTask<String, String, String> {


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
                JSONArray jsonArray = parentObject.getJSONArray("dates");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("p1");
                String p2=jsonObject.getString("p2");
                String p3=jsonObject.getString("p3");
                String p4=jsonObject.getString("p4");
                String p5=jsonObject.getString("p5");
                String p6=jsonObject.getString("p6");




                return p1+"\t"+p2+"\n\n"+p3+"\t"+p4+"\n\n"+p5+"\t"+p6+"\n";

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
            progressBar4.setVisibility(View.GONE);

            try {
                textView4.setText(s);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                textView4.setText("Refresh");
            }

        }

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
    }
}
