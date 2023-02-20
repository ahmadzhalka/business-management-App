package com.example.businessmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class Adapter_Shifts  extends RecyclerView.Adapter<Adapter_Shifts.shiftsViewHolder>{

    private Context context;
    private ArrayList<Shifts> shifts;
    private SharedViewModel viewModel;


    public Adapter_Shifts(Context context, ArrayList<Shifts> shifts) {
        this.context = context;
        this.shifts = shifts;
    }


    @Override
    public Adapter_Shifts.shiftsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shiftlist, parent, false);
        shiftsViewHolder myShitsViewHolder = new shiftsViewHolder(view);
        return myShitsViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Shifts.shiftsViewHolder holder, int position) {
        Shifts shift = shifts.get(position);
        holder.date.setText((CharSequence) shift.getDate());
        holder.start1.setText(""+ shift.getStart());
        holder.end.setText(""+ shift.getEnd());
        holder.loc.setText(""+ shift.getLoc());
        holder.timer.setText(""+ shift.getShiftTime());
    }

    @Override
    public int getItemCount() {
        return shifts == null ? 0 : shifts.size();
    }
    class shiftsViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView date;
        private MaterialTextView start1;
        private MaterialTextView end;
        private MaterialTextView loc;
        private TextView timer;

        public shiftsViewHolder(View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            start1=itemView.findViewById(R.id.start1);
            end=itemView.findViewById(R.id.end);
            loc=itemView.findViewById(R.id.loc);
            timer=itemView.findViewById(R.id.timer);

        }

    }

}

