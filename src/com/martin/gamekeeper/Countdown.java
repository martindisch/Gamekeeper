package com.martin.gamekeeper;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.RemoteViews;

public class Countdown extends CountDownTimer {
	private RemoteViews views;
	private ComponentName thisWidget;
	private AppWidgetManager manager;

	public Countdown(Context context, long millisInFuture, int countDownInterval, RemoteViews views) {
		super(millisInFuture, countDownInterval);
		this.views = views;
		thisWidget = new ComponentName(context, WidgetProvider.class );
		manager = AppWidgetManager.getInstance(context);
	}

	@Override
	public void onFinish() {
		views.setTextViewText(R.id.tvTimer, "2:00");
		manager.updateAppWidget(thisWidget, views);
	}

	@Override
	public void onTick(long millisUntilFinished) {
		int minutes = (int) millisUntilFinished / 1000 / 60;
		int seconds = (int) (millisUntilFinished / 1000) - (minutes * 60);

		views.setTextViewText(R.id.tvTimer, minutes + ":" + seconds);
		manager.updateAppWidget(thisWidget, views);
	}
}
