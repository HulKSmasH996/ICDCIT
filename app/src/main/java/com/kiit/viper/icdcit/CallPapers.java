package com.kiit.viper.icdcit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class CallPapers extends AppCompatActivity {
    private static String url1;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    //String url1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_papers);
        new JsonTask1().execute("https://api.myjson.com/bins/4fewh");
        new JsonTask2().execute("https://api.myjson.com/bins/rlj5");
        new JsonTask3().execute("https://api.myjson.com/bins/34lsl");
        new JsonTask4().execute("https://api.myjson.com/bins/51en9");
        new JsonTask5().execute("http://ec2-52-25-202-83.us-west-2.compute.amazonaws.com/easychaircfp.json");


        progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);
        progressBar4=(ProgressBar)findViewById(R.id.progressBar4);
        textView1=(TextView)findViewById(R.id.text1);
        textView2=(TextView)findViewById(R.id.text2);
        textView3=(TextView)findViewById(R.id.text3);
        textView4=(TextView)findViewById(R.id.text4);


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
            new JsonTask1().execute("https://api.myjson.com/bins/rlj5");
            new JsonTask2().execute("https://api.myjson.com/bins/rlj5");
            new JsonTask3().execute("https://api.myjson.com/bins/34lsl");
            new JsonTask4().execute("https://api.myjson.com/bins/3d6fp");
            new JsonTask5().execute("http://ec2-52-41-7-99.us-west-2.compute.amazonaws.com/easychaircfp.json");



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static Intent getOpenEasyChairIntent(Context context) {

        return new Intent(Intent.ACTION_VIEW,
                Uri.parse(url1));
    }

    public void EasyChairIntent(View view) {
        Intent easychairIntent = getOpenEasyChairIntent(CallPapers.this);
        startActivity(easychairIntent);
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
                JSONArray jsonArray = parentObject.getJSONArray("call");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("desc1");
                String p2=jsonObject.getString("desc2");
                String p3=jsonObject.getString("desc3");
                String p4=jsonObject.getString("desc4");
                String p5=jsonObject.getString("desc5");
                String p6=jsonObject.getString("desc6");
                String p7=jsonObject.getString("desc7");
                String p8=jsonObject.getString("desc8");
                String p9=jsonObject.getString("desc9");
                String p10=jsonObject.getString("desc10");
                String p11=jsonObject.getString("desc11");
                return p1+"\n"+p2+"\n"+p3+"\n"+p4+"\n"+p5+"\n"+p6+"\n"+p7+"\n"+p8+"\n"+p9+"\n"+p10+"\n"+p11+"\n";

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
                JSONArray jsonArray = parentObject.getJSONArray("call");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("desc1");
                String p2=jsonObject.getString("desc2");
                String p3=jsonObject.getString("desc3");
                String p4=jsonObject.getString("desc4");
                String p5=jsonObject.getString("desc5");
                String p6=jsonObject.getString("desc6");
                String p7=jsonObject.getString("desc7");
                String p8=jsonObject.getString("desc8");
                String p9=jsonObject.getString("desc9");
                String p10=jsonObject.getString("desc10");
                String p11=jsonObject.getString("desc11");
                String p12=jsonObject.getString("desc12");
                return p1+"\n"+p2+"\n"+p3+"\n"+p4+"\n"+p5+"\n"+p6+"\n"+p7+"\n"+p8+"\n"+p9+"\n"+p10+"\n"+p11+"\n"+p12+"\n";

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
                JSONArray jsonArray = parentObject.getJSONArray("call");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("desc1");
                String p2=jsonObject.getString("desc2");
                String p3=jsonObject.getString("desc3");
                String p4=jsonObject.getString("desc4");
                String p5=jsonObject.getString("desc5");
                String p6=jsonObject.getString("desc6");
                String p7=jsonObject.getString("desc7");
                String p8=jsonObject.getString("desc8");
                String p9=jsonObject.getString("desc9");
                return p1+"\n"+p2+"\n"+p3+"\n"+p4+"\n"+p5+"\n"+p6+"\n"+p7+"\n"+p8+"\n"+p9+"\n";

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
                textView2.setText("Refresh");
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
                JSONArray jsonArray = parentObject.getJSONArray("icdcit");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String p1=jsonObject.getString("p1");
                String p2=jsonObject.getString("p2");

                return p1+"\n\n"+p2+"\n";

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
    class JsonTask5 extends AsyncTask<String, String, String> {


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
                return p1;

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
           // progressBar1.setVisibility(View.GONE);

            try {
                url1=s;
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();

            }

        }

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();

        }
    }
}
