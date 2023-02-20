package com.example.businessmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Date;
import java.util.Locale;

public class SigninActivity extends AppCompatActivity {
    private EditText home_TXT_id;
    private MaterialButton home_BTN_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_signin);
        findViews();
        initViews();

    }

    private void initViews() {
        home_BTN_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = home_TXT_id.getText().toString();
                if(check()==false){

                }else{
                    if (DataManneger.getAmdminid() == null) {
                        DataManneger.setAmdminid(home_TXT_id.getText().toString());
                        database.updateAdmin(home_TXT_id.getText().toString());
                        Log.d("message3", DataManneger.getAmdminid());
                        Toast.makeText(SigninActivity.this, "you are the admin ", Toast.LENGTH_SHORT).show();

                    } else {
                        if (id.matches(DataManneger.getAmdminid())) {
                            Log.d("message3", "ok");
                            Intent intent = new Intent(SigninActivity.this, MannegerActivity.class);
                            startActivity(intent);

                        } else {

                            database.readWorker(id,DataManneger.getAmdminid());
                            database.readShifts(id,DataManneger.getAmdminid());
                            if (id.matches(String.valueOf(DataManneger.worker.getId()))) {
                                String currentDate=new java.text.SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                database.getShift(""+id,currentDate,DataManneger.getAmdminid());
                                Intent intent = new Intent(SigninActivity.this, Activity_Splash2.class);
                                startActivity(intent);
                                Log.d("message3", "ok2");

                            }
                        }
                    }
                }
            }
        });
    }

    private boolean check(){
        String id = home_TXT_id.getText().toString();
        try {
            int d = Integer.parseInt((id));
            if ((id).length() != 9) {
                Toast.makeText(SigninActivity.this, "unlegal id", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } catch (NumberFormatException nfe) {
            Toast.makeText(SigninActivity.this, "unlegal id", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private void findViews() {
        home_TXT_id=findViewById(R.id.home_TXT_id);
        home_BTN_sign=findViewById(R.id.home_BTN_sign);
    }

}