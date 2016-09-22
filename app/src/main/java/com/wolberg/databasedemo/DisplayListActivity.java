package com.wolberg.databasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        Bundle data = getIntent().getExtras();
        if(data==null){
            return;
        }
        String msg = (String) data.get("log");
        final TextView displayTextView = (TextView) findViewById(R.id.displayTextView);
        displayTextView.setText(msg);
    }
}
