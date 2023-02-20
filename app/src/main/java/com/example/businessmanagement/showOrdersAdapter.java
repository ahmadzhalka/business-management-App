package com.example.businessmanagement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class showOrdersAdapter extends RecyclerView.Adapter<showOrdersAdapter.ViewHolder> {
    private ArrayList<String> showOrders=new ArrayList();
    private Context context;
    public showOrdersAdapter(ArrayList<String> showOrdersArrayList, Context context) {
        this.showOrders = showOrdersArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fregmentshoworder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pdf_LBL_name.setText(showOrders.get(position));
        holder.pdf_BTN_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.download(context,showOrders.get(position));
                Log.d("message3", "ok2");

            }
        });
        holder.pdf_BTN_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deletePDF(  showOrders.get(position),DataManneger.getAmdminid());
                showOrders.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return showOrders.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        // on below line we are creating variable.
        private TextView pdf_LBL_name;
        private Button pdf_BTN_show;
        private Button pdf_BTN_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // on below line we are initialing our variable.
            pdf_LBL_name = itemView.findViewById(R.id.pdf_LBL_name);
            pdf_BTN_show =itemView.findViewById(R.id.pdf_BTN_show);
            pdf_BTN_remove=itemView.findViewById(R.id.pdf_BTN_remove);
        }
    }
}
