package com.trantan.comicsapp.api;

import android.os.AsyncTask;

import com.trantan.comicsapp.interfaces.GetComicFromAPI;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIGetComic extends AsyncTask<Void, Void, Void> {
    String data;
    GetComicFromAPI getComicFromAPI;

    public APIGetComic(GetComicFromAPI getComicFromAPI) {
        this.getComicFromAPI = getComicFromAPI;
        this.getComicFromAPI.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.url("https://api.myjson.com/bins/1hf452")
                .url("http://tantran99.000webhostapp.com/getComics.php")
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
        if (data == null){
            this.getComicFromAPI.error();
        }else {
            this.getComicFromAPI.finish(data);
        }
    }
}
