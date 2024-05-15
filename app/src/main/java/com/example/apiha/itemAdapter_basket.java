package com.example.apiha;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class itemAdapter_basket extends RecyclerView.Adapter<itemAdapter_basket.ViewHolder>{

    private Context context;
    private ArrayList<Service> list;
    private ArrayList<String> list_b;


    SharedPreferences settings;
    SharedPreferences.Editor editor;

    ArrayList<String> a = new ArrayList<String>();

    public itemAdapter_basket(Context context, ArrayList<Service> list, ArrayList<String> list_b) {
        this.context = context;
        this.list = list;
        this.list_b = list_b;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_basket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter_basket.ViewHolder holder, int position) {
        ArrayList<Service> sr = list;
        settings = context.getSharedPreferences("settings", MODE_PRIVATE);

        for (String i : list_b){
            //Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
            if (Objects.equals(i, Integer.toString(list.get(position).id_service))){
                //Toast.makeText(context, "as", Toast.LENGTH_SHORT).show();
                holder.name.setText(sr.get(position).name.toString());
                holder.coast.setText(sr.get(position).coast.toString());
            }
        }

//        holder.name.setText(sr.get(position).name.toString());
//        holder.coast.setText(sr.get(position).coast.toString());

        //Toast.makeText(context, "ajcno", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return list_b.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView coast;

        Button btn;
        private final Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            name = itemView.findViewById(R.id.name);
            coast = itemView.findViewById(R.id.coast);
            btn = itemView.findViewById(R.id.button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    list_b.remove(position);

                    settings = context.getSharedPreferences("settings", MODE_PRIVATE);
                    editor = settings.edit();
                    editor.putString("basket", String.join(" ", list_b));
                    editor.apply();
                }
            });




        }
    }
}
