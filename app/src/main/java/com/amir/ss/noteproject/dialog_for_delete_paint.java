package com.amir.ss.noteproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.io.File;

public class dialog_for_delete_paint extends DialogFragment   {

  ImageAdbter adbter;
  GridView gridView;
  String name;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View miv=inflater.inflate(R.layout.dialog_delete_paint,null);


        ((Button)miv.findViewById(R.id.diloge_delete_paint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                File path = Environment.getExternalStorageDirectory();
               File dir = new File(path + "/NoteBook/image/");
               dir.mkdirs();
               File file = new File(dir, name);
               file.delete();




                try {

                    adbter = new ImageAdbter(getActivity(), ((MainActivity) getActivity()).readList(), getFragmentManager(), gridView);

                   gridView.setAdapter(adbter);
                    adbter.notifyDataSetChanged();


                }catch (Exception ex){

                }
                dismiss();



            }
        });
        ((Button)miv.findViewById(R.id.diloge_cancel_paint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();



            }
        });





        builder.setView(miv);
        return builder.create();
    }
}
