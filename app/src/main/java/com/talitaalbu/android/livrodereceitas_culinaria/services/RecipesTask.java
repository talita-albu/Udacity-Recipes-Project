package com.talitaalbu.android.livrodereceitas_culinaria.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Recipe;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipesTask extends AsyncTask<List<Recipe>, Void, List<Recipe>> {

    private static final String RECIPES_API_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private final ProgressBar mProgressBar;
    public RecipesTask(ProgressBar progressBar){
        mProgressBar = progressBar;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Recipe> doInBackground(List<Recipe>... recipes) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> recipeFromJson = null;
        try {
            recipeFromJson = gson.fromJson(run(RECIPES_API_URL), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipeFromJson;
    }

    @Override
    protected void onPostExecute(List<Recipe> recipe) {
        super.onPostExecute(recipe);
        if(mProgressBar != null)
            mProgressBar.setVisibility(View.INVISIBLE);
    }

    private static String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static boolean isConected(Context context) {
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected());
    }

}
