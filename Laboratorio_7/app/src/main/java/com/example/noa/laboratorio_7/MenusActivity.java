package com.example.noa.laboratorio_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenusActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        Button bShowPreferences = (Button) findViewById(R.id.btnShowPreferences);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        bShowPreferences.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                String username =
                        preferences.getString("username", "n/a");
                String password = preferences.getString(
                        "password", "n/a");
                showPrefs(username, password);
            }
        });

        Button buttonChangePreferences =
                (Button) findViewById(R.id.btnChangePreferences);
        buttonChangePreferences.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        updatePreferenceValue();
                    }
                });
    }

    public void showPrefs(String usr, String pass){
        Toast.makeText(this, "El usuario es "+usr+" y la contraseña: "+pass,
                Toast.LENGTH_LONG).show();

    }

    private void updatePreferenceValue(){
        SharedPreferences.Editor edit = preferences.edit();
        String username = preferences.getString("username", "n/a");
        StringBuffer buffer = new StringBuffer();
        for (int i = username.length() - 1; i >= 0; i--) {
            buffer.append(username.charAt(i));
        }
        edit.putString("username", buffer.toString());
        edit.commit();
        Toast.makeText(this, "Explique que realiza",
                Toast.LENGTH_LONG).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                Intent i = new Intent(this, MisPreferencias.class);
                startActivity(i);
                Toast.makeText(this, "Introduce nombre/pass",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}

































