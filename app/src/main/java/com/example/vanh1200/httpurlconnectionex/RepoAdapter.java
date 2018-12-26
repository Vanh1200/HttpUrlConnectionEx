package com.example.vanh1200.httpurlconnectionex;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    private List<Repo> mRepos;

    public RepoAdapter(List<Repo> repos) {
        mRepos = repos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_repo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mRepos.get(i));
    }

    @Override
    public int getItemCount() {
        return mRepos == null ? 0 : mRepos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextId;
        private TextView mTextName;
        private TextView mTextFullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mTextId = itemView.findViewById(R.id.text_id);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextFullName = itemView.findViewById(R.id.text_full_name);
        }

        public void bindData(Repo repo) {
            mTextId.setText(String.valueOf(repo.getId()));
            mTextName.setText(repo.getName());
            mTextFullName.setText(repo.getFullName());
        }
    }
}
