package com.example.vanh1200.httpurlconnectionex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchJsonAsync.OnLoadCompleted {
    private ProgressBar mProgressBarFetch;
    private RecyclerView mRecyclerRepo;
    private RepoAdapter mRepoAdapter;
    public static final String URL = "https://api.github.com/users/google/repos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getRepos();
    }

    private void getRepos() {
        FetchJsonAsync fetchJsonAsync = new FetchJsonAsync();
        fetchJsonAsync.setListener(this);
        fetchJsonAsync.execute(URL);
    }

    private void initViews() {
        mProgressBarFetch = findViewById(R.id.progress_bar_fetch);
        mRecyclerRepo = findViewById(R.id.recycler_repository);
    }

    @Override
    public void onSuccess(List<Repo> repos) {
        mRepoAdapter = new RepoAdapter(repos);
        mRecyclerRepo.setAdapter(mRepoAdapter);
        mProgressBarFetch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailed(String mess) {
        mProgressBarFetch.setVisibility(View.INVISIBLE);
    }
}
