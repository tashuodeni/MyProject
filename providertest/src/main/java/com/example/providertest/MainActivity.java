package com.example.providertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addData;
    private Button queryData;
    private Button updateData;
    private Button deleteData;
    private String newId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        addData = (Button) findViewById(R.id.add_data);
        queryData = (Button) findViewById(R.id.query_data);
        updateData = (Button) findViewById(R.id.update_data);
        deleteData = (Button) findViewById(R.id.delete_data);

        addData.setOnClickListener(this);
        queryData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_data:
                break;
            case R.id.query_data:
                break;
            case R.id.update_data:
                break;
            case R.id.delete_data:
                break;
            default:
                break;
        }
    }
}
