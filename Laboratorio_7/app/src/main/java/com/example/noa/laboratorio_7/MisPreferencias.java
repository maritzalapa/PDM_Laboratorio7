package com.example.noa.laboratorio_7;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MisPreferencias extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Descomentar
//        setContentView(R.layout.activity_mis_preferencias);
        addPreferencesFromResource(R.xml.preferences);
    }
}


















