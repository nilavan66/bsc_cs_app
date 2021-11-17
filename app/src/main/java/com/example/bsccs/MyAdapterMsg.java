package com.example.bsccs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapterMsg extends RecyclerView.Adapter<MyAdapterMsg.MyViewHolder> {

    ArrayList<ModelMsg> mList;
    Context context;

    public MyAdapterMsg(Context context, ArrayList<ModelMsg> mList) {

        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.msg, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelMsg model = mList.get(position);
        holder.msg.setText(model.getMsg());
        holder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView msg, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            msg = itemView.findViewById(R.id.msg);
            date = itemView.findViewById(R.id.date);
        }
    }
}
