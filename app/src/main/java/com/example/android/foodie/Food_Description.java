package com.example.android.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.foodie.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Food_Description extends AppCompatActivity
{
    TextView food_name,food_price,food_description;

    ImageView food_image;


    CollapsingToolbarLayout collapsingToolbarLayout;

    FloatingActionButton fab;

    Food selectedFood;

    String FoodiD="";



    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__description);

        databaseReference = FirebaseDatabase.getInstance().getReference("Food");

               food_name = (TextView)findViewById(R.id.Food_Name);

        food_price = (TextView)findViewById(R.id.FoodPrice);

        food_description = (TextView)findViewById(R.id.FoodDescription);

        food_image = (ImageView)findViewById(R.id.img_food);

        fab = (FloatingActionButton)findViewById(R.id.cardButton);





        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);




        if(getIntent() != null)
        {
            FoodiD = getIntent().getStringExtra("MenuId");

            if(!FoodiD.isEmpty())
            {
                databaseReference.child(FoodiD).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                     selectedFood  = dataSnapshot.getValue(Food.class);

                        Picasso.with(getBaseContext()).load(selectedFood.getImage()).into(food_image);

                        collapsingToolbarLayout.setTitle(selectedFood.getName());

                        food_description.setText(selectedFood.getContents());

                        food_price.setText(selectedFood.getPrice());

                        food_name.setText(selectedFood.getName());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                    }
                });
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Food_Description.this,CART.class);
                startActivity(intent);
              /*  int quantity =1;
                new Database(Food_Description.this).addTOCart(new Order(
                        FoodiD,
                        selectedFood.getName(),
                        selectedFood.getPrice(),
                      String.valueOf(quantity),
                        selectedFood.getDiscount()));
                Toast.makeText(Food_Description.this,"கூடையில் சேர்க்கப்பட்டது",Toast.LENGTH_SHORT).show();*/



            }
        });

    }
}
