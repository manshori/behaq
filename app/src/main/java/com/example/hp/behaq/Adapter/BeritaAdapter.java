package com.example.hp.behaq.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.behaq.Dialog1Activity;
import com.example.hp.behaq.Modal.Berita;
import com.example.hp.behaq.R;
import com.example.hp.behaq.SentimentDialogActivity;

import java.util.ArrayList;

/**
 * Created by hp on 10/10/2017.
 */

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.ViewHolder> {
    Context context;
    private ArrayList<Berita> beritasList;

    public BeritaAdapter(Context context, ArrayList<Berita> beritasList) {
        this.context = context;
        this.beritasList = beritasList;
    }

    @Override
    public BeritaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.berita_list_row,null));
    }

    @Override
    public void onBindViewHolder(BeritaAdapter.ViewHolder holder, int position) {
         final Berita b = beritasList.get(position);
            //holder.id.setText(""+b.getId());
            //holder.count_varified.setText(""+b.getCount_verified());
            //holder.count_hoax.setText(""+b.getCount_hoax());
            //holder.sentiment.setText(""+b.getSentiment());
            //holder.label.setText(""+b.getLabel());
            //holder.query.setText(""+b.getQuery());
            holder.name.setText(""+b.getName());
            holder.url.setText(""+b.getUrl());
            holder.snippet.setText(""+b.getSnippet());
            //holder.datetime.setText(""+b.getDatetime());

        holder.rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "coba", Toast.LENGTH_SHORT).show();
            }
        });

        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Dialog1Activity.class);
                context.startActivity(i);
                Toast.makeText(context, "Login Dulu", Toast.LENGTH_SHORT).show();
            }
        });

        holder.show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SentimentDialogActivity.class);
                context.startActivity(i);
                Log.d("dialog2", "dialog 2: tidak bisa ");
                Toast.makeText(context, "Mencoba dialog 2", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int getItemCount() {
        return beritasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout rlt;
        TextView id, count_varified, count_hoax, sentiment, label, query, name, url, snippet;
        ImageView show, show2;

        public ViewHolder(View itemView) {
            super(itemView);
            rlt = (RelativeLayout)itemView.findViewById(R.id.rlt_beritaList);
            name = (TextView)itemView.findViewById(R.id.judul);
            url = (TextView)itemView.findViewById(R.id.url);
            snippet = (TextView)itemView.findViewById(R.id.deskripsi);

            show = (ImageView) itemView.findViewById(R.id.imageView3);
            show2 = (ImageView)itemView.findViewById(R.id.img_sentimen_beritaListRow);
        }
    }
}
