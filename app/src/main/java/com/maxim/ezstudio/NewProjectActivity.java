package com.maxim.ezstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.maxim.ezstudio.core.ProjectType;

public class NewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        setTitle(R.string.new_string);

        Bundle bundle = getIntent().getExtras();

        ProjectType type = null;

        if(bundle != null) {
           type = (ProjectType) bundle.getSerializable("projectType");
            Toast.makeText(this, type.toString(), Toast.LENGTH_SHORT).show();
        }

        if(ProjectType.ALBUM == type) {
            setTitle(getString(R.string.new_album_project));
        } else if(ProjectType.TRACK == type) {
            setTitle(getString(R.string.new_track_project));
        } else {
            setTitle(getString(R.string.new_project));
        }
    }
}
