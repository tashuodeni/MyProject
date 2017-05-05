package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Button createDatabase = (Button) findViewById(R.id.create_database);
        Button addData = (Button) findViewById(R.id.add_data);
        Button updateData = (Button) findViewById(R.id.update_data);
        Button deleteData = (Button) findViewById(R.id.delete_data);
        Button queryData = (Button) findViewById(R.id.query_data);

        createDatabase.setOnClickListener(this);
        addData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        queryData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Book book;
        switch (v.getId()) {
            case R.id.create_database:
                Connector.getDatabase();
                break;
            case R.id.add_data:
                book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
                break;
            case R.id.update_data:
                book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");
                break;
            case R.id.delete_data:
                DataSupport.deleteAll(Book.class,"price<?","15");
                break;
            case R.id.query_data:
                List<Book> books=DataSupport.findAll(Book.class);
                final String TAG="MainActivity";
                for (Book book1:books) {
                    Log.d(TAG, "book name is "+book1.getName());
                    Log.d(TAG, "book author is "+book1.getAuthor());
                    Log.d(TAG, "book pages is "+book1.getPages());
                    Log.d(TAG, "book price is "+book1.getPrice());
                    Log.d(TAG, "book press is "+book1.getPress());
                }
                break;
            default:
                break;
        }
    }
}
