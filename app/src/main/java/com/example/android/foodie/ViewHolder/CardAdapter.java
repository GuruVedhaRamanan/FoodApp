package com.example.android.foodie.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.android.foodie.Model.Order;
import com.example.android.foodie.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


  class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  public TextView text_cart_name,text_price;

  public ElegantNumberButton img_cart_count;

  public void setText_cart_name(TextView text_cart_name) {
    this.text_cart_name = text_cart_name;
  }

  public CardViewHolder(View itemView) {
        super(itemView);

    text_cart_name = (TextView)itemView.findViewById(R.id.itemname);

    text_price = (TextView)itemView.findViewById(R.id.itemcost);

    img_cart_count = (ElegantNumberButton) itemView.findViewById(R.id.itemcount);

    img_cart_count.setOnClickListener(this);
  }

    @Override
    public void onClick(View v) {

    }
}


public class CardAdapter extends RecyclerView.Adapter<CardViewHolder>{

  private List<Order> listdata  = new ArrayList<>();

  private Context context;

  public CardAdapter(List<Order> listdata, Context context) {
    this.listdata = listdata;
    this.context = context;
  }

  @Override
  public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {

      LayoutInflater inflator = LayoutInflater.from(context);

      View itemview = inflator.inflate(R.layout.card_layout,parent,false);

       return new CardViewHolder(itemview);

  }

  @Override
  public void onBindViewHolder(CardViewHolder holder, int position)
  {
  String quantity = holder.img_cart_count.getNumber();

    listdata.get(position).setProductQuantity(quantity);

    Locale locale = new Locale("en","in");

    NumberFormat number = NumberFormat.getCurrencyInstance(locale);

    int price = Integer.parseInt((listdata.get(position).getProductPrice()))*Integer.parseInt(listdata.get(position).getProductQuantity());

    holder.text_price.setText(number.format(price));

    holder.text_cart_name.setText(listdata.get(position).getProductName());



  }

  @Override
  public int getItemCount()
  {
    return listdata.size();
  }
}
