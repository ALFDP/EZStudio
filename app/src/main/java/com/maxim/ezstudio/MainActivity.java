package com.maxim.ezstudio;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button newButton;
    Button openButton;

    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link button to view
        this.initButton();

        // Init View
        this.initList();

        // Debug Init List
        this.initListDEBUG();
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

    private void initList() {

    }

    private void initListDEBUG() {
        String[] fakeProject = {"Master Of Puppets", "jomsviking", "Hardwire... The Self Destruct", "Quantum Of Enigma"};
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fakeProject);
        ListView recentList = (ListView) findViewById(R.id.lv_recentProject);
        recentList.setAdapter(listAdapter);
    }


}
