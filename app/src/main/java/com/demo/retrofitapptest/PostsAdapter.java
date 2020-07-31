package com.demo.retrofitapptest;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsAdapterViewHolder> {


    private List<PostModel> post;

    public List<PostModel> getPost() {
        return post;
    }

    public void setPost(List<PostModel> post) {
        this.post = post;
    }

    public PostsAdapter(List<PostModel> post) {
        this.post = post;
    }

    @NonNull
    @Override
    public PostsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lalout_item, parent, false);
        return new PostsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapterViewHolder holder, int position) {
        PostModel postModel = post.get(position);
//        holder.textViewText.setText(postModel.getName());
//        holder.textViewBash.setText(postModel.getDesc());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textViewText.setText(Html.fromHtml(postModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.textViewText.setText(Html.fromHtml(postModel.getElementPureHtml()));
        }
        holder.textViewBash.setText(postModel.getSite());
    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    class PostsAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewText;
        private TextView textViewBash;

        public PostsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewText = itemView.findViewById(R.id.textViewText);
            textViewBash = itemView.findViewById(R.id.textViewBash);
        }
    }
}
