package com.amir.ss.noteproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.io.File;
import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;

import static maes.tech.intentanim.CustomIntent.customType;

@SuppressLint("ValidFragment")
public class fragment_one extends Fragment   {



    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    DataBaseHelper dataBaseHelper;
    ConstraintLayout linearLayout;
    MyAdbter adapter;

    Context _Context;
    View view;




    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_layout_one,container,false);


        mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView_one);







        linearLayout=(ConstraintLayout)view.findViewById(R.id.line_two);
        creatFolder();
        ((ShimmerLayout)view.findViewById(R.id.shimmer_text_for)).startShimmerAnimation();

        dataBaseHelper=new DataBaseHelper(getActivity());
        dataBaseHelper.getWritableDatabase();


        ((LinearLayout)view.findViewById(R.id.two_line)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout)view.findViewById(R.id.two_line)).setVisibility(View.GONE);

            }
        });


        filllist();
        if(new DataBaseHelper(getActivity()).getCategory().isEmpty()){
            ((LinearLayout)view.findViewById(R.id.two_line)).setVisibility(View.VISIBLE);

        }else {
            ((LinearLayout)view.findViewById(R.id.two_line)).setVisibility(View.GONE);
        }






        mFloatingActionButton = (FloatingActionButton)view. findViewById(R.id.floating_action_button);

        hideFloatingActionBar();
        addDetail();





        return  view;
    }


    public static Fragment newInstance() {



        fragment_one fragment = new fragment_one();

        return fragment;
    }
    public void creatFolder(){
        File folder=new File(Environment.getExternalStorageDirectory()+File.separator+"NoteBook/image");
        boolean success=true;
        if(!folder.exists()){
            success=folder.mkdirs();
        }

        if(success){


        }else{


        }
    }
    public  void addDetail(){

        ((ImageButton)view.findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),InputActivity.class);
                startActivity(intent);
               getActivity().finish();
            }
        });





    }

    @Override
    public void onSaveInstanceState( Bundle outState) {
      super.onSaveInstanceState(outState);
      if(mRecyclerView!=null){
          outState.putParcelable("key",mRecyclerView.getLayoutManager().onSaveInstanceState());
      }
    }

    void filllist(){
        DataBaseHelper dataBaseHelper=new DataBaseHelper(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);

        mRecyclerView.setLayoutManager(layoutManager);

       adapter=new MyAdbter(dataBaseHelper.getCategory(), getActivity().getApplicationContext(),dataBaseHelper,getActivity().getFragmentManager(),mRecyclerView);




        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


    public void hideFloatingActionBar(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });



    }



}
