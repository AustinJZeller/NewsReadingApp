package com.austinzeller.newsreadingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

class InformationLoader extends AsyncTaskLoader<ArrayList<Information>> {
    private String mUrl;

    InformationLoader(Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Override protected void onStartLoading() {
        forceLoad();
    }

    @Override public ArrayList<Information> loadInBackground() {

        return QueryUtils.fetchNewsData(mUrl);
    }
}
