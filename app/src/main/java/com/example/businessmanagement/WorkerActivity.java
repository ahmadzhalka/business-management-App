package com.example.businessmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.text.ParseException;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class WorkerActivity extends AppCompatActivity {
    private MaterialButton worker_BTN_start;
    private MaterialButton worker_BTN_end;
    private MaterialButton worker_BTN_shifts;
    String currentTime;
    private Shifts shift;
    int currentMonth;
    int currentYear;
    String currentDate;
    Worker worker=DataManneger.getWorker();
    private TextView worker_TXT_title;
    private TextView worker_TXT_name;
    private MaterialButton worker_BTN_order;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_worker);
        Log.d("message3",worker.getName());
        findViews();
        worker_TXT_name.setText(worker.getName());
        worker_TXT_title.setText(worker.getTitle());

        if(worker.getTitle().matches("WORKER")){
            Log.d("message3",""+worker.getTitle());
            worker_BTN_order.setVisibility(View.INVISIBLE);
        }
        initViews();
        showShifts();

        if(DataManneger.getSh().size()!=0&&DataManneger.getSh().get(DataManneger.getSh().size()-1).getEnd()==null&&DataManneger.getSh().get(DataManneger.getSh().size()-1).getStart()!=null){
            Log.w("pttt", ""+DataManneger.getSh().get(DataManneger.getSh().size()-1).getEnd());
            worker_BTN_start.setVisibility(View.INVISIBLE);
            worker_BTN_end.setVisibility(View.VISIBLE);
        }



    }

    private void showShifts() {
             worker_BTN_shifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WorkerActivity.this, ShiftsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        worker_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shift=new Shifts();
                worker_BTN_start.setVisibility(View.INVISIBLE);
                worker_BTN_end.setVisibility(View.VISIBLE);
                currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                currentYear = Integer.parseInt(new java.text.SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date()));
                currentMonth = Integer.parseInt(new java.text.SimpleDateFormat("MM", Locale.getDefault()).format(new Date()));
                currentDate=new java.text.SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                shift.setDate(currentDate);
                shift.setMonth(currentMonth);
                shift.setYear(currentYear);
                shift.setStart(currentTime);
                shift.setCount((DataManneger.getSh().size()+1));
                DataManneger.sh.add(shift);
                Log.w("pttt", ""+shift.getCount());
                database.updateShift(String.valueOf(worker.getId()),shift,DataManneger.getAmdminid());

            }
        });
        worker_BTN_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worker_BTN_start.setVisibility(View.VISIBLE);
                worker_BTN_end.setVisibility(View.INVISIBLE);
                client = LocationServices.getFusedLocationProviderClient(WorkerActivity.this);
                if (ActivityCompat.checkSelfPermission(WorkerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    ActivityCompat.requestPermissions(WorkerActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }


                Log.w("pttt", ""+DataManneger.getSh().get(DataManneger.getSh().size()-1).getCount());

                DataManneger.getSh().get(DataManneger.getSh().size()-1).setEnd( new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                double differenceInMilliSeconds;
                differenceInMilliSeconds = ( chek(DataManneger.getSh().get(DataManneger.getSh().size()-1).getEnd()).getTime()- chek(DataManneger.getSh().get(DataManneger.getSh().size()-1).getStart()).getTime());
                double differenceInHours
                        = (differenceInMilliSeconds / (60 * 60 * 1000))
                        % 24;
                Log.w("msq", ""+differenceInHours);
                DataManneger.getSh().get(DataManneger.getSh().size()-1).setShiftTime(differenceInHours);
                database.updateShift(String.valueOf(DataManneger.getWorker().getId()),DataManneger.getSh().get(DataManneger.getSh().size()-1),DataManneger.getAmdminid());
            }
        });

        worker_BTN_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkerActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

    }
    private Date chek(String s){
        Date date=null ;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void findViews() {
        worker_BTN_start=findViewById(R.id.worker_BTN_start);
        worker_BTN_end=findViewById(R.id.worker_BTN_end);
        worker_BTN_end.setVisibility(View.INVISIBLE);
        worker_BTN_shifts=findViewById(R.id.worker_BTN_shifts);
        worker_TXT_title=findViewById(R.id.worker_TXT_title);
        worker_TXT_name=findViewById(R.id.worker_TXT_name);
        worker_BTN_order=findViewById(R.id.worker_BTN_order);



    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(WorkerActivity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Log.d("message2",Double.toString(location.getLongitude()));

                        DataManneger.getSh().get(DataManneger.getSh().size()-1).setLoc(addresses.get(0).getLocality());
                        database.updateShift(String.valueOf(DataManneger.getWorker().getId()),DataManneger.getSh().get(DataManneger.getSh().size()-1),DataManneger.getAmdminid());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44){
            getCurrentLocation();
        }
        else{
            Toast.makeText(getApplicationContext(),"Permission denied.",Toast.LENGTH_SHORT).show();
        }
    }

}