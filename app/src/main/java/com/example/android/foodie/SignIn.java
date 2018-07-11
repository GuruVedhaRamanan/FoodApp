package com.example.android.foodie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.foodie.Common.Common;
import com.example.android.foodie.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

  TextView shoptitle,reg;
    MaterialEditText PhoneNumber;
    MaterialEditText password;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    Button butn;
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        PhoneNumber = (MaterialEditText) findViewById(R.id.Phonenum);

        password = (MaterialEditText) findViewById(R.id.Password);

        shoptitle = (TextView)findViewById(R.id.ShopTitle);




        reg = (TextView)findViewById(R.id.register);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/ComicRelief.ttf");

        reg.setTypeface(face);

        Typeface faces  = Typeface.createFromAsset(getAssets(),"fonts/GenghisKhan.otf");

        shoptitle.setTypeface(faces);


          view    = (View) findViewById(R.id.Layout);

        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        connectivityManager   = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        networkInfo  = connectivityManager.getActiveNetworkInfo();
        Button butn = (Button)findViewById(R.id.SignInButn);
        butn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (networkInfo != null &&( networkInfo.isConnected()))
                {
                    signin();
                }

                else
                {
                    Snackbar.make(view, "Check your Internet Connection", 10000).show();
                }


            }
        });




    }

    public void signin()
    {
        final   String email = PhoneNumber.getText().toString();

        final String passwords =    password.getText().toString();

        progressDialog.setMessage("சில நோடிகள்");

        progressDialog.show();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwords)) {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(email).exists()) {

                        /// From database getting the  information of the User.

                        User user = dataSnapshot.child(email).getValue(User.class);

                        if (user.getPassword().equals(passwords) )
                            {
                                progressDialog.dismiss();
                             //   Toast.makeText(getApplicationContext(), "Logged  in  Successful", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(SignIn.this,Home.class);
                                Common.currentuser = user;

                               in.putExtra("Name",user.getUserName());

                                startActivity(in);

                                finish();

                            }
                            else if (!(user.getPassword().equals(password.getText().toString()))) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "கடவு சொல்லை சரி பார்க்கவும்", Toast.LENGTH_SHORT).show();
                            }


                    }


                   }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(this, R.string.filltheform, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onBackPressed() {


        progressDialog.dismiss();

        finish();
    }
}
