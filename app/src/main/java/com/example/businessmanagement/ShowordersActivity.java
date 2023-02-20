package com.example.businessmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ShowordersActivity extends AppCompatActivity {
    private RecyclerView showorders_RCY_list;
    private Button showorders_BTN_back;
    private showOrdersAdapter showOrdersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showorders);
        findView();
        initView();
    }

    private void initView() {
        showOrdersAdapter = new showOrdersAdapter(DataManneger.getOrders(),this);
        showorders_RCY_list.setAdapter(showOrdersAdapter);

        showorders_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findView() {
        showorders_RCY_list=findViewById(R.id.showorders_RCY_list);
        showorders_BTN_back=findViewById(R.id.showorders_BTN_back);
    }
}