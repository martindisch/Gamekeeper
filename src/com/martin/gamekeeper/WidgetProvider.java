package com.martin.gamekeeper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		DbManager db = new DbManager(context);

		final int wcount = appWidgetIds.length;

		// Iterate through all instances of the widget
		for (int y = 0; y < wcount; y++) {
			
			// Get the views for the widget
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
			// Background
			views.setInt(R.id.llCardRoot, "setBackgroundResource", Util.getBackground(Util.getDay()));
			
			// Day
			views.setTextViewText(R.id.tvDay, context.getResources().getStringArray((R.array.days))[Util.getDay()]);
			
			// Score
			views.setTextViewText(R.id.tvScore, db.getResultForDay(Util.getDay()));
			
			// Profile pictures
			
			// Get Uri's
			Uri p1Uri = db.getPicUri(1);
			Uri p2Uri = db.getPicUri(2);
			// Get size of imageviews
			int[] size = db.getSavedSize();
			int width = size[0];
			int height = size[1];
			// If we know a size, do it
			if (width != 0 && height != 0) {
				Bitmap pic1 = null, pic2 = null;
				if (!p1Uri.toString().contentEquals("null")) {
					pic1 = Bitmapper.decodeSampledBitmap(context, p1Uri, width, height);
				}
				if (!p2Uri.toString().contentEquals("null")) {
					pic2 = Bitmapper.decodeSampledBitmap(context, p2Uri, width, height);
				}
				if (pic1 != null) {
					views.setImageViewBitmap(R.id.ivC1, pic1);
				}
				if (pic2 != null) {
					views.setImageViewBitmap(R.id.ivC2, pic2);
				}
			}			
			
			// set the intent for the click-event
			/*views.setOnClickPendingIntent(R.id.llCardNoten, penNotIntent);
			views.setOnClickPendingIntent(R.id.llCardKontingent, penKontIntent);
			views.setOnClickPendingIntent(R.id.llCardKISS, penKissIntent);
			views.setOnClickPendingIntent(R.id.ibNotenAdd, penacNotIntent);
			views.setOnClickPendingIntent(R.id.ibKontAdd, penacKontIntent);*/

			appWidgetManager.updateAppWidget(appWidgetIds[y], views);
		}
	}
	
	@Override
    public void onReceive(Context context, Intent intent) {
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
