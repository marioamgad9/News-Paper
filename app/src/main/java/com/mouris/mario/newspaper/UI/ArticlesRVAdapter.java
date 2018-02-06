package com.mouris.mario.newspaper.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ArticlesRVAdapter extends RecyclerView.Adapter<ArticlesRVAdapter.ArticleViewHolder> {

    private Context mContext;
    private List<Article> mArticlesList;

    public ArticlesRVAdapter(List<Article> articlesList) {
        mArticlesList = articlesList;
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
        articleViewHolder.bindData(article, mContext);
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

        ImageView imageView;
        TextView titleTextView;
        TextView publishDateTextView;

        public ArticleViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.article_image);
            titleTextView = itemView.findViewById(R.id.article_title);
            publishDateTextView = itemView.findViewById(R.id.article_publish_date);
        }

        public void bindData(Article article, Context context) {
            Picasso.with(context).load(article.urlToImage)
                    .placeholder(R.drawable.no_image).into(imageView);
            titleTextView.setText(article.title);

            String publishDateString = context.getString(R.string.article_date, article.publishedAt);
            publishDateTextView.setText(publishDateString);
        }

    }

}
