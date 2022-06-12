package com.amir.ss.noteproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.DrawsAdapter;
import com.amir.ss.noteproject.data.datasource.file.FileSystem;
import com.amir.ss.noteproject.MainActivity;
import com.amir.ss.noteproject.PaintDialog;
import com.amir.ss.noteproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DrawsListFragment extends Fragment {

    RecyclerView gridList;
    DrawsAdapter adapter;
    PaintDialog paint;
    View view;
    String date;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            adapter = new DrawsAdapter(requireContext(), new FileSystem().loadImage(requireContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_painting_list, container, false);
        gridList = (RecyclerView) view.findViewById(R.id.grid_painting_list);

        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        date = sdf.format(new Date());


        ((ImageButton) view.findViewById(R.id.btn_add_draw)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint = new PaintDialog(adapter);
                paint.show(getActivity().getFragmentManager(), "");
                try {
                    gridList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (Exception ex) {


                }
            }
        });


        try {
            ((TextView) ((MainActivity) getActivity()).findViewById(R.id.draw_name)).setText((((MainActivity) getActivity()).readList()).get(2).getDetail());
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            adapter = new DrawsAdapter(getActivity(), new FileSystem().loadImage(requireContext()));
            gridList.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


        return view;
    }

    public static Fragment newInstance() {
        DrawsListFragment fragment = new DrawsListFragment();
        return fragment;
    }

}


