package com.trantan.comicsapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Comic implements Serializable {
    private String mId;
    private String mNameComic;
    private String mNameChap;
    private String mLinkImage;

    public Comic(String mId, String mNameComic, String mNameChap, String mLinkImage) {
        this.mId = mId;
        this.mNameComic = mNameComic;
        this.mNameChap = mNameChap;
        this.mLinkImage = mLinkImage;
    }

    public Comic(String mNameComic, String mNameChap, String mLinkImage) {
        this.mNameComic = mNameComic;
        this.mNameChap = mNameChap;
        this.mLinkImage = mLinkImage;
    }

    public Comic() {
    }

    public Comic(JSONObject o) throws JSONException {
        mId = o.getString("id");
        mNameComic = o.getString("nameComic");
        mNameChap = o.getString("nameChap");
        mLinkImage = o.getString("linkImage");
    }

    public String getmNameComic() {
        return mNameComic;
    }

    public void setmNameComic(String mNameComic) {
        this.mNameComic = mNameComic;
    }

    public String getmNameChap() {
        return mNameChap;
    }

    public void setmNameChap(String mNameChap) {
        this.mNameChap = mNameChap;
    }

    public String getmLinkImage() {
        return mLinkImage;
    }

    public void setmLinkImage(String mLinkImage) {
        this.mLinkImage = mLinkImage;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
