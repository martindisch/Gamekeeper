package com.martin.gamekeeper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class DayCard extends LinearLayout implements OnClickListener {

	private DbManager db;
	private int day;
	private TextView tvScore;
	private ImageView pic1, pic2;

	@SuppressWarnings("deprecation")
	public DayCard(Context context, int day, String score, boolean editMode) {
		super(context);
		db = new DbManager(context);
		this.day = day;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.card, this, true);

		String[] days = context.getResources().getStringArray(R.array.days);
		((TextView) findViewById(R.id.tvDay)).setText(days[day]);
		tvScore = (TextView) findViewById(R.id.tvScore);
		tvScore.setText(score);

		findViewById(R.id.llCardRoot).setBackgroundDrawable(getResources().getDrawable(Util.getBackground(day)));

		// Prepare edit buttons
		if (editMode) {
			ImageButton current;
			current = (ImageButton) findViewById(R.id.ibP1Inc);
			current.setColorFilter(getResources().getColor(android.R.color.darker_gray), Mode.SRC_OUT);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
			current = (ImageButton) findViewById(R.id.ibP2Inc);
			current.setColorFilter(getResources().getColor(android.R.color.darker_gray), Mode.SRC_OUT);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
			current = (ImageButton) findViewById(R.id.ibP1Dec);
			current.setColorFilter(getResources().getColor(android.R.color.darker_gray), Mode.SRC_OUT);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
			current = (ImageButton) findViewById(R.id.ibP2Dec);
			current.setColorFilter(getResources().getColor(android.R.color.darker_gray), Mode.SRC_OUT);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
		}

		pic1 = (ImageView) findViewById(R.id.ivC1);
		pic2 = (ImageView) findViewById(R.id.ivC2);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibP1Inc:
			db.setScoreForDay(1, day, db.getScoreForDay(1, day) + 1);
			break;
		case R.id.ibP1Dec:
			db.setScoreForDay(1, day, db.getScoreForDay(1, day) - 1);
			break;
		case R.id.ibP2Inc:
			db.setScoreForDay(2, day, db.getScoreForDay(2, day) + 1);
			break;
		case R.id.ibP2Dec:
			db.setScoreForDay(2, day, db.getScoreForDay(2, day) - 1);
			break;
		}
		tvScore.setText(db.getResultForDay(day));
	}

	public int[] getSize() {
		int[] size = new int[2];
		size[0] = pic1.getDrawable().getIntrinsicWidth();
		size[1] = pic1.getDrawable().getIntrinsicHeight();
		return size;
	}

	public void setProfiles(Bitmap p1, Bitmap p2) {
		if (p1 != null) {
			pic1.setImageBitmap(p1);
		}
		if (p2 != null) {
			pic2.setImageBitmap(p2);
		}
	}

}
