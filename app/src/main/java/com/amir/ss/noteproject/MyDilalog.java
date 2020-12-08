package com.amir.ss.noteproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


@SuppressLint("ValidFragment")
public class MyDilalog extends DialogFragment {
    int getId;
    Context _Context;
    DataBaseHelper dataBaseHelper;
    RecyclerView mrecyclerView;
    public MyDilalog(int id, Context context, DataBaseHelper helper, RecyclerView recyclerView) {
        getId = id;
        _Context=context;
        dataBaseHelper=helper;
        mrecyclerView=recyclerView;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());


        LayoutInflater inflater=getActivity().getLayoutInflater();
        View miv=inflater.inflate(R.layout.dilog_for_delete,null);
        ((Button)miv.findViewById(R.id.diloge_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataBaseHelper(_Context).deleteCategory(getId);
                MyAdbter adapter=new MyAdbter(dataBaseHelper.getCategory(),_Context,dataBaseHelper,getFragmentManager(),mrecyclerView);
                mrecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                dismiss();



            }
        });
        ((Button)miv.findViewById(R.id.diloge_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();



            }
        });

        builder.setView(miv);
        return builder.create();
    }



}
