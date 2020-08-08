package com.numad.numadsu_alangrinberg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class WebService extends AppCompatActivity {
    private static final String TAG = "WebService";

    private TextView apiResult;
    private EditText categoryText;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        apiResult = (TextView) findViewById(R.id.apiResult);
        categoryText = (EditText) findViewById(R.id.categoryInput);
        button = (Button) findViewById(R.id.webSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PingJokesTask task = new PingJokesTask();
                task.execute(categoryText.getText().toString());
//                TODO: find out proper way to structure
            }
        });
    }


    private class PingJokesTask extends AsyncTask<String, Void, String[]> {


        protected String[] doInBackground(String... params) {
            String[] results = new String[2];
            String category = categoryText.getText().toString();
            try {
                String DUMMY_API = "https://jsonplaceholder.typicode.com/" + category + "/1";
                URL url = new URL(DUMMY_API);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();

                // Read response.
                InputStream inputStream = conn.getInputStream();
                final String resp = convertStreamToString(inputStream);

//                JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
                JSONObject jObject = new JSONObject(resp);
                Log.d("result test", jObject.toString());
                String jTitle = jObject.getString("id");
                String jBody = jObject.getString("body");
                results[0] = jTitle;
                results[1] = jBody;
                return results;

            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, "JSONException");
                e.printStackTrace();
            }
            results[0] = "Something went wrong";
            return (results);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            apiResult.setText("Starting...");
        }

        @Override
        protected void onPostExecute(String... s) {
            super.onPostExecute(s);
            TextView apiResult = (TextView) findViewById(R.id.apiResult);
            apiResult.setText(String.format("%s%s", getString(R.string.yourResult), s[1]));
        }

        private String convertStreamToString(InputStream is) {
            Scanner s = new Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next().replace(",", ",\n") : "";
        }
    }
}
