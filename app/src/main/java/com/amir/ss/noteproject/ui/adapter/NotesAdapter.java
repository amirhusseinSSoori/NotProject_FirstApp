package com.amir.ss.noteproject.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.DetailActivity;
import com.amir.ss.noteproject.MainActivity;
import com.amir.ss.noteproject.R;
import com.amir.ss.noteproject.data.model.Category;

import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    Context _Context;
    ContractWithDialog contractor;

    public void setOnContractor(ContractWithDialog contractor) {
        this.contractor = contractor;
    }

    public void submitList(List<Category> list) {
        diffResult.submitList(list);
    }

    AsyncListDiffer<Category> diffResult = new AsyncListDiffer<Category>(this, new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getCategoryId() == newItem.getCategoryId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getCategoryId() == newItem.getCategoryId();
        }
    });

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_items, parent, false);
        _Context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.fetchData(position);
    }

    public interface ContractWithDialog {
        void deleteItem(int Position);
    }

    @Override
    public int getItemCount() {
        return diffResult.getCurrentList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_name, tx_data;
        private ImageButton pic_delete;
        private ImageButton pic_edit;
        public CardView card1;
        public LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            card1 = itemView.findViewById(R.id.card_one);
            tx_data = itemView.findViewById(R.id.txt_data);
            tx_name = itemView.findViewById(R.id.txt_name);
            pic_delete = itemView.findViewById(R.id.bin_btn);
            pic_edit = itemView.findViewById(R.id.download_folder_btn);
            linearLayout = itemView.findViewById(R.id.line_click);
        }

        public void fetchData(int position) {

            tx_name.setText(diffResult.getCurrentList().get(position).getTitle().toString());
            tx_data.setText(diffResult.getCurrentList().get(position).getDate());
            pic_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contractor.deleteItem(diffResult.getCurrentList().get(position).getCategoryId());
                }
            });
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(_Context, DetailActivity.class);
                    intent.putExtra("category", diffResult.getCurrentList().get(position));
                    ((MainActivity) linearLayout.getContext()).startActivity(intent);
                    ((MainActivity) linearLayout.getContext()).finish();
                }
            });


            pic_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(_Context, "this note save in storage", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}
