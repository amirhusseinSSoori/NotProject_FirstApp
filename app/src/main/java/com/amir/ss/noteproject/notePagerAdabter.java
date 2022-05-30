package com.amir.ss.noteproject;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import net.appitiza.android.drawingpad.drawpad.views.DrawingView;

public class notePagerAdabter extends FragmentPagerAdapter {

    private final int NUM_PAGE = 2;
    public notePagerAdabter(FragmentManager fm) {
        super(fm);
    }
   Context context;

    @Override
    public Fragment getItem(int position) {
        Fragment returnValue = new Fragment();
        switch (position) {
            case 0:
                returnValue = fragment_one.newInstance();

                break;
            case 1:
                returnValue = fragment_two.newInstance();
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
        String titel="";
        switch (position){
            case 0:
                titel="Note";
                break;
            case 1:
                titel="Drawing";
                break;





        }

        return titel;
    }
}
