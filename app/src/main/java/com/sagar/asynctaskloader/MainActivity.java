package com.sagar.asynctaskloader;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sagar.asynctaskloader.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    private static final int GITHUB_SEARCH_LOADER = 22;

    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText =  findViewById(R.id.et_search_box);
        mUrlDisplayTextView =  findViewById(R.id.tv_url_display);
        mSearchResultsTextView =  findViewById(R.id.tv_github_search_results_json);
        mErrorMessageDisplay =  findViewById(R.id.tv_error_message_display);
        mLoadingIndicator =  findViewById(R.id.pb_loading_indicator);


        // end
    }


    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            String mGithubJson;

            @Override
            protected void onStartLoading() {
                if (args == null) {
                    return;
                }
                mLoadingIndicator.setVisibility(View.VISIBLE);

                if (mGithubJson != null) {
                    deliverResult(mGithubJson);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String loadInBackground() {
                assert args != null;
                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);

                if (searchQueryUrlString == null || TextUtils.isEmpty(searchQueryUrlString)) {
                    return null;
                }

                try {
                    URL githubUrl = new URL(searchQueryUrlString);
                    return NetworkUtils.getResponseFromHttpUrl(githubUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(@Nullable String githubJson) {
                mGithubJson = githubJson;
                super.deliverResult(githubJson);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            //makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // END
}
