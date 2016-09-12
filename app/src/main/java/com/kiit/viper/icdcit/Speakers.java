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
import android.widget.AdapterView;
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

public class Speakers extends AppCompatActivity {
    public ListView listView;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);
        // Create default options which will be used for every
       //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)

        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        progressDialog=new ProgressDialog(Speakers.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        listView= (ListView) findViewById(R.id.speakerlv);
        //Button button = (Button) findViewById(R.id.button);
        // textView = (TextView) findViewById(R.id.textView);

        JsonTask obj=new JsonTask();obj.execute("http://ec2-52-41-7-99.us-west-2.compute.amazonaws.com/speakers.json");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });

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
            new JsonTask().execute("http://ec2-52-41-7-99.us-west-2.compute.amazonaws.com/speakers.json");
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }*/


    class JsonTask extends AsyncTask<String, String, List<SpeakerModel>> {


        @Override
        protected List<SpeakerModel> doInBackground(String... params) {
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
                JSONArray jsonArray =jsonObject.getJSONArray("speakers");
                List<SpeakerModel> speakerModelList =new ArrayList<>();


                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject finalObject = jsonArray.getJSONObject(i);
                    SpeakerModel speakerModel =new SpeakerModel();
                    speakerModel.setProf(finalObject.getString("name"));
                    speakerModel.setDob(finalObject.getString("desc"));
                    speakerModel.setUniversity(finalObject.getString("university"));
                    speakerModel.setCountry(finalObject.getString("country"));
                    speakerModel.setImage(finalObject.getString("image"));
                    speakerModelList.add(speakerModel);
                }
                return speakerModelList;

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
        protected void onPostExecute(List<SpeakerModel> s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            try{SpeakerAdapter dateAdapter =new SpeakerAdapter(getApplicationContext(),R.layout.speaker_row,s);
                listView.setAdapter(dateAdapter);}
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Unable to Fetch Data from Server",
                        Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }

    public class SpeakerAdapter extends ArrayAdapter {
        List<SpeakerModel> speakerModelList;
        int resource;
        LayoutInflater inflater;


        public SpeakerAdapter(Context context, int resource, List<SpeakerModel> objects) {

            super(context, resource, objects);
             speakerModelList=objects;
            this.resource=resource;
            inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null) {

                convertView = inflater.inflate(resource, null);
            }

            TextView name;
            TextView dob;
            TextView university;
            TextView country;
            ImageView imageView;

            imageView=(ImageView)convertView.findViewById(R.id.imageView);
            name= (TextView) convertView.findViewById(R.id.textView3);
            dob= (TextView) convertView.findViewById(R.id.textView4);
            university= (TextView) convertView.findViewById(R.id.textView5);
            country= (TextView) convertView.findViewById(R.id.textView6);

            name.setText(speakerModelList.get(position).getProf());
            dob.setText(speakerModelList.get(position).getDob());
            university.setText(speakerModelList.get(position).getUniversity());
            country.setText(speakerModelList.get(position).getCountry());




            final ProgressBar progressBar =(ProgressBar)convertView.findViewById(R.id.progressBar);
           ImageLoader.getInstance().displayImage(speakerModelList.get(position).getImage(),imageView, new ImageLoadingListener(){
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
