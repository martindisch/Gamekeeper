package com.martin.gamekeeper;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View view = findViewById(R.id.container);
		GradientDrawable shape = (GradientDrawable) view.getBackground();
		shape.setColor(getResources().getColor(android.R.color.holo_blue_bright));
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
