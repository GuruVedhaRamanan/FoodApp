package com.example.android.foodie;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.foodie.Model.Order;
import com.example.android.foodie.Model.Request;
import com.example.android.foodie.ViewHolder.CardAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import info.hoang8f.widget.FButton;



public class CART extends AppCompatActivity {

    public SharedPrefernesConfig sharedPrefernesConfig;

    int total = 0;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    DatabaseReference ordertable;

    TextView label,totalcost;

    FButton buton;

    Random myRandom = new Random();

    List<Order> cart = new ArrayList<>();

    CardAdapter adapter;

    String Customer_Address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.cartList);

        layoutManager = new LinearLayoutManager(this);

        sharedPrefernesConfig = new SharedPrefernesConfig(this);

        ordertable = FirebaseDatabase.getInstance().getReference("Requests");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            recyclerView.setLayoutManager(layoutManager);
        }

        label = (TextView) findViewById(R.id.label);

        buton = (FButton) findViewById(R.id.OrderButton);

        Typeface FACE = Typeface.createFromAsset(getAssets(), "fonts/ComicRelief.ttf");

        label.setTypeface(FACE);

        totalcost = (TextView) findViewById(R.id.costs);

         loadlist();

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogue();

            }
        });



    }



    private void showAlertDialogue() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("விரைவில் வருகிறோம்");

        alert.setMessage("பொருள் சேர்க்க வேண்டிய இடம் ");

        alert.setIcon(R.drawable.ic_directions_bike_black_24dp);

             final  EditText Address = new EditText(CART.this);

             LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
             LinearLayout.LayoutParams.MATCH_PARENT,
             LinearLayout.LayoutParams.MATCH_PARENT);
              Address.setLayoutParams(lp);


       // this will add the address to the alert dialoguebox
          alert.setView(Address);


          alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Customer_Address =  Address.getText().toString();

                String Req_id  = String.valueOf(myRandom.nextInt());

                if(!TextUtils.isEmpty(Customer_Address))
                {


                    Request request = new Request(
                            Req_id,
                            sharedPrefernesConfig.readphone(),
                            Customer_Address,
                            totalcost.getText().toString(),
                            cart);

                    ordertable.child(String.valueOf(System.currentTimeMillis())).setValue(request);                 //clear the cart
                    new Database(getBaseContext()).clean();

                    Toast.makeText(CART.this,R.string.thank_you, Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(CART.this,R.string.destination, Toast.LENGTH_SHORT).show();
                    finish();
                }



                }
          });

    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            dialog.dismiss();
            cart.clear();
            new Database(getBaseContext()).clean();
            adapter.notifyDataSetChanged();
            total = 0;


        }


    });
        alert.show();
    }

    private void loadlist() {
     // getting the


        cart = new Database(this).getCarts();

     // settting the list of items and the context to card adapter

        adapter = new CardAdapter(cart,this);

       //  adapter.onBindViewHolder(CardViewHolder holder , int position)
        recyclerView.setAdapter(adapter);


        for(Order order : cart)
        {
            total+=Integer.parseInt(order.getProductPrice())*Integer.parseInt(order.getProductQuantity());

        }

        Locale locale = new Locale("en","in");

        NumberFormat format =  NumberFormat.getCurrencyInstance(locale);

        totalcost.setText(format.format(total));
    }


}
