package com.example.businessmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class LogintoshiftsActivity extends AppCompatActivity {
    private Spinner  spinner1;
    private MaterialButton _BTN_ok;
    private MaterialButton _BTN_back;

    private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintoshifts);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        findView();
        intView();
    }

   private void intView() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,DataManneger.getId());
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        _BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.readShifts(s,DataManneger.getAmdminid());
                database.readWorker(s,DataManneger.getAmdminid());
                Intent intent = new Intent(LogintoshiftsActivity.this, ShiftsActivity.class);
                startActivity(intent);
            }
        });

       _BTN_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

    }

    private void findView() {
        spinner1=findViewById(R.id.spinner1);
        _BTN_ok=findViewById(R.id._BTN_ok);
        _BTN_back=findViewById(R.id._BTN_back);
    }
}