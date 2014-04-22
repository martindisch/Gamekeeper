package com.martin.gamekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class PrefsFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.settings);
		
		((Preference) findPreference("p1pic")).setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				getPic(1);
				return true;
			}
		});
		((Preference) findPreference("p2pic")).setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				getPic(2);
				return true;
			}
		});
		((Preference) findPreference("reset")).setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(R.string.reset_message);
				builder.setTitle(R.string.reset_title);
				builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DbManager db = new DbManager(getActivity());
						db.resetAll();
					}
				});
				builder.setNegativeButton(R.string.no, null);
				builder.show();
				return true;
			}
		});
		
	}
	
	private void getPic(int i) {
		
	}
}
