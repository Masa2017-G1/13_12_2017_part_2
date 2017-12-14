package com.sheygam.masa_2017_13_12_part2;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gregorysheygam on 14/12/2017.
 */

public class OkHttpProvider {

    public static final String BASE_URL = "https://olimshelper.herokuapp.com/";

    private static final OkHttpProvider ourInstance = new OkHttpProvider();

    public static OkHttpProvider getInstance() {
        return ourInstance;
    }

    private OkHttpClient client;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpProvider() {
        client = new OkHttpClient();
    }

    public String getSteps() throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/en")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code() < 400){
            String result = response.body().string();
            return result;
        }else{
            Log.d("MY_TAG", "getSteps: error: " + response.body().string());
            throw  new Exception("Server error! Call to support");
        }
    }

    public void sendBranch(String data) throws Exception {
        RequestBody body = RequestBody.create(JSON,data);
        Request request = new Request.Builder()
                .url(BASE_URL+"/branch")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() >= 400){
            Log.d("MY_TAG", "sendBranch: error: " + response.body().string());
            throw new Exception("Server error! Call to support!");
        }
    }
}
