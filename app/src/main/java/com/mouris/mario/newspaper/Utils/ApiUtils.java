package com.mouris.mario.newspaper.Utils;


import android.net.Uri;
import android.util.Log;

import com.mouris.mario.newspaper.Data.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.net.ssl.HttpsURLConnection;

public class ApiUtils {
    private static final String LOG_TAG = ApiUtils.class.getSimpleName();

    private ApiUtils() { }

    public static List<Article> fetchHeadlineArticles() {
        URL url = createHeadlinesUrl();

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpsRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractArticlesFromJson(jsonResponse);
    }

    private static URL createHeadlinesUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(ApiConstants.UrlConstants.SCHEMA)
                .authority(ApiConstants.UrlConstants.AUTHORITY)
                .appendPath(ApiConstants.UrlConstants.API_PATH)
                .appendPath(ApiConstants.UrlConstants.TOP_HEADLINES_PATH)
                .appendQueryParameter(ApiConstants.ApiParameterKeys.API_KEY,
                        ApiConstants.ApiParameterValues.API_KEY)
                .appendQueryParameter(ApiConstants.ApiParameterKeys.COUNTRY,
                        ApiConstants.ApiParameterValues.COUNTRY_US);

        String urlString = builder.build().toString();
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "There was an error while parsing URL", e);
        }

        return url;
    }

    private static String makeHttpsRequest(URL url) throws IOException {
        String jsonResponse = "";

        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int response_code = urlConnection.getResponseCode();
            if (response_code == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code " + response_code);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        } else {
            Log.e(LOG_TAG, "InputStream is null");
        }

        return output.toString();
    }

    private static List<Article> extractArticlesFromJson(String jsonResponse) {
        if (jsonResponse.isEmpty()) return null;

        List<Article> articleList = new ArrayList<>();
        try {
            JSONObject responseJson = new JSONObject(jsonResponse);
            JSONArray articlesArrayJson = responseJson
                    .getJSONArray(ApiConstants.ApiResponseKeys.ARTICLES);

            for (int i=0 ; i < articlesArrayJson.length() ; i++) {
                JSONObject articleJson = articlesArrayJson.getJSONObject(i);

                Article article = new Article();
                article.title = articleJson.getString(ApiConstants.ApiResponseKeys.TITLE);
                article.author = articleJson.getString(ApiConstants.ApiResponseKeys.AUTHOR);
                article.description = articleJson.getString(ApiConstants.ApiResponseKeys.DESCRIPTION);
                article.urlToImage = articleJson.getString(ApiConstants.ApiResponseKeys.URL_TO_IMAGE);
                article.urlToArticle = articleJson.getString(ApiConstants.ApiResponseKeys.URL_TO_ARTICLE);
                article.publishedAt = articleJson.getString(ApiConstants.ApiResponseKeys.PUBLISHED_AT);

                articleList.add(article);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "There was an error while extracting articles from JSON", e);
        }

        return articleList;
    }

}
