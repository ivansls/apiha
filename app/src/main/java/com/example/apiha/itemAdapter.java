package com.example.apiha;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Service> list;


    SharedPreferences settings;
    SharedPreferences.Editor editor;

    ArrayList<String> a = new ArrayList<String>();

    public itemAdapter(Context context, ArrayList<Service> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter.ViewHolder holder, int position) {
        ArrayList<Service> sr = list;
        holder.name.setText(sr.get(position).name.toString());
        holder.coast.setText(sr.get(position).coast.toString());
        //Toast.makeText(context, "ajcno", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView coast;
        private final Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            name = itemView.findViewById(R.id.name);
            coast = itemView.findViewById(R.id.coast);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //ArrayList<Service> sr = list;
                    a.add(Integer.toString(list.get(position).id_service));
                    //Toast.makeText(context, String.join(" ", a), Toast.LENGTH_SHORT).show();

                    settings = context.getSharedPreferences("settings", MODE_PRIVATE);
                    editor = settings.edit();
                    editor.putString("basket", String.join(" ", a));
                    editor.apply();

                    //Toast.makeText(context, String.join(" ", a), Toast.LENGTH_SHORT).show();


                }
            });

        }
    }
}
