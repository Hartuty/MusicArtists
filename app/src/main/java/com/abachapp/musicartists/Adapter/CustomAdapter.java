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

import com.abachapp.musicartists.Activity.PlaySample;
import com.abachapp.musicartists.Model.Deezers.Datum;
import com.abachapp.musicartists.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

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
        String Album =datalist.get(position).getAlbum().getTitle();
        holder.textView.setText(Album);
        String Artist =datalist.get(position).getArtist().getName();
        holder.textView1.setText(Artist);
        String image=datalist.get(position).getAlbum().getCoverBig();
        Picasso.get().load(image).into(holder.imageView);
        String url=datalist.get(position).getPreview();
        String url1=datalist.get(position).getArtist().getPictureSmall();

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context,PlaySample.class);
            intent.putExtra("Album",Album);
            intent.putExtra("Artist",Artist);
            intent.putExtra("Image",image);
            intent.putExtra("Sample",url);
            intent.putExtra("Sample1",url1);
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
            textView=mView.findViewById(R.id.albumname);
            textView1=mView.findViewById(R.id.artistname);
            imageView=mView.findViewById(R.id.albumimage);
            materialCardView=mView.findViewById(R.id.material_card);
        }
    }
}
