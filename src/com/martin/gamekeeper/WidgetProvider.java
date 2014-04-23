package com.martin.gamekeeper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	private static final String C1Inc = "C1_Score";
	private static final String C2Inc = "C2_Score";
	private static boolean fullyUpdated = false;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		DbManager db = new DbManager(context);

		// Get the views for the widget
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

		// Background
		views.setInt(R.id.llCardRoot, "setBackgroundResource", Util.getBackground(Util.getDay()));

		// Day
		views.setTextViewText(R.id.tvDay, context.getResources().getStringArray((R.array.days))[Util.getDay()]);

		// Score
		views.setTextViewText(R.id.tvScore, db.getResultForDay(Util.getDay()));

		// Profile pictures
		// Only load images if necessary
		if (Util.picsChangedSinceLastCheck()) {
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
			views.setOnClickPendingIntent(R.id.ivC1, getPendingSelfIntent(context, C1Inc));
			views.setOnClickPendingIntent(R.id.ivC2, getPendingSelfIntent(context, C2Inc));
		}

		ComponentName comp = new ComponentName(context, WidgetProvider.class);
		if (!fullyUpdated) {
			appWidgetManager.updateAppWidget(appWidgetManager.getAppWidgetIds(comp), views);
			fullyUpdated = true;
		} else {
			appWidgetManager.partiallyUpdateAppWidget(appWidgetManager.getAppWidgetIds(comp), views);
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		if (C1Inc.equals(intent.getAction()) || C2Inc.equals(intent.getAction())) {
			// Only allow interaction when not counting already
			if (!Util.counting()) {
				DbManager db = new DbManager(context);

				int player = 0;
				if (C1Inc.equals(intent.getAction())) {
					player = 1;
				}
				if (C2Inc.equals(intent.getAction())) {
					player = 2;
				}
				int prev = db.getScoreForDay(player, Util.getDay());
				prev++;
				db.setScoreForDay(player, Util.getDay(), prev);

				Countdown countdown = new Countdown(context, 120000, 1000, new RemoteViews(context.getPackageName(), R.layout.widget_layout));
				countdown.start();
				Util.startCounting();
			}
			else {
				Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
				long[] pattern = {0, 100};
				v.vibrate(pattern, -1);
			}
		}
	}

	protected PendingIntent getPendingSelfIntent(Context context, String action) {
		Intent intent = new Intent(context, getClass());
		intent.setAction(action);
		return PendingIntent.getBroadcast(context, 0, intent, 0);
	}
}
