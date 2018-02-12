package com.mouris.mario.newspaper.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.R;
import com.mouris.mario.newspaper.UI.NewsDetailActivity.NewsDetail;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ArticlesRVAdapter extends RecyclerView.Adapter<ArticlesRVAdapter.ArticleViewHolder> {

    private Context mContext;
    private OnItemClickListener listener;
    private List<Article> mArticlesList;

    public ArticlesRVAdapter(List<Article> articlesList, OnItemClickListener listener) {
        mArticlesList = articlesList;
        this.listener = listener;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder((view));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder articleViewHolder, int position) {
        Article article = mArticlesList.get(position);
        articleViewHolder.bindData(mContext, article);
        articleViewHolder.view
                .setOnClickListener(v-> listener.onItemClick(article,
                        articleViewHolder.imageView,
                        articleViewHolder.titleTextView));
    }

    @Override
    public int getItemCount() {
        if (mArticlesList == null) return 0;
        return mArticlesList.size();
    }

    public void setArticlesList(List<Article> articlesList) {
        mArticlesList = articlesList;
        notifyDataSetChanged();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView imageView;
        TextView titleTextView;
        TextView publishDateTextView;

        ArticleViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            imageView = itemView.findViewById(R.id.article_image);
            titleTextView = itemView.findViewById(R.id.article_title);
            publishDateTextView = itemView.findViewById(R.id.article_publish_date);
        }

        void bindData(Context context, Article article) {
            Picasso.with(context).load(article.urlToImage)
                    .placeholder(R.drawable.no_image).into(imageView);
            titleTextView.setText(article.title);

            String publishDateString = context.getString(R.string.article_date, article.publishedAt);
            publishDateTextView.setText(publishDateString);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Article article, ImageView imageView, TextView titleTextView);
    }

}
