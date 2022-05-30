package com.amir.ss.noteproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.core.app.ActivityCompat;

import net.appitiza.android.drawingpad.drawpad.views.DrawingView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("ValidFragment")
public class Dialog_for_Paint extends DialogFragment {

    ImageAdbter imageAdbter;
    DrawingView drawingView;

    GridView grid;
    String name;
    String currentDateandTime;


    public Dialog_for_Paint(ImageAdbter myadbter, GridView gridView) {
        imageAdbter = myadbter;


        grid = gridView;


    }

    ImageAdbter adbter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View miv = inflater.inflate(R.layout.dialog_paint, null);

        drawingView = (DrawingView) miv.findViewById(R.id.my_pictrue2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            drawingView.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        }
        ((Button) miv.findViewById(R.id.diloge_paint_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File path = Environment.getExternalStorageDirectory();
//                File dir = new File(path + "/NoteBook/image/");
//                dir.mkdirs();
//                File file = new File(dir, name);
//                file.delete();
                SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
                currentDateandTime = sdf.format(new Date());
                ConvertBitMapToPng(currentDateandTime);


                try {

                    adbter = new ImageAdbter(getActivity(), ((MainActivity) getActivity()).readList(), getFragmentManager(), grid);

                    grid.setAdapter(adbter);
                    adbter.notifyDataSetChanged();


                } catch (Exception ex) {

                }


                dismiss();


            }
        });


        ((Button) miv.findViewById(R.id.diloge_paint_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();


            }
        });


        builder.setView(miv);
        return builder.create();
    }

    public ArrayList<FileMode> readList() throws Exception {

        ArrayList<FileMode> myModel = new ArrayList<>();

        File path = Environment.getExternalStorageDirectory();

        File dir = new File(path + "/NoteBook/image");

        File[] files = dir.listFiles();


        for (int i = 0; i < files.length; i++) {

            File file = files[i];
            FileMode model = new FileMode();
            model.setDetail(file.getName());
            model.setUri(Uri.fromFile(file));
            myModel.add(model);

        }


        return myModel;
    }


    public void ConvertBitMapToPng(String titel) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);


        Bitmap bitmap = drawingView.getSignatureBitmap();
        File path = Environment.getExternalStorageDirectory();

        File dir = new File(path + "/NoteBook/image");
        dir.mkdirs();


        File file = new File(dir, titel + ".hilton");
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}