package com.alfdp.ezstudio;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aurelien on 13-Feb-17.
 */

public class ProjectListFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int mCount = 10;
    private List<String> mWidgetItems = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;

    public ProjectListFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {

        for (int i = 0; i < mCount; i++) {
            mWidgetItems.add(i + "");
        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    public RemoteViews getViewAt(int position) {

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.project_list_widget);
        rv.setTextViewText(R.id.stack_view, mWidgetItems.get(position));

        Bundle extras = new Bundle();
        extras.putInt(ProjectListWidgetProvider.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.stack_view, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(mContext.getPackageName(), R.layout.activity_splash_screen);
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
