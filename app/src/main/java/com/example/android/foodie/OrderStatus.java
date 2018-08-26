package com.example.android.foodie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.android.foodie.Interface.ItemClickListener;
import com.example.android.foodie.Common.Common;
import com.example.android.foodie.Model.Request;
import com.example.android.foodie.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {

    private SharedPrefernesConfig sharedPrefernesConfig;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    DatabaseReference orders;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("OrderList");

        setSupportActionBar(toolbar);

        sharedPrefernesConfig = new SharedPrefernesConfig(getApplicationContext());

        recyclerView = (RecyclerView)findViewById(R.id.Orders);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        orders = FirebaseDatabase.getInstance().getReference("Requests");

        loadorder(sharedPrefernesConfig.readphone());

    }

    private void loadorder(String userPhoneNumber) {

        // constructor for inflating the layout using firebaseRecycler Adapter

        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                orders.orderByChild("phone").equalTo(userPhoneNumber)) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {

                viewHolder.UserName.setText("பதிவு எண்:\t \t" +adapter.getRef(position).getKey());

                viewHolder.PhoneNumber.setText("தொலை பேசி எண்:\t \t" + model.getPhone());

                viewHolder.Delivery_Address.setText("இடம்:\t \t" + model.getAddress());

               viewHolder.Status.setText(Common.ConvertStatus(model.getStatus()));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Toast.makeText(getApplicationContext(),"விரைவில் வருகிறோம்",Toast.LENGTH_SHORT).show();

                        // the intent will carry the id of each food and display their own images from the firebase


                    }
                });
            }
        };


        recyclerView.setAdapter(adapter);
    }


    }

