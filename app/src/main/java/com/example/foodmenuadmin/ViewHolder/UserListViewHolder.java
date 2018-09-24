package com.example.foodmenuadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.foodmenuadmin.Interface.ItemClickListener;
import com.example.foodmenuadmin.R;

import static com.example.foodmenuadmin.Common.Common.DELETE;
import static com.example.foodmenuadmin.Common.Common.UPDATE;

public class UserListViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener

{

    public TextView txtusername;
    public TextView txtuserphone;

    private ItemClickListener itemClickListener;

    public UserListViewHolder(View itemView) {
        super(itemView);

        txtusername = (TextView)itemView.findViewById(R.id.username);
        txtuserphone = (TextView)itemView.findViewById(R.id.phone);
        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the action");

        contextMenu.add(0,0,getAdapterPosition(),UPDATE);
        contextMenu.add(0,0,getAdapterPosition(),DELETE);
    }




}
