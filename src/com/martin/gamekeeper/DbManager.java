package com.martin.gamekeeper;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class DbManager {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	private Bitmap bitmap;
	private Context context;
	private boolean available;

	@SuppressLint("CommitPrefEdits")
	public DbManager(Context context) {
		super();
		this.context = context;
		available = false;
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
		callUpdate();
	}

	public boolean picAvailable(int player) {
		available = false;
		try {
			Uri imageUri = Uri.parse(sp.getString(player + "pic", "null"));
			
			// Potentially critical when other threads are trying to access the bitmap
			if (bitmap != null) {
				bitmap.recycle();
			}

			InputStream stream = context.getContentResolver().openInputStream(imageUri);
			
			BitmapFactory.Options options=new BitmapFactory.Options();
			options.inSampleSize = 10;
			
			bitmap = BitmapFactory.decodeStream(stream,null,options);

			stream.close();
			available = true;
		} catch (Exception e) {
			available = false;
		}
		return available;
	}

	public Bitmap getProfilePic(int player) {
		return bitmap;
	}

	private void callUpdate() {

	}

}
