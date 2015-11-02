package nl.waarisdetrein.hudlauncher.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import nl.waarisdetrein.hudlauncher.R;

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);
    }

}
