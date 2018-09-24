package com.example.foodmenuadmin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.foodmenuadmin.Model.User;
import com.example.foodmenuadmin.ViewHolder.UserListViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UsersList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;
    FirebaseRecyclerAdapter<User,UserListViewHolder> adapter;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    User newusers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        recycler_menu = (RecyclerView)findViewById(R.id.recycler_user);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        loadUsers();
    }




    private void loadUsers() {
            adapter =new FirebaseRecyclerAdapter<User, UserListViewHolder>(
                User.class,
                R.layout.layout_user_listitem,
                    UserListViewHolder.class,
                    users
        ) {
                @Override
                protected void populateViewHolder(UserListViewHolder viewHolder, User model, int position) {
                    viewHolder.txtusername.setText(model.getName());
                    viewHolder.txtuserphone.setText(model.getPhone());

                    /*viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Intent foodList = new Intent(UsersList.this,FoodList.class);
                            foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                            startActivity(foodList);
                        }
                    });*/
                }
                };
        recycler_menu.setAdapter(adapter);
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

        return super.onOptionsItemSelected(item);
    }


}
