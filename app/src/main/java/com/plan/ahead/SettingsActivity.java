package com.plan.ahead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.plan.ahead.Storage.StorageUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle(getResources().getString(R.string.nav_settings));
    }

    public void deleteEvents(View view) {
        StorageUtils.deleteAllEvents();
        Toast.makeText(getApplicationContext(), "Events deleted", Toast.LENGTH_LONG).show();
    }
}
