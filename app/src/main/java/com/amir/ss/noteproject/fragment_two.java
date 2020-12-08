package com.amir.ss.noteproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class fragment_two extends Fragment {


    GridView  mylist;
    ImageAdbter adbter;
    Dialog_for_Paint paint;

    DrawingView drawingView;
   boolean Valid=false;
   Button button;

    List<FileMode> LIST;

    String currentDateandTime;

    fragment_two(){

    }




   View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      view= inflater.inflate(R.layout.fragment_layout_two,container,false);

          mylist=(GridView )view.findViewById(R.id.my_list_two);



        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        currentDateandTime = sdf.format(new Date());




        ((ImageButton)view.findViewById(R.id.btn_add_draw)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paint=new Dialog_for_Paint(adbter,mylist);

                         paint.show(getActivity().getFragmentManager(),"");



                try {


                    adbter= new ImageAdbter(getActivity(),((MainActivity)getContext()).readList(),getActivity().getFragmentManager(),mylist);



                    adbter = new ImageAdbter(getActivity(),((MainActivity)getContext()).readList(),getActivity().getFragmentManager(),mylist);
                    mylist.setAdapter(adbter);
                    adbter.notifyDataSetChanged();
                    mylist.deferNotifyDataSetChanged();



//         Toast .makeText(getActivity(),Integer.toString(mylist.getAdapter().getCount()),Toast.LENGTH_SHORT).show();




                }catch (Exception ex){


                }
//                Intent intent=new Intent(getActivity(),DrawPaint.class);
//                startActivity(intent);
//                getActivity().finish();



            }
        });

        try {
            ((TextView)((MainActivity)getActivity()).myView.findViewById(R.id.draw_name)).setText((((MainActivity) getActivity()).readList()).get(2).getDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
          adbter = new ImageAdbter(getActivity(), ((MainActivity)getActivity()).readList(),getActivity().getFragmentManager(),mylist);
            mylist.setAdapter(adbter);
            adbter.notifyDataSetChanged();
            mylist.deferNotifyDataSetChanged();
        }



        catch (Exception ex){

        }


                         try {
                          adbter = new ImageAdbter(getActivity(),((MainActivity)getActivity()).readList(),getActivity().getFragmentManager(),mylist);
                             mylist.setAdapter(adbter);
                            adbter.notifyDataSetChanged();
                             mylist.deferNotifyDataSetChanged();
                         }



                         catch (Exception ex){

                         }






















        return  view;
    }
    public ArrayList<FileMode> readList()throws Exception{

        ArrayList<FileMode> myModel=new ArrayList<>();

        File path= Environment.getExternalStorageDirectory();

        File dir=new File(path+"/NoteBook/image");

        File [] files=dir.listFiles();


                   for (int i = 0; i < files.length; i++) {

                       File file = files[i];
                       FileMode model = new FileMode();
                       model.setDetail(file.getName());
                       model.setUri(Uri.fromFile(file));
                       myModel.add(model);

                   }



        return myModel;
    }


    public static Fragment newInstance() {



        fragment_two fragment = new fragment_two();

        return fragment;
    }
    public void paint(){
        try {
            ImageAdbter  adbter = new ImageAdbter(getActivity(), readList(),getActivity().getFragmentManager(),mylist);
            mylist.setAdapter(adbter);
            mylist.deferNotifyDataSetChanged();

        }



        catch (Exception ex){

        }
    }
    public void ConvertBitMapToPng(String titel){
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);


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




}


