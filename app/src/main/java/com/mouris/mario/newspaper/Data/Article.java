package com.mouris.mario.newspaper.Data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Articles")
public class Article {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String author;
    public String description;
    public String urlToImage;
    public String urlToArticle;
    public String publishedAt;  //TODO: Store this as a date

    public Article() { }
}
