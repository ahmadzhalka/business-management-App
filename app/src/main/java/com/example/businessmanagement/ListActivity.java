package com.example.businessmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListActivity extends Fragment {

    private RecyclerView shifts_LST_shifts;
    Worker worker=DataManneger.getWorker();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list, container, false);
        shifts_LST_shifts = view.findViewById(R.id.shifts_LST_shifts);
        ArrayList<Shifts> players = DataManneger.getShiftsBYM();
        for (int i = 0; i <players.size() ; i++) {
            Log.w("mee", ""+i);

        }
        Adapter_Shifts adapter_player = new Adapter_Shifts(getContext(), players);
        shifts_LST_shifts.setLayoutManager(new LinearLayoutManager(getContext()));
        shifts_LST_shifts.setAdapter(adapter_player);
        return view;
    }

}