package com.amir.ss.noteproject;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.amir.ss.noteproject.ui.main.NotesListFragment;

public class notePagerAdapter extends FragmentPagerAdapter {

    private final int NUM_PAGE = 2;

    public notePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    Context context;

    @Override
    public Fragment getItem(int position) {
        Fragment returnValue = new Fragment();
        switch (position) {
            case 0:
                returnValue = NotesListFragment.newInstance();

                break;
            case 1:
                returnValue = DrawsListFragment.newInstance();
                break;

        }


        return returnValue;
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String titel = "";
        switch (position) {
            case 0:
                titel = "Note";
                break;
            case 1:
                titel = "Drawing";
                break;


        }

        return titel;
    }
}
