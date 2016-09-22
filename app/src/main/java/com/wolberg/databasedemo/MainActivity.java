package com.wolberg.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText entryEditText;
    private Button showAllButton;
   // private Button showAllButton;
    //private Button showAllButton;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to widget
        entryEditText = (EditText) findViewById(R.id.entryEditText);
        showAllButton = (Button) findViewById(R.id.showAllButton);
        //showAllButton = (Button) findViewById(R.id.showAllButton);
       // showAllButton = (Button) findViewById(R.id.showAllButton);
        dbHandler = new MyDBHandler(this,null,null,1);
    }

    public void showAllClick(View view) {
        List<Products> dbString = dbHandler.databaseToString();
        String log = "";
        for(Products pn : dbString){
            log+="\nName: " + pn.getProductName()+"\n";
        }
        Intent i = new Intent(this,DisplayListActivity.class);
        i.putExtra("log",log);
        startActivity(i);
        entryEditText.setText("");
    }

    public void deleteClick(View view) {
        String entryText = entryEditText.getText().toString();
        dbHandler.deleteProduct(entryText);
    }

    public void addClick(View view) {
        Products products = new Products(entryEditText.getText().toString());
        dbHandler.addProduct(products);
    }
}
