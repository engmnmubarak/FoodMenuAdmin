package com.example.foodmenuadmin.ViewHolder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.foodmenuadmin.Model.User;
import com.example.foodmenuadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserListAdapter extends ArrayAdapter<User> {

    private static final String TAG = "UserListAdapter";


    private LayoutInflater mInflater;
    private List<User> mUsers = null;
    private int layoutResource;
    private Context mContext;


    public UserListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        mContext = context;
        // gets you a LayoutInflater directly from the system service
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mUsers = objects;
    }

    private static class ViewHolder{
        TextView name;
        TextView phone;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        final ViewHolder holder;

        if(convertView == null){

            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.username);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);


            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("User");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: found user: " +
                            singleSnapshot.getValue(User.class).toString());

                    User users = new User();
                    Map<String, Object> objectMap = (HashMap<String, Object>) singleSnapshot.getValue();

                    users.setName(singleSnapshot.getValue(User.class).getName());
                    holder.name.setText(users.getName());

                    users.setPhone(singleSnapshot.getValue(User.class).getPhone());
                    holder.phone.setText(users.getPhone());

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return convertView;
    }
}
