package com.example.bsccs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterFolder extends RecyclerView.Adapter<MyAdapterFolder.MyViewHolder> {

    ArrayList<ModelFolder> mList;
    Context context;

    public MyAdapterFolder(Context context, ArrayList<ModelFolder> mList){

        this.mList=mList;
        this.context=context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemfolder, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ModelFolder modelFolder = mList.get(position);
        holder.name.setText(modelFolder.getName());
       // holder.number.setText(model.getFileurl());

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.link.getContext(),ViewPfd.class);
                intent.putExtra("name",modelFolder.getName());
                intent.putExtra("fileurl",modelFolder.getFileurl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.link.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;// number;
        LinearLayout link;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            //number = itemView.findViewById(R.id.number);
            link = itemView.findViewById(R.id.link);

        }
    }
}
