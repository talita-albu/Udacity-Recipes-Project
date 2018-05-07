package com.talitaalbu.android.livrodereceitas_culinaria.widget;

import android.content.Context;
import android.content.SharedPreferences;

import com.talitaalbu.android.livrodereceitas_culinaria.R;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Recipe;


public class RecipePreferences {

    public static final String PREFS_NAME = "prefs";

    public static void saveRecipe(Context context, Recipe recipe) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.putString(context.getString(R.string.widget_key), Recipe.toBase64String(recipe));

        prefs.apply();
    }

    public static Recipe loadRecipe(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String recipeBase64 = prefs.getString(context.getString(R.string.widget_key), "");

        if(!recipeBase64.equals(""))
        {
            return Recipe.fromBase64(recipeBase64);
        }
        else
        {
            return null;
        }
        //return "".equals(recipeBase64) ? null : Recipe.fromBase64(prefs.getString(context.getString(R.string.widget_key), ""));
    }
}
