package com.example.foodmenuadmin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.foodmenuadmin.Interface.ItemClickListener;
import com.example.foodmenuadmin.R;

import static com.example.foodmenuadmin.Common.Common.DELETE;
import static com.example.foodmenuadmin.Common.Common.UPDATE;


public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;
    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {

        super(itemView);
        txtOrderAddress = itemView.findViewById(R.id.order_address);
        txtOrderPhone = itemView.findViewById(R.id.order_phone);
        txtOrderStatus = itemView.findViewById(R.id.order_status);
        txtOrderId = itemView.findViewById(R.id.order_id);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
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
        contextMenu.setHeaderTitle("Select Action");

        contextMenu.add(0,0,getAdapterPosition(),UPDATE);
        contextMenu.add(0,1,getAdapterPosition(),DELETE);
    }
}
