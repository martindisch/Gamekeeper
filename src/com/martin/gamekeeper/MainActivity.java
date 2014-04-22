package com.martin.gamekeeper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	boolean editMode = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
	}

	@Override
	protected void onResume() {
		super.onResume();
		displayDays();
	}

	private void displayDays() {
		setContentView(R.layout.activity_main);

		LinearLayout root = (LinearLayout) findViewById(R.id.container);
		DbManager db = new DbManager(this);
		
		for (int i = 0; i < 5; i++) {
			root.addView(new DayCard(this, i, db.getResultForDay(i), editMode));
		}

		// Get Uri's
		Uri p1Uri = db.getPicUri(1);
		Uri p2Uri = db.getPicUri(2);

		// Get size of imageviews
		int[] size = ((DayCard) root.getChildAt(0)).getSize();
		int width = size[0];
		int height = size[1];

		// Fill bitmaps
		Bitmap pic1 = null, pic2 = null;
		if (!p1Uri.toString().contentEquals("null")) {
			pic1 = Bitmapper.decodeSampledBitmap(this, p1Uri, width, height);
		}
		if (!p2Uri.toString().contentEquals("null")) {
			pic2 = Bitmapper.decodeSampledBitmap(this, p2Uri, width, height);
		}

		// If at least one has a custom picture, update
		if (pic1 != null || pic2 != null) {
			for (int i = 0; i < 5; i++) {
				((DayCard) root.getChildAt(i)).setProfiles(pic1, pic2);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_editmode:
			editMode = !editMode;
			displayDays();
			break;
		case R.id.action_settings:
			Intent i = new Intent(MainActivity.this, PrefsActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
		case R.id.action_about:

			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
