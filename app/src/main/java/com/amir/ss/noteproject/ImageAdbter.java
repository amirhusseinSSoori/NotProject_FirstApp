package com.amir.ss.noteproject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageAdbter extends ArrayAdapter {
    List<FileMode>LIST;
    Context _Context;
    FragmentManager myFragment;
    GridView list;
    CheckBox checkBox;
    View finalConvertView;


    public ImageAdbter(Context context, List<FileMode>mydata , FragmentManager fragment,GridView listview){
        super(context,R.layout.custom_list_two,mydata);
        LIST = mydata;
        _Context = context;
        myFragment=fragment;
        list=listview;


    }

    public ImageAdbter( Context context) {
        super(context,R.layout.custom_list_two);
    }


    ;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=((Activity)_Context).getLayoutInflater().inflate(R.layout.custom_list_two,null);


        Glide.with(_Context).load(LIST.get(position).getUri()).into(((ImageView)convertView.findViewById(R.id.picture_draw)));
        ((TextView)convertView.findViewById(R.id.draw_name)).setText(LIST.get(position).getDetail());











        ((ImageButton)convertView.findViewById(R.id.delete_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File path = Environment.getExternalStorageDirectory();
//                File dir = new File(path + "/NoteBook/image/");
//                dir.mkdirs();
//                File file = new File(dir, LIST.get(position).getDetail());
//                file.delete();

                try {
                    ImageAdbter adbter=new ImageAdbter(_Context,new ImageAdbter(_Context,LIST,myFragment,list).LIST=((MainActivity)_Context).readList(),myFragment,list);
                    dialog_for_delete_paint paint=new dialog_for_delete_paint();
                    paint.name=LIST.get(position).getDetail();
                    paint.adbter=adbter;
                    paint.gridView=list;
                    adbter.notifyDataSetChanged();
                            paint.show(((Activity) _Context).getFragmentManager(),"");
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                try {
//                    ImageAdbter adbter=new ImageAdbter(_Context,new ImageAdbter(_Context,LIST,myFragment,list).LIST=((MainActivity)_Context).readList(),myFragment,list);
//                    Dialog_for_Paint paint=new Dialog_for_Paint(adbter,list);
//                    paint.name=LIST.get(position).getDetail();
//                    paint.show(myFragment,"");
//                    list.setAdapter(adbter);
//                    list.deferNotifyDataSetChanged();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }




            }
        });






        return convertView;
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
}
