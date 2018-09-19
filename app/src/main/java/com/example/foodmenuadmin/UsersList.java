package com.example.foodmenuadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.foodmenuadmin.Model.User;
import com.example.foodmenuadmin.ViewHolder.UserListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersList extends AppCompatActivity {

    private static final String TAG = "UsersList";

    //widgets
    private ListView mListView;

    //vars
    private List<User> mUserList;
    private UserListAdapter mAdapter;

    private Context mContext = UsersList.this;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        mListView = (ListView) findViewById(R.id.listView);
        mUserList = new ArrayList<>();

        Log.d(TAG, "onCreate: started.");

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'CategoryList'");
                Intent i = new Intent(mContext, CategoryList.class);
                startActivity(i);
                finish();
            }
        });

        getUsers();

    }

    private void getUsers(){
        Log.d(TAG, "getUsers: searching for users");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("User");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: found user:" + singleSnapshot.getValue());

                    mUserList.add(singleSnapshot.getValue(User.class));
                    //display following users
                    updateUsersFollowersList();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void updateUsersFollowersList(){
        Log.d(TAG, "updateUsersList: updating users list");

        mAdapter = new UserListAdapter(mContext, R.layout.layout_user_listitem, mUserList);
        mListView.setAdapter(mAdapter);

        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: selected user: " + mUserList.get(position).toString());
                //navigate to FollowersList activity
                Intent intent =  new Intent(mContext, ProfileActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.followers_list));
                intent.putExtra(getString(R.string.intent_user), mUserList.get(position));
                startActivity(intent);
            }
        });*/
    }


}
