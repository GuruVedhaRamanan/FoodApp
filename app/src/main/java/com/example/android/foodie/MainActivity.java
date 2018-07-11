package com.example.android.foodie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button Signup, SignIn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Signup = (Button)findViewById(R.id.SignUpButn);

        SignIn = (Button)findViewById(R.id.SignInButn);

        text = (TextView)findViewById(R.id.textslogan);

        Typeface FACE = Typeface.createFromAsset(getAssets(),"fonts/ComicRelief.ttf");

        text.setTypeface(FACE);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignIn.class);

                startActivity(intent);
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);

                startActivity(intent);
            }
        });
    }
}
