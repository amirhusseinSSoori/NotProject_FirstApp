package com.amir.ss.noteproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amir.ss.noteproject.ui.adapter.NotesAdapter;
import com.amir.ss.noteproject.R;
import com.amir.ss.noteproject.ui.dialog.RemovingDialog;
import com.amir.ss.noteproject.data.di.AppContainer;
import com.amir.ss.noteproject.ui.viewmodel.NotesViewModel;
import com.amir.ss.noteproject.ui.input.InputFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.supercharge.shimmerlayout.ShimmerLayout;


public class NotesListFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private NotesAdapter adapter;
    private View view;
    private NotesViewModel mainViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new AppContainer().ProvideMainVieModel();
        adapter = new NotesAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_one);
        ((ShimmerLayout) view.findViewById(R.id.shimmer_text_for)).startShimmerAnimation();
        hideFloatingActionBar();
        addDetail();
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillList();
    }

    public static Fragment newInstance() {
        NotesListFragment fragment = new NotesListFragment();
        return fragment;
    }



    public void addDetail() {
        ((ImageButton) view.findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialInputFragment();}
        });
    }
    public void initialInputFragment(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InputFragment.class, null)
                .setReorderingAllowed(true)
                .commit();


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecyclerView != null) {
            outState.putParcelable("key", mRecyclerView.getLayoutManager().onSaveInstanceState());
        }
    }

    void fillList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mainViewModel._resultList.observe(getViewLifecycleOwner(),observe -> {
            mRecyclerView.setAdapter(adapter);
            adapter.submitList(observe);
            mRecyclerView.setLayoutManager(layoutManager);
        });


        adapter.setOnContractor(new NotesAdapter.ContractWithDialog() {
            @Override
            public void deleteItem(int Position) {
                RemovingDialog paint = new RemovingDialog();
                paint.show(getActivity().getFragmentManager(), "tag");
                paint.setOnRemoveListener( () -> {
                    mainViewModel.deleteInItemNotes(Position);
                    mainViewModel.event();
                });
            }
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
