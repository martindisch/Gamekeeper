package com.martin.gamekeeper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LinearLayout root = (LinearLayout) findViewById(R.id.container);
		DbManager db = new DbManager(this);
		
		for (int i = 0; i < 5; i++) {
			root.addView(new DayCard(this, i, db.getResultForDay(i)));
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
		case R.id.action_settings:
			
			break;
		case R.id.action_about:
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
