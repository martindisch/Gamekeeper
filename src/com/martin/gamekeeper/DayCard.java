package com.martin.gamekeeper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayCard extends LinearLayout {

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
	}

}
