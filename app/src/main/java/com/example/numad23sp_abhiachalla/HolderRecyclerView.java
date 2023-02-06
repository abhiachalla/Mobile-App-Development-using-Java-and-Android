package com.example.numad23sp_abhiachalla;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class HolderRecyclerView extends RecyclerView.ViewHolder {
    public TextView url;


    public HolderRecyclerView(View itemView, final LinkListener listener) {
        super(itemView);
        url = itemView.findViewById(R.id.url);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}