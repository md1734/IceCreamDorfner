package com.example.mitch.icecreamdorfner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView storeNameTextView;
    TextView descriptionTextView;
    TextView developerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        storeNameTextView = (TextView) findViewById(R.id.storeNameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        developerTextView = (TextView) findViewById(R.id.developerTextView);

        Intent i = getIntent();
        String title = i.getStringExtra("Title");
        storeNameTextView.setText(title);
        String desc = i.getStringExtra("ShopInfo");
        descriptionTextView.setText(desc);
        String dev = i.getStringExtra("Developer");
        developerTextView.setText(dev);
    }
}
