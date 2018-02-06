package com.mouris.mario.newspaper.Utils;


public class ApiConstants {

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String TOP_HEADLINES = "top-headlines";
    public static final String EVERYTHING = "everything";

    public static class ApiParameterKeys {
        public static final String API_KEY = "apiKey";
        public static final String COUNTRY = "country";
        public static final String CATEGORY = "category";
        public static final String QUERY = "q";
    }

    public static class ApiParameterValues {
        public static final String API_KEY = "e592cbb6575e4f0d9c1786d8c4c83d67";
    }

    public static class ApiResponseKeys {
        public static final String STATUS = "status";
        public static final String ARTICLES = "articles";
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
        public static final String DESCRIPTION = "description";
        public static final String URL_TO_IMAGE = "urlToImage";
        public static final String URL_TO_ARTICLE = "url";
        public static final String PUBLISHED_AT = "publishedAt";
    }

}
