package com.amir.ss.noteproject.ui.input;

import static maes.tech.intentanim.CustomIntent.customType;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.amir.ss.noteproject.R;
import com.amir.ss.noteproject.data.di.AppContainer;
import com.amir.ss.noteproject.ui.MainViewModel;
import com.amir.ss.noteproject.ui.main.MainFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputFragment extends Fragment {

    EditText edit_title;
    EditText edit_description;
    String date;
    MainViewModel mainViewModel;
    OnBackPressedCallback callback;
    FragmentManager fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mainViewModel = new AppContainer().ProvideMainVieModel();
        customType(getActivity(), "left-to-right");
        SimpleDateFormat sdf = (SimpleDateFormat) new SimpleDateFormat("MMM MM, dd, yyyy h:mm a").getDateTimeInstance();
        onCustomBackPressed(view);
        date = sdf.format(new Date());
        fm = getActivity()
                .getSupportFragmentManager();

    }

    public void initView(View view) {
        edit_title = (EditText) view.findViewById(R.id.input_Titel);
        edit_description = (EditText) view.findViewById(R.id.input_Detail);
    }


    public String title(View view) {
        String title = ((EditText) view.findViewById(R.id.input_Titel)).getText().toString();
        return title;
    }

    public String detail(View view) {
        String data = ((EditText) view.findViewById(R.id.input_Detail)).getText().toString();
        return data;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callback.setEnabled(false);
        callback.remove();
    }

    private void onCustomBackPressed(View view) {
        callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (edit_title.getText().length() == 0) {
                    mainViewModel.insertToNotes("noTitle", detail(view), date);
                } else {
                    mainViewModel.insertToNotes(title(view), detail(view), date);
                }
                popStackFragmentContainer();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(callback);


    }

    public void popStackFragmentContainer() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.class, null)
                .commit();
    }
}
