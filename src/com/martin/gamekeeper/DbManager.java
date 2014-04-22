package com.martin.gamekeeper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class DbManager {
	
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	
	@SuppressLint("CommitPrefEdits")
	public DbManager(Context context) {
		super();
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
	
	private void callUpdate() {
		
	}

}
