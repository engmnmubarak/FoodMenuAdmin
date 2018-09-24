package com.example.foodmenuadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    Button btn_admin;
    TextView txtslogan;
    private FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_admin = (Button)findViewById(R.id.btn_admin);

        Auth = FirebaseAuth.getInstance();

        //Change text font to the font added in  assets/fonts directory
        txtslogan = (TextView)findViewById(R.id.txtslogan);
//        Typeface face = Typeface.createFromAsset(getAssets(), "NABILA.ttf");
//        txtslogan.setTypeface(face);

        //Admin button
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                View dialog_layout = inflater.inflate(R.layout.activity_admin_dialog,null);
                final EditText txtemail = (EditText) dialog_layout.findViewById(R.id.txtuseremail);
                final EditText txtpss = (EditText) dialog_layout.findViewById(R.id.txtuserpass);

                builder.setView(dialog_layout)

                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                final String email = txtemail.getText().toString();
                                final String password = txtpss.getText().toString();

                                if (TextUtils.isEmpty(email)) {
                                    Toast.makeText(getApplicationContext(), "Please Enter Your Email!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (TextUtils.isEmpty(password)) {
                                    Toast.makeText(getApplicationContext(), "Please Enter Your Password!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                //authenticate user
                                Auth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(AdminActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                // If sign in fails, display a message to the user. If sign in succeeds
                                                // the auth state listener will be notified and logic to handle the
                                                // signed in user can be handled in the listener.
                                                if (!task.isSuccessful()) {
                                                    // there was an error
                                                    if (password.length() == 6) {
                                                        txtpss.setError(getString(R.string.minimum_password));
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "Access Field .. ", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Access Successfully .. ", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(AdminActivity.this,CategoryList.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }
}
