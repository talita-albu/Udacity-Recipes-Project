package com.talitaalbu.android.livrodereceitas_culinaria.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.talitaalbu.android.livrodereceitas_culinaria.RecipeWidget;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Recipe;

public class WidgetService extends RemoteViewsService {

    public static void updateWidget(Context context, Recipe recipe) {
        RecipePreferences.saveRecipe(context, recipe);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidget.class));
        RecipeWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        return new ListRemoteViewsFactory(getApplicationContext());
    }
}
