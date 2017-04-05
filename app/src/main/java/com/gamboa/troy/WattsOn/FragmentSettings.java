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

public class FragmentSettings extends PreferenceFragmentCompat  {

    public FragmentSettings(){
        //required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        //variables for settings entities
        Preference openAbout = findPreference("keyAbout");
        Preference openHouseRegister = findPreference("openHouseRegister");
        Preference openUser = findPreference("openUser");
        Preference openStats = findPreference("stats");

        //preference intent for about activity
        openAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent openStats = new Intent(getActivity(), AboutActivity.class);
                startActivity(openStats);
                return false;
            }
        });

        //preference intent for User Activity. Make new activity and change this later
        openUser.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent open = new Intent(getActivity(), ShowUserData.class);
                String username = getActivity().getIntent().getExtras().getString("username");
                Bundle extras = new Bundle();
                extras.putString("username", username);
                open.putExtras(extras);
                startActivity(open);
                return false;
            }
        });
        //View Past comparisons
        openStats.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent open = new Intent(getActivity(), StatisticsActivity.class);
                startActivity(open);
                return false;
            }
        });

        //preference intent for House Register Activity. Make new activity and change this later
        openHouseRegister.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent open = new Intent(getActivity(), HouseRegisterActivity.class);
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
