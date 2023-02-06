package com.example.numad23sp_abhiachalla;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<HolderRecyclerView>{
    private final ArrayList<Card> list;
    private LinkListener listener;

    public AdapterRecyclerView(ArrayList<Card> linkList) {
        this.list = linkList;
    }

    public void setOnItemClickListener(LinkListener listener) {
        this.listener = listener;
    }

    @Override
    public HolderRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_link, parent, false);
        return new HolderRecyclerView(view, listener);
    }

    @Override
    public void onBindViewHolder(HolderRecyclerView holder, int position) {
        Card currentItem = list.get(position);
        holder.url.setText(currentItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}