package com.example.businessmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;

public class AddworkerActivity extends AppCompatActivity {
    private EditText worker_name;
    private EditText worker_id;
    private EditText worker_salary;
    private MaterialRadioButton radia_id1;
    private MaterialRadioButton radia_id2;
    private MaterialButton addworker_BTN_add;
    private MaterialButton addworker_BTN_back;
    private RadioGroup home_radiogroup;
    private String name;
    private String title;
    private int id;
    private int salary;
    private String radio_title ="";
    private Worker w=new Worker();
    private ArrayList<Shifts> shifts=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_addworker);
        findViews();
        initViews();

    }

    private void initViews() {
        addworker_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addworker_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if( cheakisok()){
                   shifts=null;
                     w=new Worker(shifts,name, title,id,salary);
                    Log.d("message3",DataManneger.chekisin(w).toString());
                   database.updateWorker(w,DataManneger.getAmdminid());
                   DataManneger.getId().add(id);
                    if(DataManneger.getWorkers().size()==0||!DataManneger.chekisin(w)){
                        DataManneger.addWorker(w);
                        worker_name.setText("");
                        worker_id.setText("");
                        worker_salary.setText("");
                        radia_id1.setChecked(false);
                        radia_id2.setChecked(false);
                       Log.d("message3",DataManneger.getWorkers().get(id).getName());

                        Toast.makeText(AddworkerActivity.this,"the worker has been added",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddworkerActivity.this,"there is a worker with the same id",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public Boolean cheakisok(){
        name=worker_name.getText().toString();

        if(name.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(worker_id.getText().toString().matches("")){
            id=0;
        }else {
            try {
                int d = Integer.parseInt((worker_id.getText().toString()));
                if((worker_id.getText().toString()).length()!=9){
                    Toast.makeText(this, "unlegal id1", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "unlegal id", Toast.LENGTH_SHORT).show();
                return false;
            }
            id = Integer.parseInt((worker_id.getText().toString()));
        }
       if(id==0) {
            Toast.makeText(this, "You did not enter a id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(worker_salary.getText().toString().matches("")){
            salary=0;
        }else {
            try {
                int d = Integer.parseInt((worker_salary.getText().toString()));
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "unlegal salary", Toast.LENGTH_SHORT).show();
                return false;
            }
            salary = Integer.parseInt((worker_salary.getText().toString()));
        }
        if(salary==0){
            Toast.makeText(this, "You did not enter a salary", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(checkRadio()!=-1) {
            title=radio_title;
        }else{
            Toast.makeText(this, "You did not enter a title", Toast.LENGTH_SHORT).show();
            return false;
        }
    return true;
    }
    public int checkRadio(){
        int radioId=home_radiogroup.getCheckedRadioButtonId();
        if(radioId!=-1){
            MaterialRadioButton radioButton=findViewById(radioId);
            radio_title=radioButton.getText().toString();
           }
        return (radioId);
    }

    private void findViews() {
        worker_name=findViewById(R.id.worker_name);
        worker_id=findViewById(R.id.worker_id);
        worker_salary=findViewById(R.id.worker_salary);
        radia_id1=findViewById(R.id.radia_id1);
        radia_id2=findViewById(R.id.radia_id2);
        addworker_BTN_add=findViewById(R.id.addworker_BTN_add);
        addworker_BTN_back=findViewById(R.id.addworker_BTN_back);
        home_radiogroup=findViewById(R.id.home_radiogroup);
    }
}