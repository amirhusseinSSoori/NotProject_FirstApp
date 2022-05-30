package com.amir.ss.noteproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DrawsAdapter extends RecyclerView.Adapter<DrawsAdapter.MyViewHolder> {
    List<FileMode> LIST;
    Context _Context;
    private LayoutInflater mInflater;


    public DrawsAdapter(Context context, ArrayList<FileMode> mydata) {
        this.mInflater = LayoutInflater.from(context);
        _Context = context;
        this.LIST = mydata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.draw_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(_Context).load(LIST.get(position).getUri()).into(holder.imageDelete);
        holder.myTextView.setText(LIST.get(position).getDetail());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RemovingDialog paint = new RemovingDialog();
                    paint.show(((Activity) _Context).getFragmentManager(), "");
                    paint.setOnRemoveListener(() -> {
                        File path = Environment.getExternalStorageDirectory();
                        File dir = new File(path + "/NoteBook/image/");
                        dir.mkdirs();
                        File file = new File(dir, LIST.get(position).getDetail());
                        file.delete();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return LIST.size();
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


    }


}
