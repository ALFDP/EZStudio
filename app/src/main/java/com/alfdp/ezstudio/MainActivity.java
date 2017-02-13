package com.alfdp.ezstudio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button newButton;
    Button openButton;

    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initList();
        initButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initList();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_maps) {
            // Handle the maps action
            Intent intent = new Intent(this, MapsActivity.class);
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        ListView recentList = (ListView) findViewById(R.id.lv_recentProject);
        TextView noRecent = (TextView) findViewById(R.id.lb_noRecentProject);

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

        if(0 < projects.size()) {
            Project[] projectTab = new Project[projects.size()];
            noRecent.setVisibility(View.GONE);
            recentList.setVisibility(View.VISIBLE);
            projectTab = projects.toArray(projectTab);
            listAdapter = new RecentListAdapter(this, projectTab);
            recentList.setAdapter(listAdapter);

        } else {
            noRecent.setVisibility(View.VISIBLE);
            recentList.setVisibility(View.GONE);
        }



    }

    private ProjectType getProjectType(String key) {

        String parseKey = key.substring(0,1);

        if(parseKey.equals("0")) {
            return ProjectType.ALBUM;
        }
        return ProjectType.TRACK;
    }

    private long prepareId(String idToPrepare) {

        return Long.valueOf(idToPrepare.substring(2));
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
