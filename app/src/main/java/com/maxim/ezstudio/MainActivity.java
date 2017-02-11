package com.maxim.ezstudio;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newButton;
    Button openButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link button to view
        this.initButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initButton() {
        newButton = (Button)findViewById(R.id.bt_newProject);
        openButton = (Button)findViewById(R.id.bt_openProject);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreationDialog creationDialog = new CreationDialog();
                creationDialog.show(getFragmentManager(), "creation_alert");
            }
        });

    }


}
