package com.example.businessmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class orderRVAdapter extends RecyclerView.Adapter<orderRVAdapter.ViewHolder> {
    private HashMap<String,String> orderRVModalArrayList;


    public orderRVAdapter(HashMap<String, String> orderRVModalArrayList, Context context) {
        this.orderRVModalArrayList = orderRVModalArrayList;
    }

    @NonNull
    @Override
    public orderRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order, parent, false);

        // at last we are returning our view holder
        // class with our item View File.
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderRVAdapter.ViewHolder holder, int position) {
        // on below line we are setting text to our text view.
        Object[] x=(orderRVModalArrayList.keySet().toArray());
        holder.key.setText((CharSequence) x[position]);
        holder.value.setText(orderRVModalArrayList.get(x[position]));
        holder.idBtnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderRVModalArrayList.remove(x[position]);
               notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderRVModalArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // on below line we are creating variable.
        private TextView key;
        private TextView value;
        private Button idBtnremove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // on below line we are initialing our variable.
            key = itemView.findViewById(R.id.idTVLngName);
            value =itemView.findViewById(R.id.idTVLngName2);
            idBtnremove=itemView.findViewById(R.id.idBtnremove);
        }
    }
}