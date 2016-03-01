package com.example.contacts.contactss.api;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.contacts.contactss.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 01.03.2016.
 */
public class Tut {
    public static abstract class CallBackGetNews {
        public abstract void onSuccess(ArrayList<NewsItem> model);

        public abstract void onError(String error);
    }

    public static void asyncSearchUsers(String query, final CallBackGetNews callback) {
        new AsyncTask<String, Void, ArrayList<NewsItem>>() {
            @Override
            protected ArrayList<NewsItem> doInBackground(String... params) {
                String result = "";
                String responseLine = "";

                ArrayList<NewsItem> Array = new ArrayList<>();

                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("api.vk.com")
                        .appendPath("method")
                        .appendPath("users.search")
                        .appendQueryParameter("q", params[0])
                        .appendQueryParameter("fields", "photo_100,last_seen,online")
                        .appendQueryParameter("count", "50");
                String myUrl = builder.build().toString();

                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(myUrl).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Length", "0");
                    connection.setConnectTimeout(10000);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((responseLine = bufferedReader.readLine()) != null) {
                        result += responseLine;
                    }
                    bufferedReader.close();

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray response = jsonObject.getJSONArray("response");


                } catch (JSONException | ProtocolException | MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Array;
            }

            @Override
            protected void onPostExecute(ArrayList<NewsItem> result) {
                callback.onSuccess(result);
            }
        }.execute(query);
    }
}
