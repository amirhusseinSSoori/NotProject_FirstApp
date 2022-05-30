package com.amir.ss.noteproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.data.db.DataBaseHelper;
import com.amir.ss.noteproject.ui.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

import io.supercharge.shimmerlayout.ShimmerLayout;


public class NotesListFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    DataBaseHelper dataBaseHelper;
    ConstraintLayout linearLayout;
    MyAdapter adapter;
    View view;

    MainViewModel mainViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_one);
        linearLayout = (ConstraintLayout) view.findViewById(R.id.line_two);
        createFolder();
        ((ShimmerLayout) view.findViewById(R.id.shimmer_text_for)).startShimmerAnimation();
        dataBaseHelper = new DataBaseHelper(getActivity());
        dataBaseHelper.getWritableDatabase();


        fillList();
        hideFloatingActionBar();
        addDetail();


//        ((LinearLayout) view.findViewById(R.id.two_line)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((LinearLayout) view.findViewById(R.id.two_line)).setVisibility(View.GONE);
//
//            }
//        });


//        if (new DataBaseHelper(getActivity()).getCategory().isEmpty()) {
//            ((LinearLayout) view.findViewById(R.id.two_line)).setVisibility(View.VISIBLE);
//
//        } else {
//            ((LinearLayout) view.findViewById(R.id.two_line)).setVisibility(View.GONE);
//        }


        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating);


        return view;
    }


    public static Fragment newInstance() {
        NotesListFragment fragment = new NotesListFragment();
        return fragment;
    }

    public void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "NoteBook/image");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {

        } else {


        }
    }

    public void addDetail() {
        ((ImageButton) view.findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecyclerView != null) {
            outState.putParcelable("key", mRecyclerView.getLayoutManager().onSaveInstanceState());
        }
    }

    void fillList() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);


        mainViewModel._resultList.observe(getViewLifecycleOwner(),observe -> {

            adapter = new MyAdapter(dataBaseHelper.getCategory(), getActivity().getApplicationContext(), dataBaseHelper, getActivity().getFragmentManager(), mRecyclerView);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(layoutManager);
            adapter.notifyDataSetChanged();

        });

    }


    public void hideFloatingActionBar() {
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
