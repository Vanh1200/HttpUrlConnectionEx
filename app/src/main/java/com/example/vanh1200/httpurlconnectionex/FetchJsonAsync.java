package com.example.vanh1200.httpurlconnectionex;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchJsonAsync extends AsyncTask<String, Void, List<Repo>> {
    private static final String METHOD_GET = "GET";
    private static final String REPO_ID = "id";
    private static final String REPO_NAME = "name";
    private static final String REPO_FULL_NAME = "full_name";
    private OnLoadCompleted mListener;
    private List<Repo> mRepos;

    public void setListener(OnLoadCompleted listener) {
        mListener = listener;
    }

    @Override
    protected List<Repo> doInBackground(String... strings) {
        mRepos = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(METHOD_GET);
            InputStream inputStream = connection.getInputStream();
            return getRepos(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mRepos;
    }

    private List<Repo> getRepos(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        try {
            JSONArray jsonArray = new JSONArray(builder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Repo repo = new Repo();
                repo.setId(jsonObject.getInt(REPO_ID));
                repo.setName(jsonObject.getString(REPO_NAME));
                repo.setFullName(jsonObject.getString(REPO_FULL_NAME));
                mRepos.add(repo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mRepos;
    }

    @Override
    protected void onPostExecute(List<Repo> repos) {
        super.onPostExecute(repos);
        if (mListener != null) {
            mListener.onSuccess(repos);
        }
    }

    public interface OnLoadCompleted {
        void onSuccess(List<Repo> repos);

        void onFailed(String mess);
    }
}
