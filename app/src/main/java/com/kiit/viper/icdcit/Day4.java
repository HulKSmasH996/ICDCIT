package com.kiit.viper.icdcit;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

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
import java.util.ArrayList;
import java.util.List;

public class Day4 extends AppCompatActivity {
    public ListView listView;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day4);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        progressDialog=new ProgressDialog(Day4.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        listView= (ListView) findViewById(R.id.day4lv);
        //Button button = (Button) findViewById(R.id.button);
        // textView = (TextView) findViewById(R.id.textView);

        JsonTask obj=new JsonTask();obj.execute("https://api.myjson.com/bins/3d6cx");
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
            new JsonTask().execute("https://api.myjson.com/bins/3d6cx");
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
    class JsonTask extends AsyncTask<String, String, List<Day4Model>> {


        @Override
        protected List<Day4Model> doInBackground(String... params) {
            HttpURLConnection connection =null;
            BufferedReader reader = null;
            try{
                URL url=new URL(params[0]);
                connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader =new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line="";
                while ((line=reader.readLine())!= null){
                    buffer.append(line);

                }
                String finalJSON= buffer.toString();
                JSONObject jsonObject = new JSONObject(finalJSON);
                JSONArray jsonArray =jsonObject.getJSONArray("day4");
                List<Day4Model> day4ModelList =new ArrayList<>();


                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);
                    Day4Model day4Model =new Day4Model();
                    day4Model.setActivity(finalObject.getString("activity"));
                    day4Model.setTime(finalObject.getString("time"));
                    day4Model.setVenue(finalObject.getString("venue"));
                    day4Model.setImage(finalObject.getString("image"));
                    day4ModelList.add(day4Model);
                }
                return day4ModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null)
                    connection.disconnect();
                try {
                    if(reader!=null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(List<Day4Model> s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            try{Day4Adapter dateAdapter =new Day4Adapter(getApplicationContext(),R.layout.day4row,s);
                listView.setAdapter(dateAdapter);}
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }

    public class Day4Adapter extends ArrayAdapter {
        List<Day4Model> day4ModelList;
        int resource;
        LayoutInflater inflater;


        public Day4Adapter(Context context, int resource, List<Day4Model> objects) {

            super(context, resource, objects);
            day4ModelList=objects;
            this.resource=resource;
            inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null) {

                convertView = inflater.inflate(resource, null);
            }

            TextView activity;
            TextView time;
            TextView venue;
            ImageView imageView;

            imageView=(ImageView)convertView.findViewById(R.id.imageView);
            activity= (TextView) convertView.findViewById(R.id.textView3);
            time= (TextView) convertView.findViewById(R.id.textView4);
            venue= (TextView) convertView.findViewById(R.id.textView5);

            activity.setText(day4ModelList.get(position).getActivity());
            time.setText(day4ModelList.get(position).getTime());
            venue.setText(day4ModelList.get(position).getVenue());





            final ProgressBar progressBar =(ProgressBar)convertView.findViewById(R.id.progressBar);
            ImageLoader.getInstance().displayImage(day4ModelList.get(position).getImage(),imageView, new ImageLoadingListener(){
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            }); // Default options will be used



            return convertView;
        }

    }
}
