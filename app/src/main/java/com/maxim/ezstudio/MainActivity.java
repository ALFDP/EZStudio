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

import com.maxim.ezstudio.core.Album;
import com.maxim.ezstudio.core.Project;
import com.maxim.ezstudio.core.Track;
import com.maxim.ezstudio.design.RecentListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        long[] link = new long[] {4,5,6,7,8};

        Album master = new Album(0, "Master Of Puppets", date, "Metallica", date);
        Album quantum = new Album(1, "Quantum Of Enigma", date, "Epica", date);
        Track drink = new Track(2, "Drink", date, "Alestorm", link);
        Track mon = new Track(3, "J'aime mettre mon penis", date, "Antoine Daniel", link);


        Project[] projects = new Project[] {master, drink, quantum, mon};

        listAdapter = new RecentListAdapter(this, projects);
        ListView recentList = (ListView) findViewById(R.id.lv_recentProject);
        recentList.setAdapter(listAdapter);
    }


}
