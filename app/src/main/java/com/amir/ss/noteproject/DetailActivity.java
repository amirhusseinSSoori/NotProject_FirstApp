package com.amir.ss.noteproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import io.supercharge.shimmerlayout.ShimmerLayout;

import static maes.tech.intentanim.CustomIntent.customType;

public class DetailActivity extends AppCompatActivity {





    EditText edit_titel;
    EditText edit_detail;
    TextView text_titel;
    TextView text_detail;
    Category category;

 LinearLayout layout;

    private android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        layout=(LinearLayout)findViewById(R.id.line_fure);








        customType(DetailActivity.this,"left-to-right");

//        AnimationDrawable drawable=(AnimationDrawable)layout.getBackground();
//
//        drawable.setEnterFadeDuration(4500);
//        drawable.setExitFadeDuration(4500);
//        drawable.start();
        edit_titel=(EditText)findViewById(R.id.Detail_Titel);
        edit_detail=(EditText)findViewById(R.id.Detail_Detail);
        text_titel=(TextView)findViewById(R.id.Detail_txt_Titel);
        text_detail=(TextView)findViewById(R.id.Detail_txt_Detail);
        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar_one);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);











        category=(Category)getIntent().getExtras().getSerializable("category");

        text_titel.setText(category.getTitel().toString());
       text_detail.setText(category.getDetail().toString());
        edit_titel.setText(category.getTitel().toString());
        edit_detail.setText(category.getDetail().toString());


    }

 public  void setLocal(String lang){
     Locale mylocal=new Locale(lang);
     Resources res=getResources();
     DisplayMetrics dm=res.getDisplayMetrics();
     Configuration conf=res.getConfiguration();
     conf.locale=mylocal;
     res.updateConfiguration(conf,dm);
     Intent refresfh=new Intent(this,DetailActivity.class);
     startActivity(refresfh);
     finish();
 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.action_call:
               SetVisibility();
                break;

            case android.R.id.home:
                new DataBaseHelper(this).UpdateCategory(category.getCategoryid(),edit_titel.getText().toString(),edit_detail.getText().toString());
                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("TabNumber","0");
                startActivity(intent);
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void SetVisibility(){
      if(text_titel.getVisibility()== View.VISIBLE  && text_detail.getVisibility()==View.VISIBLE){
          edit_titel.setVisibility(View.VISIBLE);
          edit_detail.setVisibility(View.VISIBLE);
          text_titel.setVisibility((View.GONE));
          text_detail.setVisibility((View.GONE));
          new DataBaseHelper(this).UpdateCategory(category.getCategoryid(),edit_titel.getText().toString(),edit_detail.getText().toString());
          text_titel.setText(edit_titel.getText().toString());
          text_detail.setText(edit_detail.getText().toString());

      }else {
          edit_titel.setVisibility(View.GONE);
          edit_detail.setVisibility(View.GONE);
          text_titel.setVisibility((View.VISIBLE));
          text_detail.setVisibility((View.VISIBLE));
          text_titel.setText(edit_titel.getText().toString());
          text_detail.setText(edit_detail.getText().toString());
          new DataBaseHelper(this).UpdateCategory(category.getCategoryid(),text_titel.getText().toString(),text_detail.getText().toString());
      }

    }


    @Override
    public void onBackPressed() {

        new DataBaseHelper(this).UpdateCategory(category.getCategoryid(),edit_titel.getText().toString(),edit_detail.getText().toString());
        startActivity(new Intent(DetailActivity.this,MainActivity.class));
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("TabNumber","0");
        startActivity(intent);
        finish();



        super.onBackPressed();
    }
}
