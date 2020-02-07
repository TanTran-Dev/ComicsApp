package com.trantan.comicsapp.api;

import android.os.AsyncTask;

import com.trantan.comicsapp.interfaces.GetChapFromAPI;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIGetChap extends AsyncTask<Void, Void, Void> {
    String data;
    GetChapFromAPI getChapFromAPI;
    String idComic;

    public APIGetChap(GetChapFromAPI getChapFromAPI, String idComic) {
        this.getChapFromAPI = getChapFromAPI;
        this.getChapFromAPI.start();
        this.idComic = idComic;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://tantran99.000webhostapp.com/getChaps.php?id=" + idComic)
                .build();

        data = null;

        try{
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null) {
            this.getChapFromAPI.error();
        }else {
            this.getChapFromAPI.finish(data);
        }
        super.onPostExecute(aVoid);
    }
}
