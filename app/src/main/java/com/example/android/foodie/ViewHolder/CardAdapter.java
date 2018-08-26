package com.example.android.foodie.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.android.foodie.Database;
import com.example.android.foodie.Model.Order;
import com.example.android.foodie.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


  public TextView text_cart_name,text_price;

  public ElegantNumberButton increasebutn;



  CardViewHolder(View itemView) {
        super(itemView);

    text_cart_name = itemView.findViewById(R.id.itemname);

    text_price = itemView.findViewById(R.id.itemcost);

      increasebutn =  itemView.findViewById(R.id.quantity);

   // Quantity = (TextView)itemView.findViewById(R.id.Quantitytext);

    increasebutn.setOnClickListener(this);

  }



    @Override
    public void onClick(View v)
    {

    }


}







    public class CardAdapter extends RecyclerView.Adapter<CardViewHolder>{


    private List<Order> listdata;

     private Context context;






      public CardAdapter(List<Order> listdata, Context context) {
      this.listdata = listdata;
      this.context = context;
  }

  @Override
  public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
      //final int numbers = 1;

      LayoutInflater inflator = LayoutInflater.from(context);

      View itemview = inflator.inflate(R.layout.card_layout,parent,false);

       return new CardViewHolder(itemview);

  }

  @Override
  public void onBindViewHolder(final CardViewHolder holder, @SuppressLint("RecyclerView") final int position)
  {


      holder.increasebutn.setNumber(listdata.get(position).getProductQuantity());


      holder.increasebutn.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
          @Override
          public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
              Order order =    listdata.get(position);

              order.setProductQuantity(String.valueOf(newValue));
              Locale locale = new Locale("en","in");

              final NumberFormat number = NumberFormat.getCurrencyInstance(locale);

              number.setMaximumFractionDigits(0);

              holder.text_price.setText((number.format(Integer.parseInt((listdata.get(position).getProductPrice()))*Integer.parseInt(listdata.get(position).getProductQuantity()))));

              new Database(context).updateQuantity(order);
          }
      });

      Locale locale = new Locale("en","in");

      final NumberFormat number = NumberFormat.getCurrencyInstance(locale);

      number.setMaximumFractionDigits(0);

        final int price =  Integer.parseInt(listdata.get(position).getProductQuantity())*Integer.parseInt(listdata.get(position).getProductPrice());



          holder.text_price.setText(number.format(price));

         holder.text_cart_name.setText(listdata.get(position).getProductName());


   /* holder.increasebutn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

          int numbers =Integer.parseInt( listdata.get(position).getProductQuantity());

          if(numbers < 20) {
              numbers++;



           //   Toast.makeText(context, listdata.get(position).getProductQuantity(), Toast.LENGTH_SHORT).show();


              int price = calculateprice(position, Integer.parseInt(listdata.get(position).getProductQuantity()));

              holder.Quantity.setText(listdata.get(position).getProductQuantity());

              holder.text_price.setText(number.format(price));

          }










      //  Toast.makeText(context,"Increasing the quantity",Toast.LENGTH_SHORT).show();





      }
    });*/
    /*  holder.decreasebutn.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {

              int numbers =Integer.parseInt( listdata.get(position).getProductQuantity());

              if(numbers > 0) {
                  numbers--;
                  Order order =    listdata.get(position);

                  order.setProductQuantity(String.valueOf(numbers));

                  new Database(context).updateQuantity(order);

                //  Toast.makeText(context, listdata.get(position).getProductQuantity(), Toast.LENGTH_SHORT).show();


                  int price = calculateprice(position, Integer.parseInt(listdata.get(position).getProductQuantity()));


                  holder.text_price.setText(number.format(price));

                  holder.Quantity.setText(listdata.get(position).getProductQuantity());
              }





              {
             //     Toast.makeText(context, "The item is cancelled", Toast.LENGTH_SHORT).show();
              }




          }
      });*/

  }





    @Override
  public int getItemCount()
  {
    return listdata.size();
  }
}
