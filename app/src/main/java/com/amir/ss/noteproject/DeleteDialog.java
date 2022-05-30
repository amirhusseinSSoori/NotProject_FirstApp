package com.amir.ss.noteproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.data.db.DataBaseHelper;


@SuppressLint("ValidFragment")
public class DeleteDialog extends DialogFragment {
    int getId;
    Context _Context;
    DataBaseHelper dataBaseHelper;
    RecyclerView mRecyclerView;

    public DeleteDialog(int id, Context context, DataBaseHelper helper, RecyclerView recyclerView) {
        getId = id;
        _Context = context;
        dataBaseHelper = helper;
        mRecyclerView = recyclerView;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View miv = inflater.inflate(R.layout.dilog_for_delete, null);

        ((Button) miv.findViewById(R.id.diloge_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataBaseHelper(_Context).deleteCategory(getId);
                MyAdapter adapter = new MyAdapter(dataBaseHelper.getCategory(), _Context, dataBaseHelper, getFragmentManager(), mRecyclerView);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                dismiss();
            }
        });
        ((Button) miv.findViewById(R.id.diloge_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        builder.setView(miv);
        return builder.create();
    }


}
