package com.amir.ss.noteproject.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.amir.ss.noteproject.R;

public class RemovingDialog extends DialogFragment {

    RemoveContract removeContract;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View miv = inflater.inflate(R.layout.dialog_delete_paint, null);


        ((Button) miv.findViewById(R.id.diloge_delete_paint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeContract.removeItem();
                dismiss();
            }
        });


        ((Button) miv.findViewById(R.id.diloge_cancel_paint)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        builder.setView(miv);
        return builder.create();
    }

    public void setOnRemoveListener(RemoveContract removeContract) {
        this.removeContract = removeContract;

    }


    public interface RemoveContract {
        void removeItem();
    }
}
