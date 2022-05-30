package com.amir.ss.noteproject;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;


import android.content.Intent;

import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MyAdbter extends RecyclerView.Adapter<MyAdbter.MyViewHolder> {

   List<Category> LIST;
   Context _Context;
   DataBaseHelper dataBaseHelper;
   FragmentManager fragment;
   RecyclerView mrecyclerView;
   ArrayList<Category> getLIST;




    public MyAdbter(List<Category> mydata, Context context, DataBaseHelper dataBase, FragmentManager fragmentManager,RecyclerView recyclerView) {
        LIST = mydata;
        _Context = context;
        dataBaseHelper=dataBase;
        fragment=fragmentManager;
        mrecyclerView=recyclerView;
        getLIST=new ArrayList<Category>();
        getLIST.addAll(LIST);

    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,null,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tx_name.setText(LIST.get(position).getTitel().toString());
        holder.tx_data.setText(LIST.get(position).getDate());
        holder.pic_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyDilalog dilalog=new MyDilalog(LIST.get(position).getCategoryid(),_Context,dataBaseHelper,mrecyclerView);
                dilalog.show(fragment,"");

            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent=new Intent(_Context,DetailActivity.class);
               intent.putExtra("category",  LIST.get(position));
                ((MainActivity)holder.linearLayout.getContext()).startActivity(intent);
                ((MainActivity)holder.linearLayout.getContext()).finish();

            }
        });



          holder.pic_edit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                      write(LIST.get(position).getTitel()+".txt",LIST.get(position).getDetail());
                Toast.makeText(_Context,"this note save in storage",Toast.LENGTH_SHORT).show();


              }
          });


          }



    @Override
    public int getItemCount() {
        return LIST.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_name, tx_data;
        private ImageButton pic_delete;
        private ImageButton pic_edit;
        public CardView card1;

         public LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            card1=(CardView)itemView.findViewById(R.id.card_one);
            tx_data = (TextView) itemView.findViewById(R.id.txt_data);
            tx_name = (TextView) itemView.findViewById(R.id.txt_name);
            pic_delete = (ImageButton) itemView.findViewById(R.id.bin_btn);

            pic_edit = (ImageButton) itemView.findViewById(R.id.download_folder_btn);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.line_click);



        }
    }
    public void write(String titel,String detail){
     String rootPath =Environment.getExternalStorageDirectory()
             .getAbsolutePath()+"/NoteBook/";
     File root =new File(rootPath);
     if(!root.exists()){
         root.mkdirs();
     }
     File f=new File(rootPath+titel);
     if(f.exists()){
         f.delete();
     }
        try {
            f.createNewFile();
            FileOutputStream out=new FileOutputStream(f);
            out.write(detail.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        LIST.clear();
        if (charText.length() == 0) {
            LIST.addAll(getLIST);
        } else {
            for (Category wp : getLIST) {
                if (wp.getTitel().toLowerCase(Locale.getDefault()).contains(charText)) {
                    LIST.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
