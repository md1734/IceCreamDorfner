package com.example.mitch.icecreamdorfner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    ArrayList<OrderItem> order;
    ArrayList<String> stringOrder;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Intent i = getIntent();
        order = (ArrayList<OrderItem>) i.getSerializableExtra("OrderKey");

        stringOrder = new ArrayList<String>();
        for (OrderItem item: order) {
            stringOrder.add(item.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1, stringOrder);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
