package com.example.businessmanagement;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ShiftsActivity extends AppCompatActivity {
    private TextView monthYear;
    private MaterialButton shift_BTN_next;
    private MaterialButton shift_BTN_back;
    private MaterialButton shift_BTN_back1;
    private MaterialTextView shifts_TXT_salary;
    private RecyclerView shifts_LST_shifts;
    private ListActivity listActivity;
    private FrameLayout panel_LAY_list;
    int currentMonth;
    int currentYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_shifts);
        Intent previousIntent = getIntent();

        showshifts();
        currentYear = Integer.parseInt(new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date()));
        currentMonth = Integer.parseInt(new SimpleDateFormat("MM", Locale.getDefault()).format(new Date()));
        findViews();
        initViews();
        DataManneger.setCurrentMonth(currentMonth);
        shifts_TXT_salary.setText(""+DataManneger.getWorker().getSalary()*DataManneger.getShiftsBYM().size());


    }
    private void showshifts(){
            listActivity = new ListActivity();
            getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list, listActivity).commit();

    }

    private void findViews() {
        shifts_LST_shifts = findViewById(R.id.shifts_LST_shifts);
        monthYear=findViewById(R.id.monthYear);
        shift_BTN_next=findViewById(R.id.shift_BTN_next);
        shift_BTN_back=findViewById(R.id.shift_BTN_back);
        monthYear.setText(currentMonth+"  "+currentYear+"");
        shift_BTN_back1=findViewById(R.id.shift_BTN_back1);
        panel_LAY_list=findViewById(R.id.panel_LAY_list);
        shifts_TXT_salary=findViewById(R.id.shifts_TXT_salary);

    }
    private void initViews() {
        shift_BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMonth++;
                if(currentMonth==13){
                    currentMonth=1;
                    currentYear++;
                }
                DataManneger.setCurrentMonth(currentMonth);
                findViews();
                panel_LAY_list.removeAllViews();
                showshifts();
                shifts_TXT_salary.setText(""+DataManneger.getWorker().getSalary()*DataManneger.getShiftsBYM().size());

            }
        });
        shift_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentMonth==1){
                    currentMonth=12;
                    currentYear--;
                }else{
                currentMonth--;}
                DataManneger.setCurrentMonth(currentMonth);
                panel_LAY_list.removeAllViews();
                findViews();
                showshifts();
                double x=0;
                for (int i = 0; i <DataManneger.getShiftsBYM().size() ; i++) {

                    x=x+DataManneger.getShiftsBYM().get(i).getShiftTime();
                }
                shifts_TXT_salary.setText(""+DataManneger.getWorker().getSalary()*(x/8));

            }
        });
        shift_BTN_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panel_LAY_list.removeAllViews();
                finish();
            }
        });
    }


}