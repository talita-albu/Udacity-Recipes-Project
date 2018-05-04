package com.talitaalbu.android.livrodereceitas_culinaria.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
        //return new WidgetDataProvider(getApplicationContext(), intent);
    }
}
