package com.amir.ss.noteproject.ui.main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.DrawsAdapter;
import com.amir.ss.noteproject.FileMode;
import com.amir.ss.noteproject.MainActivity;
import com.amir.ss.noteproject.PaintDialog;
import com.amir.ss.noteproject.R;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            adapter = new DrawsAdapter(requireContext(), readList());
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
            adapter = new DrawsAdapter(getActivity(), readList());
            gridList.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


        try {
            adapter = new DrawsAdapter(getActivity(), readList());
            gridList.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return view;
    }

    public ArrayList<FileMode> readList() throws Exception {
        ArrayList<FileMode> myModel = new ArrayList<>();
        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path + "/NoteBook");
        File[] files = dir.listFiles();

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
        DrawsListFragment fragment = new DrawsListFragment();
        return fragment;
    }

}


