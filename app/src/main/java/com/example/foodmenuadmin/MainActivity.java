package com.example.foodmenuadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    TextView txtslogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Impelement the buttons of SignIn & SignUp from MainActivity.xml
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp= (Button)findViewById(R.id.btnSignUp);

        //Change text font to the font added in  assets/fonts directory
        txtslogan = (TextView)findViewById(R.id.txtslogan);
//        Typeface face = Typeface.createFromAsset(getAssets(), "NABILA.ttf");
//        txtslogan.setTypeface(face);

        //SignIn button to SignIn Activity
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });

        //SignUp button code to SignUn Activity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent signUp = new Intent(MainActivity.this,SignUp.class);
//                startActivity(signUp);

            }
        });
    }
}
