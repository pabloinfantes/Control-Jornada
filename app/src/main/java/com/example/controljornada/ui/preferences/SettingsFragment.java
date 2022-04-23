package com.example.controljornada.ui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.controljornada.R;

public class SettingsFragment extends PreferenceFragmentCompat{


    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

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
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(getString(R.string.key_ringtone))){
                    Preference pre_sonido= findPreference(key);
                    pre_sonido.setSummary(sharedPreferences.getString(key,""));
                }
                if (key.equals(getString(R.string.key_hora))){
                    Preference pre_hora= findPreference(key);
                    pre_hora.setSummary(sharedPreferences.getString(key,"8:01"));
                }
            }
        };
    }



    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference sonidoPref = findPreference(getString(R.string.key_ringtone));
        sonidoPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(getString(R.string.key_ringtone),""));

        Preference horaPref = findPreference(getString(R.string.key_hora));
        horaPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(getString(R.string.key_hora),"8:01"));

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }



}