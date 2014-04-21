package com.martin.gamekeeper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayCard extends LinearLayout {

	public DayCard(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DayCard, 0, 0);
		try {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(R.layout.daycard_layout, this, true);
			String[] days = context.getResources().getStringArray(R.array.days);
			((TextView) findViewById(R.id.tvDay)).setText(days[a.getInt(R.styleable.DayCard_day, 0)]);
			((TextView) findViewById(R.id.tvScore)).setText(a.getString(R.styleable.DayCard_candidate1Score) + " : " + a.getString(R.styleable.DayCard_candidate2Score));
		}
		catch (Exception e) {
			
		}
		finally {
			a.recycle();
		}
	}

}
