package com.example.foodmenuadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    Button btn_admin;
    TextView txtslogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_admin = (Button)findViewById(R.id.btn_admin);

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
                final EditText txtps = (EditText) dialog_layout.findViewById(R.id.txtuserpass);
                builder.setView(dialog_layout)

                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (txtps.equals("12345")) {
                                    Toast.makeText(AdminActivity.this, "Access Successfully...", Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                }else {
                                    Intent adminIntent = new Intent(AdminActivity.this, CategoryList.class);
                                    startActivity(adminIntent);
                                }

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
