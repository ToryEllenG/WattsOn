package com.gamboa.troy.WattsOn;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by Troygbv on 2/24/17.
 */

public class FragmentSettings extends PreferenceFragmentCompat {

    public FragmentSettings(){
        //required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        //preference intent for about activity
        Preference openAbout = findPreference("keyAbout");
        openAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent openStats = new Intent(getActivity(), AboutActivity.class);
                startActivity(openStats);
                return false;
            }
        });
        //preference intent for Contact Activity
        Preference openContact = findPreference("keyContact");
        openContact.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent open = new Intent(getActivity(), ContactActivity.class);
                startActivity(open);
                return false;
            }
        });

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
