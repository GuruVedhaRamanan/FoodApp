package com.example.android.foodie.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.foodie.Interface.ItemClickListener;
import com.example.android.foodie.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView UserName;

    public  TextView PhoneNumber;

    public  TextView Delivery_Address;

    public  TextView Status;

    private ItemClickListener itemClickListener;


    public OrderViewHolder(View itemView) {
        super(itemView);

        UserName = (TextView)itemView.findViewById(R.id.OrderedPersonName);

        PhoneNumber = (TextView)itemView.findViewById(R.id.UserPhoneNumber);

        Delivery_Address = (TextView)itemView.findViewById(R.id.Address);

        Status = (TextView)itemView.findViewById(R.id.OrderStatus);

        itemView.setOnClickListener(this);
    }


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);

        }
    }



