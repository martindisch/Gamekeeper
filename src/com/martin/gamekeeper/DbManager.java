package com.martin.gamekeeper;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

public class DbManager {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	private Context context;

	@SuppressLint("CommitPrefEdits")
	public DbManager(Context context) {
		super();
		this.context = context;
		sp = context.getSharedPreferences("Gamedata", Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public void setScoreForDay(int player, int day, int value) {
		editor.putInt(player + "" + day, value);
		editor.commit();
		callUpdate();
	}

	public int getScoreForDay(int player, int day) {
		return sp.getInt(player + "" + day, 0);
	}

	public String getResultForDay(int day) {
		return (sp.getInt("1" + day, 0) + " : " + sp.getInt("2" + day, 0));
	}

	public void resetAll() {
		editor.clear();
		editor.commit();
		callUpdate();
	}

	public void setProfilePic(int player, Uri uri) {
		editor.putString(player + "pic", uri.toString());
		editor.commit();
		Util.picsChanged();
		callUpdate();
	}

	public Uri getPicUri(int player) {
		return Uri.parse(sp.getString(player + "pic", "null"));
	}

	public void saveSize(int[] size) {
		editor.putInt("x", size[0]);
		editor.putInt("y", size[1]);
		editor.commit();
	}

	public int[] getSavedSize() {
		int size[] = new int[2];
		size[0] = sp.getInt("x", 0);
		size[1] = sp.getInt("y", 0);
		return size;
	}

	private void callUpdate() {
		Intent rIntent = new Intent(context, WidgetProvider.class);
		rIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
		int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
		rIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
		context.sendBroadcast(rIntent);
	}

}
