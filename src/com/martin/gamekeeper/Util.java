package com.martin.gamekeeper;

import java.util.Calendar;

public class Util {

	private static boolean mCounting = false;
	private static boolean mPicsChanged = true;

	private static int[] mBACKGROUNDS = { R.drawable.card_background_blue, R.drawable.card_background_purple, R.drawable.card_background_green, R.drawable.card_background_orange,
			R.drawable.card_background_red };

	public static int getDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;
	}

	public static int getBackground(int day) {
		if (day >= 0 && day <= 4) {
			return mBACKGROUNDS[day];
		}
		return mBACKGROUNDS[4];
	}

	public static boolean counting() {
		return mCounting;
	}

	public static void startCounting() {
		mCounting = true;
	}

	public static void stopCounting() {
		mCounting = false;
	}

	public static void picsChanged() {
		mPicsChanged = true;
	}

	public static boolean picsChangedSinceLastCheck() {
		boolean old = mPicsChanged;
		mPicsChanged = false;
		return old;
	}

}
