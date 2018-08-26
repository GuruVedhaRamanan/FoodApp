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

public class SignUp extends AppCompatActivity {

    private SharedPrefernesConfig sharedPrefernesConfig;


    TextView shoptitle, reg;


    MaterialEditText UserName;

    MaterialEditText PhoneNumber;

    MaterialEditText password;

    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    Button butn;

    ConnectivityManager connectivityManager;

    NetworkInfo networkInfo;


    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        sharedPrefernesConfig = new SharedPrefernesConfig(getApplicationContext());

        shoptitle = (TextView) findViewById(R.id.ShopTitle);

        reg = (TextView) findViewById(R.id.register);

        UserName = (MaterialEditText) findViewById(R.id.UserNames);

        PhoneNumber = (MaterialEditText) findViewById(R.id.Phonenum);

        password = (MaterialEditText) findViewById(R.id.Password);

        reg = (TextView) findViewById(R.id.register);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/ComicRelief.ttf");

        reg.setTypeface(face);

        Typeface faces = Typeface.createFromAsset(getAssets(), "fonts/GenghisKhan.otf");

        shoptitle.setTypeface(faces);

        view = (View) findViewById(R.id.Layout);

        progressDialog = new ProgressDialog(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

         connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();

        Button butn = (Button) findViewById(R.id.SignInButn);
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((networkInfo != null && networkInfo.isConnected()))
                {

                    signin();

                    progressDialog.dismiss();


                }
                else
                {
                    Snackbar.make(view, "Check your Internet Connection", 10000).show();
                }


            }
        });



    }

    public void signin() {
        final String Name = UserName.getText().toString();

        final String passwords = password.getText().toString();

        final String phonenumber = PhoneNumber.getText().toString();

        progressDialog.setMessage("தகவல் பதிவு செய்யப்படுகிறது");

        progressDialog.show();
        if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(passwords)) {

              databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(phonenumber).exists()) {
                        progressDialog.dismiss();

                        Toast.makeText(getApplicationContext(), R.string.registered_successfully, Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialog.dismiss();

                        User user = new User(Name, phonenumber, passwords);

                        databaseReference.child(phonenumber).setValue(user);

                        Toast.makeText(getApplicationContext(), R.string.registered_successfully, Toast.LENGTH_SHORT).show();

                        sharedPrefernesConfig.writeStatus(true);
                        sharedPrefernesConfig.addname(user.getUserName());
                        sharedPrefernesConfig.addphone(user.getUserPhoneNumber());
                        Intent intent = new Intent(SignUp.this, Home.class);
                        Common.currentuser = user;
                        intent.putExtra("Name", user.getUserName());

                        startActivity(intent);

                        finish();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else {
            progressDialog.dismiss();

            Toast.makeText(this, R.string.filltheform, Toast.LENGTH_SHORT).show();
        }
    }





}




