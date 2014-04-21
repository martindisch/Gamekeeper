package com.martin.gamekeeper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayCard extends LinearLayout implements OnClickListener {

	private DbManager db;
	private int day;
	private TextView tvScore;

	@SuppressWarnings("deprecation")
	public DayCard(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DayCard, 0, 0);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.card, this, true);
		String[] days = context.getResources().getStringArray(R.array.days);
		((TextView) findViewById(R.id.tvDay)).setText(days[a.getInt(R.styleable.DayCard_day, 0)]);
		((TextView) findViewById(R.id.tvScore)).setText(a.getString(R.styleable.DayCard_candidate1Score) + " : " + a.getString(R.styleable.DayCard_candidate2Score));

		Drawable background = null;
		switch (a.getInt(R.styleable.DayCard_day, 0)) {
		case 0:
			background = getResources().getDrawable(R.drawable.card_background_blue);
			break;
		case 1:
			background = getResources().getDrawable(R.drawable.card_background_purple);
			break;
		case 2:
			background = getResources().getDrawable(R.drawable.card_background_green);
			break;
		case 3:
			background = getResources().getDrawable(R.drawable.card_background_orange);
			break;
		case 4:
			background = getResources().getDrawable(R.drawable.card_background_red);
			break;
		}

		findViewById(R.id.llCardRoot).setBackgroundDrawable(background);
		a.recycle();
	}

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

		Drawable background = null;
		switch (day) {
		case 0:
			background = getResources().getDrawable(R.drawable.card_background_blue);
			break;
		case 1:
			background = getResources().getDrawable(R.drawable.card_background_purple);
			break;
		case 2:
			background = getResources().getDrawable(R.drawable.card_background_green);
			break;
		case 3:
			background = getResources().getDrawable(R.drawable.card_background_orange);
			break;
		case 4:
			background = getResources().getDrawable(R.drawable.card_background_red);
			break;
		}

		findViewById(R.id.llCardRoot).setBackgroundDrawable(background);

		// Prepare edit buttons
		if (editMode) {
			ImageButton current;
			current = (ImageButton) findViewById(R.id.ibP1Inc);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
			current = (ImageButton) findViewById(R.id.ibP2Inc);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
			current = (ImageButton) findViewById(R.id.ibP1Dec);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
			current = (ImageButton) findViewById(R.id.ibP2Dec);
			current.setVisibility(VISIBLE);
			current.setOnClickListener(this);
		}
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

}
