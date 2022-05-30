package com.amir.ss.noteproject;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import net.appitiza.android.drawingpad.drawpad.views.DrawingView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawPaint extends AppCompatActivity {
    DrawingView drawingView;
    String currentDateandTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_paint);
          drawingView =(DrawingView)findViewById(R.id.my_pictrue);










    }
    public void ConvertBitMapToPng(String titel){
        ActivityCompat.requestPermissions(DrawPaint.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);


        Bitmap bitmap=drawingView.getSignatureBitmap();
        File path= Environment.getExternalStorageDirectory();

        File dir=new File(path+"/NoteBook/image");
        dir.mkdirs();


        File file=new File(dir,titel+".jpg");
        OutputStream outputStream=null;

        try {
            outputStream=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
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

    @Override
    public void onBackPressed() {
        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        currentDateandTime = sdf.format(new Date());

        ConvertBitMapToPng(currentDateandTime);
        Intent intent=new Intent (DrawPaint.this,MainActivity.class);
        intent.putExtra("TabNumber","1");


        startActivity(intent);

  finish();
    }
}
