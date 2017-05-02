package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button saveData;
    private Button restoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        saveData = (Button) findViewById(R.id.save_data);
        restoreData = (Button) findViewById(R.id.restore_data);
        saveData.setOnClickListener(this);
        restoreData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_data:
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
                break;
            case R.id.restore_data:
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String name=pref.getString("name","");
                int age=pref.getInt("age",0);
                boolean married=pref.getBoolean("married",false);
                Log.d("MainActivity", "name is "+name);
                Log.d("MainActivity", "age is "+age);
                Log.d("MainActivity", "married is "+married);
        }
    }
}
