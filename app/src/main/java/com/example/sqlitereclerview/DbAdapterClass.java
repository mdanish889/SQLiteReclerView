package com.example.sqlitereclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DbAdapterClass extends RecyclerView.Adapter<DbAdapterClass.DbViewHolderClass> {
    private ArrayList<DbModelClass> objectDbModelClassArrayList;

    public DbAdapterClass(ArrayList<DbModelClass> objectDbModelClassArrayList) {
        this.objectDbModelClassArrayList = objectDbModelClassArrayList;
    }

    @NonNull
    @Override
    public DbViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View singleItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        DbViewHolderClass objectDbViewHolderClass=new DbViewHolderClass(singleItem);



        return objectDbViewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull DbViewHolderClass holder, int position) {
      holder.nameTV.setText(objectDbModelClassArrayList.get(position).getName());
      holder.addressTV.setText(objectDbModelClassArrayList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return objectDbModelClassArrayList.size();
    }

    class DbViewHolderClass extends RecyclerView.ViewHolder
    {
        TextView nameTV,addressTV;
        public DbViewHolderClass(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.si_nameTV);
            addressTV=itemView.findViewById(R.id.si_addressTV);
        }
    }

}
