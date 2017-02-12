package com.alfdp.ezstudio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alfdp.ezstudio.core.ProjectType;

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

    public void showToast() {
        Toast.makeText(getContext(), "Select the project type", Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener setCreateButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProjectActivity.class);
                Bundle bundle = new Bundle();

                RadioGroup group = (RadioGroup) getDialog().findViewById(R.id.radio_type);

                int id = group.getCheckedRadioButtonId();

                if(id == R.id.radio_track) {
                    bundle.putSerializable("projectType", ProjectType.TRACK);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                    getDialog().dismiss();
                } else if(R.id.radio_album == id) {
                    bundle.putSerializable("projectType", ProjectType.ALBUM);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                    getDialog().dismiss();

                } else {
                    showToast();
                }

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
