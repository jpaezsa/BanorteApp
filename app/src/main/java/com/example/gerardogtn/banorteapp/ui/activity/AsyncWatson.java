package com.example.gerardogtn.banorteapp.ui.activity;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AsyncWatson extends AsyncTask<String, String, String> {

    InputStream inputStream = null;
    String mResult = "";
    String mSearch = "";

    public AsyncWatson(String search){
        mSearch = search;
    }
    @Override
    protected void onPreExecute() {}


    @Override
    protected String doInBackground(String... params) {
        Document doc = null;
        String result = "";
        try {
            doc = Jsoup.connect("https://es.m.wikipedia.org/wiki/"+mSearch).get();
            if (doc == null) return "No se encontraron resultados";
            result = doc.text();
            result = result.substring(115,result.length());
            if (result.length() > 180)
                result = result.substring(0,170);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

        /*
        String response = "";

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://banorteunomas.mybluemix.net/api/labelsearch?query="+mSearch);
            HttpResponse execute = client.execute(httpGet);
            InputStream content = execute.getEntity().getContent();

            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
            int pos = response.indexOf("\"label\":");
            response = response.substring(pos+9,response.length());
            pos = response.indexOf("}");
            response = response.substring(0, pos - 1);


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null){
            return "No se encontraron resultados";
        }
        return response;
        */
    }

    @Override
    protected void onPostExecute(String query) {
            onFinish(query);
    }

    public void onFinish(String content){}
}

