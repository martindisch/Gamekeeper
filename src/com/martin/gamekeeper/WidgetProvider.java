package com.martin.gamekeeper;

import java.util.Calendar;
import java.util.List;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		final int wcount = appWidgetIds.length;

		// Iterate through all instances of the widget
		for (int y = 0; y < wcount; y++) {
			
			// Get the views for the widget
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
			// Background
			Drawable background = null;
			switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
			case 0:
				background = context.getResources().getDrawable(R.drawable.card_background_blue);
				break;
			case 1:
				background = context.getResources().getDrawable(R.drawable.card_background_purple);
				break;
			case 2:
				background = context.getResources().getDrawable(R.drawable.card_background_green);
				break;
			case 3:
				background = context.getResources().getDrawable(R.drawable.card_background_orange);
				break;
			case 4:
				background = context.getResources().getDrawable(R.drawable.card_background_red);
				break;
			}
			//remoteViews.setInt(R.id.container, "setBackgroundResource", color);
			
			// Set up
			// set the intent for the click-event
			/*views.setOnClickPendingIntent(R.id.llCardNoten, penNotIntent);
			views.setOnClickPendingIntent(R.id.llCardKontingent, penKontIntent);
			views.setOnClickPendingIntent(R.id.llCardKISS, penKissIntent);
			views.setOnClickPendingIntent(R.id.ibNotenAdd, penacNotIntent);
			views.setOnClickPendingIntent(R.id.ibKontAdd, penacKontIntent);

			appWidgetManager.updateAppWidget(appWidgetIds[y], views);*/
		}
	}
	
	@Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        /*if (SYNC_CLICKED.equals(intent.getAction())) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews remoteViews;
            ComponentName watchWidget;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            watchWidget = new ComponentName(context, Widget.class);

            remoteViews.setTextViewText(R.id.sync_button, "TESTING");

            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

        }*/
    }

	protected PendingIntent getPendingSelfIntent(Context context, String action) {
		Intent intent = new Intent(context, getClass());
		intent.setAction(action);
		return PendingIntent.getBroadcast(context, 0, intent, 0);
	}
}
