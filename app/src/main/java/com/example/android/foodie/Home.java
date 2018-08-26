package com.example.android.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.foodie.Interface.ItemClickListener;
import com.example.android.foodie.Model.Food;
import com.example.android.foodie.Model.Order;
import com.example.android.foodie.Service.Notification;
import com.example.android.foodie.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPrefernesConfig sharedPrefernesConfig;

     DatabaseReference items;

     TextView user;



    FirebaseRecyclerAdapter<Food,MenuViewHolder> adapter;

    RecyclerView recyclerView;


      RecyclerView.LayoutManager layoutManager;
    List<String> selectedmenuId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPrefernesConfig = new SharedPrefernesConfig(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        toolbar.setTitle(R.string.items);

        setSupportActionBar(toolbar);

        items = FirebaseDatabase.getInstance().getReference("Food");

       /* FloatingActionButton faba = (FloatingActionButton) findViewById(fab);
        faba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, CART.class);
                startActivity(i);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);

        user = (TextView) headerview.findViewById(R.id.Users);

        user.setText(sharedPrefernesConfig.readname());

     //   user.setText(Common.currentuser.getUserName());

     //   materialSearchView = (MaterialSearchView)findViewById(R.id.searchbar);

        recyclerView = (RecyclerView) findViewById(R.id.Menus);

        layoutManager = new GridLayoutManager(this,2);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        loadlist();


        Intent intent = new Intent(Home.this, Notification.class);
        startService(intent);

    }

    private void loadlist() {

        // constructor for inflating the layout using firebaseRecycler Adapter

        adapter = new FirebaseRecyclerAdapter<Food, MenuViewHolder>(Food.class,
               R.layout.menu_item,
               MenuViewHolder.class,
               items) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, final Food model, final int position) {

                viewHolder.textView.setText(model.getName());

                Locale locale = new Locale("en","in");

                NumberFormat format =  NumberFormat.getCurrencyInstance(locale);

                int price = Integer.parseInt(model.getPrice());

                viewHolder.price.setText(format.format(price));

                viewHolder.cart.setOnClickListener(new View.OnClickListener()
                {



                    @Override
                    public void onClick(View v) {



                        if(!(selectedmenuId.contains(model.getMenuId())))
                        {
                            selectedmenuId.add(model.getMenuId());
                            int quantity = 1;
                            Order order =  new Order( model.getMenuId(),
                                    model.getName(),
                                    model.getPrice(),
                                    String.valueOf(quantity),
                                    model.getDiscount());

//                           This generates a row in the sqlite database for showing the items....

                            new Database(Home.this).addTOCart(order);

                            Snackbar.make(v, R.string.added_to_the_cart, Snackbar.LENGTH_SHORT).show();



                                                  }
                        else
                        {
                            Toast.makeText(getApplicationContext(),selectedmenuId.get(position),Toast.LENGTH_SHORT).show();
                            Snackbar.make(v,"Already added to the cart", Snackbar.LENGTH_SHORT).show();
                        }


                    }
                });

                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.image);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent intent = new Intent(Home.this,Food_Description.class);

                        intent.putExtra("MenuId",adapter.getRef(position).getKey());
                    Toast.makeText(getApplicationContext(),model.getMenuId(),Toast.LENGTH_SHORT).show();
                 // the intent will carry the id of each food and display their own images from the firebase

                        startActivity(intent);

                    }
                });
            }
        };
           recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_Menu:

                break;
            case R.id.nav_Cart:

                Intent cart = new Intent(Home.this, CART.class);

                startActivity(cart);


                break;
            case R.id.nav_Orders:

                Intent orders = new Intent(Home.this,OrderStatus.class);

                startActivity(orders);

                break;
            case R.id.nav_logout:

                sharedPrefernesConfig.writeStatus(false);

                Intent in = new Intent(Home.this,SignIn.class);

                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(in);

                finish();
                break;
            case R.id.nav_info:

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
