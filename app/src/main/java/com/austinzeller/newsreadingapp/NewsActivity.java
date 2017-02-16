package com.austinzeller.newsreadingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Information>> {
    private InformationAdapter mInformationAdapter;

    private ListView mNewsListView;
    private ProgressBar mLoadingSpinner;

    private static final int LOADER_ID = 0;
    private static final String URL = "https://content.guardianapis.com/search?api-key=4f88e60c-f622-4e0e-8345-0dad3dc464df";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mNewsListView = (ListView) findViewById(R.id.news_list_view);
        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_state_text_view);
        mLoadingSpinner = (ProgressBar) findViewById(R.id.loading_spinner);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mLoadingSpinner.setVisibility(View.GONE);
        }
    }

    @Override public Loader<ArrayList<Information>> onCreateLoader(int id, Bundle args) {

        return new InformationLoader(NewsActivity.this, URL);
    }

    @Override public void onLoadFinished(Loader<ArrayList<Information>> loader, ArrayList<Information> informationStories) {
        mLoadingSpinner.setVisibility(View.GONE);

        mInformationAdapter = new InformationAdapter(this, informationStories);
        mNewsListView.setAdapter(mInformationAdapter);
    }

    @Override public void onLoaderReset(Loader<ArrayList<Information>> loader) {
        mInformationAdapter = new InformationAdapter(this, new ArrayList<Information>());
        mNewsListView.setAdapter(mInformationAdapter);
    }
}
