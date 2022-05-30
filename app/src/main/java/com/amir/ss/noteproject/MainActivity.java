package com.amir.ss.noteproject;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private TabLayout layout;
    Boolean valid =true;
    String name="key";
        View myView;
        int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        viewPager=(ViewPager)findViewById(R.id.view_pager_one);



        layout=(TabLayout)findViewById(R.id.tab_one) ;
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));

   adapter=new notePagerAdabter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        Intent intent=getIntent();
        if(intent.hasExtra("TabNumber")){
            String tab=intent.getExtras().getString("TabNumber");
            switchToTab(tab);
        }








            layout.setupWithViewPager(viewPager);





    }
    public void switchToTab(String tab){
        if(tab.equals("0")){
            viewPager.setCurrentItem(0);
        }else if(tab.equals("1")){
            viewPager.setCurrentItem(1);
        }
    }







    public ArrayList<FileMode> readList() throws Exception{

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


