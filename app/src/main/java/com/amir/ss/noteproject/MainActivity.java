package com.amir.ss.noteproject;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.amir.ss.noteproject.ui.main.MainFragment;
import com.amir.ss.noteproject.ui.main.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialFragmentContainer();
    }

    public void initialFragmentContainer(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.class, null)
                .setReorderingAllowed(true)
                .commit();
    }


    public ArrayList<FileMode> readList() {
        ArrayList<FileMode> myModel = new ArrayList<>();
        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path + "/NoteBook/image");
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


}


