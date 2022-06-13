package com.amir.ss.noteproject.ui.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.amir.ss.noteproject.R;
import com.amir.ss.noteproject.ui.adapter.DrawsAdapter;


@SuppressLint("ValidFragment")
public class DeleteDialog extends DialogFragment {
    Uri uri;
    Context _Context;
    RemoveContract removeContract;

    public DeleteDialog(Uri uri, Context context) {
        this.uri = uri;
        _Context = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View miv = inflater.inflate(R.layout.dilog_for_delete, null);

        ((Button) miv.findViewById(R.id.diloge_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeContract.removeItem(uri);
                dismiss();
            }
        });
        ((Button) miv.findViewById(R.id.diloge_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        builder.setView(miv);
        return builder.create();
    }

    public interface RemoveContract {
        void removeItem(Uri uri);
    }
}
