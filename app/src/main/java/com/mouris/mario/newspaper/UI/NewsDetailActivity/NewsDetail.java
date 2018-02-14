package com.mouris.mario.newspaper.UI.NewsDetailActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouris.mario.newspaper.R;
import com.mouris.mario.newspaper.Utils.NetworkUtils;

public class NewsDetail extends AppCompatActivity {

    public static final String ARTICLE_ID_KEY = "article_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        getSupportActionBar().setElevation(0);
        setTitle("");

        int articleId = getIntent().getIntExtra(ARTICLE_ID_KEY, 0);

        NewsDetailViewModel viewModel = ViewModelProviders
                .of(this).get(NewsDetailViewModel.class);

        ImageView imageView = findViewById(R.id.imageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView publishedAtTextView = findViewById(R.id.publishedAtTextView);
        TextView articleTextView = findViewById(R.id.articleTextView);
        TextView publisherTextView = findViewById(R.id.authorTextView);

        viewModel.getArticle(articleId).observe(this, article -> {
            if (article != null) {
                NetworkUtils.loadImageIntoIV(this, article.urlToImage, imageView);
                titleTextView.setText(article.title);
                publishedAtTextView.setText(article.publishedAt);
                articleTextView.setText(article.description);
                publisherTextView.setText(article.author);
            }
        });

    }
}
