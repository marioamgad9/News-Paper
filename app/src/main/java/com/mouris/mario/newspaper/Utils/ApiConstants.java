package com.mouris.mario.newspaper.Utils;


 class ApiConstants {

     static class UrlConstants {
        static final String SCHEMA = "https";
        static final String AUTHORITY = "newsapi.org";
        static final String API_PATH = "v2";
        static final String TOP_HEADLINES_PATH = "top-headlines";
        static final String EVERYTHING_PATH = "everything";
    }

     static class ApiParameterKeys {
        static final String API_KEY = "apiKey";
         static final String COUNTRY = "country";
         static final String CATEGORY = "category";
         static final String QUERY = "q";
    }

     static class ApiParameterValues {
         static final String API_KEY = "e592cbb6575e4f0d9c1786d8c4c83d67";
         static final String COUNTRY_US = "us";
         static final String COUNTRY_EG = "eg";
    }

     static class ApiResponseKeys {
         static final String STATUS = "status";
         static final String ARTICLES = "articles";
         static final String TITLE = "title";
         static final String AUTHOR = "author";
         static final String DESCRIPTION = "description";
         static final String URL_TO_IMAGE = "urlToImage";
         static final String URL_TO_ARTICLE = "url";
         static final String PUBLISHED_AT = "publishedAt";
    }

}
