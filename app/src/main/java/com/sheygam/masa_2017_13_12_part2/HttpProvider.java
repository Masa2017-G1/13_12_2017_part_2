package com.sheygam.masa_2017_13_12_part2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gregorysheygam on 13/12/2017.
 */

public class HttpProvider {
    public static final String BASE_URL = "https://olimshelper.herokuapp.com";
    private static final HttpProvider ourInstance = new HttpProvider();

    public static HttpProvider getInstance() {
        return ourInstance;
    }

    private HttpProvider() {
    }

    public String getSteps() throws Exception {
        URL url = new URL(BASE_URL + "/en");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode() < 400){
            String line;
            String result = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while((line = br.readLine())!= null){
                result+=line;
            }
            br.close();
            return result;
        }else{
            String line;
            String result = "";
            InputStreamReader isr = new InputStreamReader(connection.getErrorStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine())!=null){
                result+=line;
            }
            br.close();
            Log.d("MY_TAG", "getSteps: error: " + result);
            throw new Exception("Server error");
        }
    }
}
