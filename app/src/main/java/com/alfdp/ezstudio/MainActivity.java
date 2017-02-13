package com.alfdp.ezstudio;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alfdp.ezstudio.core.Album;
import com.alfdp.ezstudio.core.AlbumDAO;
import com.alfdp.ezstudio.core.Project;
import com.alfdp.ezstudio.core.ProjectType;
import com.alfdp.ezstudio.core.Track;
import com.alfdp.ezstudio.core.TrackDAO;
import com.alfdp.ezstudio.design.RecentListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        //this.initListDEBUG();
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
        final String PREFS_NAME = "CacheProject";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        ArrayList<Project> projects = new ArrayList<Project>();

        ArrayList<String> strings = new ArrayList<String>();

        strings.add(settings.getString("cache1", "empty"));
        strings.add(settings.getString("cache2", "empty"));
        strings.add(settings.getString("cache3", "empty"));
        strings.add(settings.getString("cache4", "empty"));
        strings.add(settings.getString("cache5", "empty"));

        for (String object: strings) {
            if(object != "empty") {
                projects.add(getProjectFromBdd(object));
            }
        }

        Project[] projectTab = new Project[projects.size()];
        projectTab = projects.toArray(projectTab);

        listAdapter = new RecentListAdapter(this, projectTab);
        ListView recentList = (ListView) findViewById(R.id.lv_recentProject);
        recentList.setAdapter(listAdapter);

    }

    private ProjectType getProjectType(String key) {

        String parseKey = key.substring(0,1);

        if(parseKey.equals("0")) {
            return ProjectType.ALBUM;
        }
        return ProjectType.TRACK;
    }

    private long prepareId(String idToPrepare) {

        long id = Long.valueOf(idToPrepare.substring(2)).longValue();

        return id;
    }

    private Project getProjectFromBdd(String key) {

        ProjectType type = getProjectType(key);
        long id  = prepareId(key);

        if(ProjectType.ALBUM == type) {
            AlbumDAO albumDao = new AlbumDAO(getBaseContext());

            albumDao.open();
            Album album = albumDao.get(id);
            albumDao.close();

            return album;
        }

        TrackDAO trackDao = new TrackDAO(getBaseContext());

        trackDao.open();
        Track track = trackDao.get(id);
        trackDao.close();

        return track;
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
