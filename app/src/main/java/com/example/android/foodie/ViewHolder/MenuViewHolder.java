package com.example.android.foodie.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.foodie.Interface.ItemClickListener;
import com.example.android.foodie.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public  TextView textView;

    public  TextView price;

    public ImageView image;

    public Button cart;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {

        super(itemView);

        price= (TextView)itemView.findViewById(R.id.itemPrice);

        textView = (TextView)itemView.findViewById(R.id.itemname);

        image = (ImageView) itemView.findViewById(R.id.ImageItem);

        cart = (Button)itemView.findViewById(R.id.cart);

        itemView.setOnClickListener(this);

        cart.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
