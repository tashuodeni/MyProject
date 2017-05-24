package com.example.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private String newId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button addData;
        Button queryData;
        Button updateData;
        Button deleteData;

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
        Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
        ContentValues values = new ContentValues();
        switch (v.getId()) {
            case R.id.add_data:
                //添加数据
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                values.clear();
                //Log.d("MainActivity", "uri: " + uri);
                //Log.d("MainActivity", "newUri: " + newUri);
                // Log.d("MainActivity", "newId: " + newId);
                break;
            case R.id.query_data:
                //查询数据
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "name: " + name);
                        Log.d("MainActivity", "author: " + author);
                        Log.d("MainActivity", "pages: " + pages);
                        Log.d("MainActivity", "price: " + price);
                    }
                    cursor.close();
                }
                break;
            case R.id.update_data:
                //更新数据
                if (newId != null) {
                    uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                    values.put("name", "A Storm of Swords");
                    values.put("pages", 1216);
                    values.put("price", 24.05);
                    getContentResolver().update(uri, values, null, null);
                    values.clear();
                }else{ Log.d("MainActivity", "newId is empty");}
                break;
            case R.id.delete_data:
                //删除数据
                if (newId != null) {
                    uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                    getContentResolver().delete(uri,null,null);
                }
                break;
            default:
                break;
        }
    }
}
