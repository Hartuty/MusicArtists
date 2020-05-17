package com.abachapp.musicartists.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abachapp.musicartists.Model.Datum;
import com.abachapp.musicartists.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Datum> datalist;
    private Context context;

    public CustomAdapter(List<Datum> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.one_item_view,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.textView.setText(datalist.get(position).getTitle());
        holder.textView1.setText(datalist.get(position).getCompetition().getName());
        Picasso.get().load(datalist.get(position).getThumbnail()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, play_item.class);
            intent.putExtra("uri",datalist.get(position).getEmbed());
            intent.putExtra("title",datalist.get(position).getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView textView;
        TextView textView1;
        ImageView imageView;
        MaterialCardView materialCardView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView=mView.findViewById(R.id.matchname);
            textView1=mView.findViewById(R.id.competition);
            imageView=mView.findViewById(R.id.imageView);
            materialCardView=mView.findViewById(R.id.materialcardview);
        }
    }
}
