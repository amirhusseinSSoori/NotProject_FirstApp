package com.amir.ss.noteproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
                    saveImage(drawingView.getSignatureBitmap(), date, getActivity());
                    imageAdapter = new DrawsAdapter(getActivity(), ((MainActivity) getActivity()).readList());
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

    public void saveFile() {

    }

    public void ConvertBitMapToPng(String title) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        Bitmap bitmap = drawingView.getSignatureBitmap();
        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path + "/NoteBook/image");
        dir.mkdirs();
        File file = new File(dir, title + ".hilton");
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

    public ArrayList<FileMode> readList() {
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

    private void saveImage(Bitmap bitmap, String name, Context context) throws IOException {
        boolean saved;
        OutputStream fos;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/" + "NoteBook");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(imageUri);
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).toString() + File.separator + "NoteBook";
            File file = new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }
            File image = new File(imagesDir, name + ".png");
            fos = new FileOutputStream(image);

        }

        saved = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
    }
}