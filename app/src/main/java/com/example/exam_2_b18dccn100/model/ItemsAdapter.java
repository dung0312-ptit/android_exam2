package com.example.exam_2_b18dccn100.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam_2_b18dccn100.AddActivity;
import com.example.exam_2_b18dccn100.DatabaseHandler;
import com.example.exam_2_b18dccn100.MainActivity;
import com.example.exam_2_b18dccn100.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends  RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private List<Items> iList;
    private ItemListener itemListener;

    public ItemsAdapter(List<Items> iList) {
        this.iList = iList;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position){
        Items i = iList.get(position);
        if(i==null)return;
        holder.nameTxt.setText(i.getName());
        holder.idTxt.setText(Integer.toString(i.getId()));

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(view.getContext());
                db.delete(i.getId());
                iList.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
       if(iList != null) return iList.size();
       return 0;
    }

    public Items getItem(int position) {
        return iList.get(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nameTxt,idTxt;
        private Button deleteBtn;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);

            nameTxt = itemView.findViewById(R.id.item_name);
            deleteBtn = itemView.findViewById(R.id.item_delete);
            idTxt = itemView.findViewById(R.id.item_id);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(itemListener != null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}
