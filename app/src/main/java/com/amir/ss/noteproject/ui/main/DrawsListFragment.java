package com.amir.ss.noteproject.ui.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.ui.adapter.DrawsAdapter;
import com.amir.ss.noteproject.ui.dialog.PaintDialog;
import com.amir.ss.noteproject.R;
import com.amir.ss.noteproject.data.di.AppContainer;
import com.amir.ss.noteproject.ui.viewmodel.DrawsViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DrawsListFragment extends Fragment {
    RecyclerView gridList;
    DrawsAdapter adapter;
    PaintDialog paint;
    View view;
    String date;
    DrawsViewModel drawsViewModel;
    ImageButton showDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DrawsAdapter();
        drawsViewModel = new AppContainer().ProvideDrawsViewModel();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_painting_list, container, false);
        gridList = (RecyclerView) view.findViewById(R.id.grid_painting_list);
        showDialog = (ImageButton) view.findViewById(R.id.btn_add_draw);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        date = sdf.format(new Date());


        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint = new PaintDialog(adapter);
                paint.show(getActivity().getFragmentManager(), "");
                paint.AddImageContractListener(new PaintDialog.AddImageContract() {
                    @Override
                    public void addItem(Bitmap bitmap) throws IOException {
                        drawsViewModel.insertImages(bitmap, date);
                        drawsViewModel.showAllImages();
                    }
                });
            }
        });


        showAllList();
    }

    private void showAllList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        drawsViewModel._resultList.observe(getViewLifecycleOwner(), observe -> {
            gridList.setAdapter(adapter);
            adapter.submitList(observe);
            gridList.setLayoutManager(layoutManager);
        });

    }


    public static Fragment newInstance() {
        DrawsListFragment fragment = new DrawsListFragment();
        return fragment;
    }

}


