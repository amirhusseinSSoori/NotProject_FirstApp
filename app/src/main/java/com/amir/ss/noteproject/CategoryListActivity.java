package com.amir.ss.noteproject;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class CategoryListActivity extends Activity {

    void filllist(){
            DataBaseHelper dataBaseHelper=new DataBaseHelper(CategoryListActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

            getRecyclerView().setLayoutManager(layoutManager);

            MyAdbter adapter=new MyAdbter(dataBaseHelper.getCategory(), getApplicationContext(),dataBaseHelper,getFragmentManager(),getRecyclerView());

            adapter.notifyDataSetChanged();

            getRecyclerView().setAdapter(adapter);
        }
        abstract RecyclerView getRecyclerView();


}
