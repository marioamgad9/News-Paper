package com.mouris.mario.newspaper.UI.NewsDetailActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouris.mario.newspaper.R;
import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {

    public static final String ARTICLE_ID_KEY = "article_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        int articleId = getIntent().getIntExtra(ARTICLE_ID_KEY, 0);

        NewsDetailViewModel viewModel = ViewModelProviders
                .of(this).get(NewsDetailViewModel.class);

        ImageView imageView = findViewById(R.id.imageView);
        TextView titleTextView = findViewById(R.id.titleTextView);

        viewModel.getArticle(articleId).observe(this, article -> {
            if (article != null) {
                Picasso.with(this).load(article.urlToImage)
                        .placeholder(R.drawable.no_image).into(imageView);
                titleTextView.setText(article.title);
            }
        });

    }
}
