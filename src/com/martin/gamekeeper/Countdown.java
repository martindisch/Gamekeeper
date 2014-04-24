package com.martin.gamekeeper;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.RemoteViews;

public class Countdown extends CountDownTimer {
	private RemoteViews views;
	private ComponentName thisWidget;
	private AppWidgetManager manager;
	private Context mContext;

	public Countdown(Context context, long millisInFuture, int countDownInterval, RemoteViews views) {
		super(millisInFuture, countDownInterval);
		this.views = views;
		mContext = context;
		thisWidget = new ComponentName(context, WidgetProvider.class);
		manager = AppWidgetManager.getInstance(context);
	}

	@Override
	public void onFinish() {
		Vibrator v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 0, 100, 100, 100, 100, 200, 100, 100, 100, 100, 100, 200 };
		v.vibrate(pattern, -1);

		views.setTextViewText(R.id.tvTimer, "2:00");
		manager.partiallyUpdateAppWidget(manager.getAppWidgetIds(thisWidget), views);
		Util.stopCounting();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		int minutes = (int) millisUntilFinished / 1000 / 60;
		int seconds = (int) (millisUntilFinished / 1000) - (minutes * 60);
		if (seconds < 10) {
			views.setTextViewText(R.id.tvTimer, minutes + ":0" + seconds);
		} else {
			views.setTextViewText(R.id.tvTimer, minutes + ":" + seconds);
		}
		manager.partiallyUpdateAppWidget(manager.getAppWidgetIds(thisWidget), views);
	}
}
