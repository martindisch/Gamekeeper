package com.martin.gamekeeper;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class AboutFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.about);
	}

}
