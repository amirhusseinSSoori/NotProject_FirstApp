package com.amir.ss.noteproject.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import com.amir.ss.noteproject.ui.dialog.RemovingDialog;
import com.bumptech.glide.Glide;

import java.util.List;

public class DrawsAdapter extends RecyclerView.Adapter<DrawsAdapter.MyViewHolder> {

    Context _Context;


    public void submitList(List<ContentModel> list) {
        diffResult.submitList(list);
    }

    AsyncListDiffer<ContentModel> diffResult = new AsyncListDiffer<ContentModel>(this,
            new DiffUtil.ItemCallback<ContentModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull ContentModel oldItem, @NonNull ContentModel newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
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
        return new DrawsAdapter.MyViewHolder(view);
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
                         RemovingDialog paint = new RemovingDialog();
                         paint.show(((Activity) _Context).getFragmentManager(), "");
                         paint.setOnRemoveListener(() -> {
//                        String imagesDir = Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_DCIM).toString() + File.separator + "DCIM/NoteBook";
//                        _Context.deleteFile( imagesDir);
//                        File file = new File(imagesDir);
//                        if(file.exists());
//                        file.delete();
                         });

                     } catch (Exception e) {
                         e.printStackTrace();
                     }


                 }
             });
        }
        }
    }



