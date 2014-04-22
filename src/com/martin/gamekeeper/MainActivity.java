package com.martin.gamekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	boolean editMode = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
		displayDays();
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
