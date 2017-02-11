package com.maxim.ezstudio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by maxim on 11/02/2017.
 */

public class CreationDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogView = inflater.inflate(R.layout.dialog_project_creation, null);
        builder.setView(dialogView);
        // Add action buttons
        final Button btnCreate = (Button) dialogView.findViewById(R.id.btn_createProject);
        final Button btnCancel = (Button) dialogView.findViewById(R.id.btn_cancelDialog);

        btnCreate.setOnClickListener(this.setCreateButton());
        btnCancel.setOnClickListener(this.setCancelButton());
        
        return builder.create();
    }

    private View.OnClickListener setCreateButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        };
    }

    private View.OnClickListener setCancelButton(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        };
    }
}
