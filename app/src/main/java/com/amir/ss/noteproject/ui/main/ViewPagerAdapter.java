package com.amir.ss.noteproject.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final int NUM_TABS = 2;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new DrawsListFragment();
        } else {
            return new NotesListFragment();
        }

    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
