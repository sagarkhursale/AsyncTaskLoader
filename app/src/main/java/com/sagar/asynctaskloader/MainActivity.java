package com.sagar.asynctaskloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
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


    // END
}
