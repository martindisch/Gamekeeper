package com.martin.gamekeeper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class PrefsFragment extends PreferenceFragment {

	private DbManager db;
	private int player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		db = new DbManager(getActivity());

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
						db.resetAll();
						Toast.makeText(getActivity(), R.string.all_reset, Toast.LENGTH_SHORT).show();
					}
				});
				builder.setNegativeButton(R.string.no, null);
				builder.show();
				return true;
			}
		});

	}

	@SuppressLint("InlinedApi")
	private void getPic(int i) {
		Intent intent = new Intent();
		player = i;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			startActivityForResult(intent, 1);
		} else {
			intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("image/*");
			startActivityForResult(intent, 1);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			db.setProfilePic(player, data.getData());
		}
		Toast.makeText(getActivity(), R.string.pic_updated, Toast.LENGTH_SHORT).show();
	}

}
