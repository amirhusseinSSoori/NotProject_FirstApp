package com.amir.ss.noteproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.amir.ss.noteproject.data.datasource.file.FileSystem;

import net.appitiza.android.drawingpad.drawpad.views.DrawingView;

import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressLint("ValidFragment")
public class PaintDialog extends DialogFragment {
    DrawsAdapter imageAdapter;
    DrawingView drawingView;
    String date;


    public PaintDialog(DrawsAdapter imageAdapter) {
        this.imageAdapter = imageAdapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View miv = inflater.inflate(R.layout.dialog_paint, null);
        drawingView = (DrawingView) miv.findViewById(R.id.my_pictrue2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            drawingView.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        }

        ((Button) miv.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
                date = sdf.format(new Date());
                try {
                    new FileSystem().saveImage(drawingView.getSignatureBitmap(), date, getActivity());
                    imageAdapter.notifyDataSetChanged();
                } catch (Exception ex) {

                }
                dismiss();
            }
        });


        ((Button) miv.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        builder.setView(miv);
        return builder.create();
    }


}