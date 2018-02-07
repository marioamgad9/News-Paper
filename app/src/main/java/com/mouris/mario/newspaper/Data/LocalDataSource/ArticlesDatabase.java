package com.mouris.mario.newspaper.Data.LocalDataSource;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mouris.mario.newspaper.Data.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticlesDatabase extends RoomDatabase{

    public abstract ArticlesDao articlesDao();

    private static final String DATABASE_NAME = "Articles.db";

    private static volatile ArticlesDatabase sInstance;

    public static ArticlesDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ArticlesDatabase.class,
                    ArticlesDatabase.DATABASE_NAME).build();
        }
        return sInstance;
    }

}
