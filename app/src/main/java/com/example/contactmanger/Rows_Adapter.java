package com.example.contactmanger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Rows_Adapter extends RecyclerView.Adapter<Rows_Adapter.Rows_Holder> {
    private final Context context;
    Activity activity;
    private final ArrayList id , Name , number , symbol;

    Rows_Adapter(Activity activity ,Context context,ArrayList id, ArrayList name, ArrayList number, ArrayList symbol) {
        this.context = context;
        Name = name;
        this.number = number;
        this.symbol = symbol;
        this.activity = activity;
        this.id = id;
    }

    @NonNull
    @Override
    public Rows_Adapter.Rows_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rows_manager, parent , false);
        return new Rows_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rows_Adapter.Rows_Holder holder, int position) {
        holder.Name.setText(String.valueOf(Name.get(position)));
        holder.number.setText(String.valueOf(number.get(position)));
        holder.symbol.setText(String.valueOf(symbol.get(position)));
        holder.layout.setOnClickListener(View->{
            Intent intent = new Intent(context , contact_info.class);
            intent.putExtra("id", String.valueOf(id.get(position)));
            intent.putExtra("name", String.valueOf(Name.get(position)));
            intent.putExtra("number", String.valueOf(number.get(position)));
            intent.putExtra("symbol", String.valueOf(symbol.get(position)));
            activity.startActivityForResult(intent , 1);
        });
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }
    public static class Rows_Holder extends RecyclerView.ViewHolder{
        TextView Name , number , symbol;
        ConstraintLayout layout;
        public Rows_Holder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.rows_manager);
            symbol = itemView.findViewById(R.id.textView);
            Name = itemView.findViewById(R.id.textView2);
            number = itemView.findViewById(R.id.textView3);

        }
    }
}
