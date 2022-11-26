package com.amir.ss.noteproject.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.R;
import com.amir.ss.noteproject.data.model.ContentModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class DrawsAdapter extends RecyclerView.Adapter<DrawsAdapter.MyViewHolder> {

    Context _Context;
    ContractWithDialog contractor;

    public void setOnContractor(ContractWithDialog contractor) {
        this.contractor = contractor;
    }

    public void submitList(List<ContentModel> list) {
        diffResult.submitList(list);
    }

    AsyncListDiffer<ContentModel> diffResult = new AsyncListDiffer<>(this,
            new DiffUtil.ItemCallback<ContentModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull ContentModel oldItem, @NonNull ContentModel newItem) {
                    return oldItem.getId() == newItem.getId() && oldItem.getContentUris() == newItem.getContentUris() && oldItem.getDisplayName() == newItem.getDisplayName();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ContentModel oldItem, @NonNull ContentModel newItem) {
                    return oldItem.getId() == newItem.getId();
                }


            });


    @NonNull
    @Override
    public DrawsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_items, parent, false);
        _Context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawsAdapter.MyViewHolder holder, int position) {
        holder.fetchData(position);
    }

    @Override
    public int getItemCount() {
        return diffResult.getCurrentList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageButton deleteButton;
        ImageView imageDelete;

        MyViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.draw_name);
            deleteButton = itemView.findViewById(R.id.delete_btn);
            imageDelete = itemView.findViewById(R.id.picture_draw);
        }

        public void fetchData(int position) {
            Glide.with(_Context).load(diffResult.getCurrentList().get(position).getContentUris()).into(imageDelete);
            myTextView.setText(diffResult.getCurrentList().get(position).getDisplayName());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        contractor.deleteItem(diffResult.getCurrentList().get(position).getContentUris(), diffResult.getCurrentList().get(position).getId());

                    } catch (Exception e) {
                        Log.e("showAllImages", "onViewCreated: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public interface ContractWithDialog {
        void deleteItem(Uri uri, int id);
    }

}



