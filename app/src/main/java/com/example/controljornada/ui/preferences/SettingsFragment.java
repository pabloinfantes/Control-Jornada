package com.example.controljornada.ui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.controljornada.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference preference = getPreferenceManager().findPreference(getString(R.string.key_help));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(), "Se ha pulsado sobre la ayuda", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Settings Fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        //Voy a buscar si la preferencia es mi lista ringtone
        if (key.equals(getString(R.string.key_ringtone))) {
            /*
                Como he comprobado previamente que la preferencia que se ha modificado es una
                lista, se puede hacer downcasting
             */
            ListPreference listPreference = (ListPreference) preference;

            //Se recoge el indice seleccionado
            int index = listPreference.findIndexOfValue(sharedPreferences.getString(key, ""));
            if (index >= 0) {
                preference.setSummary(listPreference.getEntries()[index]);
            } else {
                listPreference.setSummary(sharedPreferences.getString(key, ""));
            }
        }
    }
}