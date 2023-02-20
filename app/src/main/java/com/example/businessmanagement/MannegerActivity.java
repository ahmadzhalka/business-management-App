package com.example.businessmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MannegerActivity extends AppCompatActivity {
    private MaterialButton manneger_BTN_addemploy;
    private MaterialButton manneger_BTN_makeorder;
    private MaterialButton manneger_BTN_seeorder;
    private MaterialButton manneger_BTN_shift;
    private MaterialButton  manneger_BTN_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_manneger);
        findViews();
        initViews();
        database.readWorkers(DataManneger.getAmdminid());
        database.readPDF(DataManneger.getAmdminid());

    }

    private void initViews() {
        manneger_BTN_addemploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MannegerActivity.this, AddworkerActivity.class);
                startActivity(intent);
            }
        });

        manneger_BTN_seeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MannegerActivity.this,ShowordersActivity.class);
                startActivity(intent);
            }
        });
        manneger_BTN_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MannegerActivity.this, LogintoshiftsActivity.class);
                startActivity(intent);
            }
        });
        manneger_BTN_makeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MannegerActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        manneger_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findViews() {
        manneger_BTN_addemploy=findViewById(R.id.manneger_BTN_addemploy);
        manneger_BTN_makeorder=findViewById(R.id.manneger_BTN_makeorder);
        manneger_BTN_seeorder=findViewById(R.id.manneger_BTN_seeorder);
        manneger_BTN_shift=findViewById(R.id.manneger_BTN_shift);
        manneger_BTN_exit=findViewById(R.id.manneger_BTN_exit);
    }
}